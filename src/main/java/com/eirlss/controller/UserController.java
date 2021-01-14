package com.eirlss.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eirlss.dto.ModelUser;
import com.eirlss.model.JwtResponse;
import com.eirlss.model.User;
import com.eirlss.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserServiceImpl userservice;

	@RequestMapping(value = "/uploadUserUtility", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadUserUtility(@RequestParam("file") MultipartFile file, @RequestParam("userid") Long userid) {

		userservice.uploadUserUtility(file, userid);

		return "success";
	}
	
	@RequestMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<ModelUser> adminUsers(Model model) {

		HashMap<Long, ModelUser> map = userservice.listAll();
		return map.values();
	}
	
	@RequestMapping(value = "/getMyProfile", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getMyProfile(@RequestBody  User user) throws NoSuchAlgorithmException {
		
		return userservice.get(user.getUserid());
	}
	
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> reguser(@RequestParam("file") MultipartFile file,
			@RequestParam("userName") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("mobile") String mobile, @RequestParam("DrivingLicense") String DrivingLicense,
			@RequestParam("Address") String Address) throws NoSuchAlgorithmException {

		ResponseEntity<JwtResponse> response = userservice.registerUser(username, email, bCryptPasswordEncoder.encode(password) , firstName, lastName, dateOfBirth, mobile, DrivingLicense, Address, file);
		return response;
		
	}
	
	@RequestMapping(value = "/blacklistUser", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public String blacklistUser(@RequestBody User user) {
		
		User u = userservice.get(user.getUserid());
		u.setState("Blacklisted");
		userservice.save(u);
		return "Success";
		
	}
//	@RequestMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Collection<ModelUser> viewCustomers(Model model) {
//
//		HashMap<Long, ModelUser> map = userservice.listAll();
//		return map.values();
//	}
}
