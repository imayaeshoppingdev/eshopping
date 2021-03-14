package com.example.eshopping.serviceImpl;

import com.example.eshopping.dao.impl.UserDaoimpl;
import com.example.eshopping.entity.Roles;
import com.example.eshopping.entity.User;
import com.example.eshopping.entity.UserDetails;
import com.example.eshopping.repo.RoleRepository;
import com.example.eshopping.repo.UserDetailsRepository;
import com.example.eshopping.repo.UserRepository;
import com.example.eshopping.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserDaoimpl userDao;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	@Override
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<User> findAllUsers(){
		return userDao.getAllUsers();
	}
	
	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}
	
	@Override
	public List<Roles> getRoles(){
		return roleRepository.findAll();
	}
	
	@Override
	public int deleteUserById(String id) {
		return userRepository.deleteById(id);
	}
	
	public UserDetails saveUserDetails(UserDetails userDetails) {
		return userDetailsRepository.save(userDetails);
	}
	
	public UserDetails findUserDetailsById(String id) {
		return userDetailsRepository.findByUserId(id);
	}
	
	public List<UserDetails> getGstInApproval(){
		return userDao.getGstInApproval();
	}
	
	public List<User> getAadharApproval(){
		return userDao.getAadharApproval();
	}
	
	public User updateUserById(String id) {
		return userRepository.findById(id);
	}
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
}
