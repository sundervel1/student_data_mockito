package com.cg.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.dto.UserDetails;

public interface LoginDao extends JpaRepository<UserDetails, String>{

}
