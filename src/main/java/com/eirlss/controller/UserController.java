package com.eirlss.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.eirlss.auth.LoggedInUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.eirlss.dto.ModelUser;
import com.eirlss.model.JwtResponse;
import com.eirlss.model.User;
import com.eirlss.service.impl.UserServiceImpl;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserServiceImpl userservice;

//	@RequestMapping(value = "/uploadUserUtility", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public String uploadUserUtility(@RequestParam("file") MultipartFile file, @RequestParam("userid") Long userid) {
//
//		userservice.uploadUserUtility(file, userid);
//
//		return "success";
//	}


	@RequestMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<ModelUser> adminUsers(Model model) {

		HashMap<Long, ModelUser> map = userservice.listAll();
		return map.values();
	}
	
	@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView getMyProfile(@AuthenticationPrincipal LoggedInUserDetails loggedInUser) throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView();
		User user = userservice.getByUserName(loggedInUser.getUsername());
		mv.setViewName("/customeraccount.jsp");
		mv.addObject("user",loggedInUser.getUsername());
		mv.addObject("profile", user);
		mv.addObject("bookings", user.getBooking());
		return mv;
	}
	
	@PostMapping(value = "/userRegister",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView reguser(@RequestParam("file") MultipartFile file,
								@RequestParam("licencefile") MultipartFile licencefile,
								@RequestParam("userName") String username, @RequestParam("email") String email,
								@RequestParam("password") String password, @RequestParam(name="firstName",required=false) String firstName,
								@RequestParam("lastName") String lastName, @RequestParam(name="dob",required=false) String dob,
								@RequestParam("mobile") String mobile,
								@RequestParam("Address") String Address) throws NoSuchAlgorithmException {

		String DrivingLicense="";
		User response = userservice.registerUser(username, email, bCryptPasswordEncoder.encode(password) , firstName, lastName, dob.toString(), mobile, licencefile, Address, file);
		ModelAndView mv = new ModelAndView();
		ModelMap model = new ModelMap();
		return new ModelAndView("redirect:/", model);
		
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
