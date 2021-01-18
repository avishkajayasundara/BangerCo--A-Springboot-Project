package com.eirlss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eirlss.config.JwtTokenUtil;
import com.eirlss.model.JwtRequest;
import com.eirlss.model.JwtResponse;
import com.eirlss.model.User;
import com.eirlss.service.JWTUserDetailsService;
import com.eirlss.service.impl.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:19002/","exp://192.168.1.4:19000"})
public class JwtAuthenticationController {

	@Autowired
	private UserServiceImpl userservice;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JWTUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		
		User loginUser = userservice.getByUserName(authenticationRequest.getUsername());
		String userName = loginUser.getUserName();
		String type = loginUser.getType();
		String firstName = loginUser.getFirstName();
		String lastName = loginUser.getLastName();
		Long userId = loginUser.getUserid();
		String drivingLicense = loginUser.getDrivingLinence();
		String state = loginUser.getState();
			
		return ResponseEntity.ok(new JwtResponse(token,userName,type,firstName,lastName,userId,drivingLicense,state));

		//return ResponseEntity.ok(new JwtResponse(token,"","","",""));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}