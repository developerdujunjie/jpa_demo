package com.example.demo.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.User;
import com.example.demo.dto.IUserDTO;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByName(String name);
	
	public List<User> findByEmailAndName(String email,String name);
	
	public List<User> findDistinctUserByNameAndEmail(String name,String email);
	
	public List<User> findUserDistinctByNameLike(String name);
	
	public List<User> findByNameLike(String name,Pageable pageable);
	@Query(value="from User u where u.email=?1")
	public Collection<User> findByEmailAddress(String email);
	@Query(value="select * from user u where u.name like ?1%",nativeQuery=true)
	public Collection<User> findByNameStartWith(String name);
	@Query(value="select * from user u where u.name like %?1",nativeQuery=true)
	public Collection<User> findByNameEndsWith(String name);
	@Query(value="select * from user u where u.name like ?1% order by ?2 desc",nativeQuery=true)
	public Collection<User> findByNameStartWithOrderById(String name,String sort);
	@Query(value="select u from User u where u.name like %?1%")
	public List<User> findByAndSort(String name,Sort sort);
	@Query(value="select u.name as username,LENGTH(u.name) as usernameLength from User u where u.name like %?1%")
	public List<IUserDTO> findByAsArryAndSort(String name,Sort sort);
	@Query(value="select u.name as username,LENGTH(u.name) as usernameLength from User u where u.name like %:name%")
	public List<IUserDTO> findByAsArryAndSortUseParm(@Param("name") String name,Sort sort);
	@Modifying
	@Transactional
	@Query(value="delete from User user where user.name=?1")
	public void deleteByName(String name);
	@Modifying
	@Transactional
	@Query(value="update User u set u.name=?2 where u.name=?1")
	public void updateUser(String oldname,String newname);
	
}
