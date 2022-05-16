package com.api.reportes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.model.Bill;
import com.api.model.BillDetails;



public class VentasExcellReport {

    private XSSFSheet sheet;
    private XSSFWorkbook wb;
    private List<Bill> bills;
    private List<BillDetails> billDetails;

    public VentasExcellReport(List<Bill> bills, List<BillDetails> billDetails)
    {
        this.bills = bills;
        this.billDetails = billDetails;
        wb =  new XSSFWorkbook();
    }
    public void readHeader()
    {
        sheet = wb.createSheet("Student-List");
        Row row = sheet.createRow(0);
        CellStyle cS = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setFontHeight(16);
        font.setBold(true);
        cS.setFont(font);
        createCell(row, 0,"Id Cliente", cS);
        createCell(row, 1,"Nombre", cS);
        createCell(row, 2,"Producto", cS);
        createCell(row, 3,"Cantidad", cS);
    }
    private void createCell(Row row, int pos, Object name, CellStyle cS){

        
        Cell cell = (Cell)row.createCell(pos);

        if(name instanceof Integer)
        {
            cell.setCellValue((Integer)name);
        }
        else if(name instanceof Boolean)
        {
            cell.setCellValue((Boolean)name);
        }
        else if(name instanceof Long)
        {
            cell.setCellValue((Long)name);
        }
        else
        {
            cell.setCellValue((String)name);
        }

        cell.setCellStyle(cS);


    }
    private void readBody()
    {
        int rC = 1;
        CellStyle cS = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setFontHeight(12);
        font.setBold(false);
        cS.setFont(font);
        for(Bill b: bills)
        {
        	for(BillDetails bd: billDetails) {
        		if(bd.getBill().getBillNumber() == b.getBillNumber()) {
        			Row row = sheet.createRow(rC);
        			int cC = 0;
        			rC++;
        			createCell(row, cC++,b.getClient().getDni(), cS);
        			createCell(row, cC++,b.getClient().getName(), cS);
        			createCell(row, cC++,bd.getProduct().getName(), cS);
        			createCell(row, cC++,bd.getAmmount(), cS);        			
        		}
        	}
        }
    }
    
    public void export(HttpServletResponse res) throws IOException
    {
        readHeader();
        readBody();
        for(int i=0;i<4;i++) {
        	sheet.autoSizeColumn(i);
        }
        ServletOutputStream stream = res.getOutputStream();
        wb.write(stream);
    }

	
    
}
