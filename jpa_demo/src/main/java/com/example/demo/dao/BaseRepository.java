package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.example.demo.bean.RecordInfo;
@NoRepositoryBean
public interface BaseRepository<T extends RecordInfo> extends JpaRepository<T, Integer> {
	@Query(value="select t from #{#entityName} t where t.creator=:creator1")
	 List<T> findByCreator(@Param("creator1") String creator2);
}
