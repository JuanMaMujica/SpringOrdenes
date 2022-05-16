package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

}
