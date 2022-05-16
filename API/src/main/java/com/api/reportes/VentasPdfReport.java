package com.api.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.api.model.Bill;
import com.api.model.BillDetails;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class VentasPdfReport {
	private List<BillDetails> billDetails;
    private List<Bill> bills;
    
    public VentasPdfReport(List<Bill> bills, List<BillDetails> billDetails)
    {
    	this.billDetails = billDetails;
        this.bills = bills;
    }

    public void HeaderTable(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.cyan);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Id Cliente"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nombre"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Producto"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad"));
        table.addCell(cell);

    }
    public void BodyTable(PdfPTable table)
    {
        for(Bill b: bills)
        {
        	for(BillDetails bd: billDetails) {
        		if(bd.getBill().getBillNumber() == b.getBillNumber()) {
        			table.addCell(String.valueOf(b.getClient().getId()));
        			table.addCell(b.getClient().getName());
        			table.addCell(bd.getProduct().getName());
        			table.addCell(String.valueOf(bd.getAmmount()));        			
        		}
        	}
        }
    }
    public void export(HttpServletResponse hsr) throws DocumentException, IOException
    {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, hsr.getOutputStream());
        doc.open();
        Paragraph paragraph = new Paragraph("Ventas List");
        paragraph.setSpacingAfter(5);
        paragraph.setAlignment(paragraph.ALIGN_CENTER);
        doc.add(paragraph);
        PdfPTable pdf = new PdfPTable(4);
        HeaderTable(pdf);
        BodyTable(pdf);
        doc.add(pdf);
        doc.close();
    }

}
