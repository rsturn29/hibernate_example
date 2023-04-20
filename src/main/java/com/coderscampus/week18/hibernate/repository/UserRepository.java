package com.coderscampus.week18.hibernate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderscampus.week18.hibernate.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//SELECT * FROM users WHERE username = :username
	List<User> findByUsername(String username);
	
	List<User> findByName(String name);
	
	List<User> findByUsernameAndName (String username, String name);
	
	List <User> findByCreatedDateBetween(LocalDate date1, LocalDate  date2);
	
	//modified SQL syntax modified to JPQL/Hibernate;native SQL= SELECT * FROM users WHERE username = :username 
	//needs domain.classname not users from table
	@Query("SELECT u FROM User u WHERE username = :username")
	List<User> findByExactlyOneUserByUsername(String username);
	
	@Query("SELECT u FROM User u" + " LEFT JOIN FETCH u.accounts" + " LEFT JOIN FETCH u.address")
	List<User> findAll();
}
