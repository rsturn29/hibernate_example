package com.coderscampus.week18.hibernate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.week18.hibernate.domain.Account;
import com.coderscampus.week18.hibernate.domain.User;
import com.coderscampus.week18.hibernate.repository.AccountRepository;
import com.coderscampus.week18.hibernate.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accountRepo;
//	public List<User> findByUsername(String username){
//		return userRepo.findByUsername(username);
//	}
//	public List<User> findByUsernameAndName(String username, String name){
//		return userRepo.findByUsernameAndName(name, username);
//	}
//	public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2){
//		return userRepo.findByCreatedDateBetween(date1, date2);
//	}
//	public User findExactlyOneUserByUsername(String username) {
//		List<User> users = userRepo.findByExactlyOneUserByUsername(username);
//		if(users.size()> 0)
//			return users.get(0);
//		else
//		return new User();
//		
//	}
	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}
//repetitive code so add new Method into to account Service 
	public User saveUser(User user) {
		if (user.getUserId() == null) {
			Account checking = new Account();
			checking.setAccountName("Checking Account");
			checking.getUsers().add(user);
			Account savings = new Account();
			savings.setAccountName("Savings Account");
			savings.getUsers().add(user);
			user.getAccounts().add(checking);
			user.getAccounts().add(savings);
			accountRepo.save(checking);
			accountRepo.save(savings);
		}
		return userRepo.save(user);

	}

	public void delete(Long userId) {
		userRepo.deleteById(userId);
		
	}
}
