package com.example.demo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.bean.User;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, String> {
	
}
