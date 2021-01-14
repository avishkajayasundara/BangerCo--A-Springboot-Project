package com.eirlss.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.eirlss.model.VehicleType;
import com.eirlss.repository.VehicleTypeRepository;
import com.eirlss.service.JWTUserDetailsService;
import com.eirlss.service.UserService;
import com.eirlss.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eirlss.config.JwtTokenUtil;
import com.eirlss.dto.ModelUser;
import com.eirlss.model.JwtResponse;
import com.eirlss.model.User;
import com.eirlss.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JWTUserDetailsService userDetailsService;

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private VehicleTypeRepository repo;


	@Override
	public HashMap<Long, ModelUser> listAll() {
		List<User> userList = userrepository.findAll();
		return UserMapper.userListToModelUserMap(userList);
	}

	public HashMap<Long, ModelUser> listProfile(String user) {

		HashMap<Long, ModelUser> map = new HashMap<>();
		List<User> userList = userrepository.findUserByuserNAme(user);
		return UserMapper.userListToModelUserMap(userList);
	}
	@Override
	public void save(User user) {
		userrepository.save(user);
	}
	@Override
	public User get(long id) {
		return userrepository.findById(id).get();
	}

	@Override
	public ModelUser getUser(long id) {
		User user = userrepository.findById(id).get();
		return UserMapper.userToModelUser(user);
	}
	@Override
	public ResponseEntity<JwtResponse> registerUser(String username, String email, String password, String firstName, String lastName,
			String dateOfBirth, String contact, String DrivingLicense,String Address, MultipartFile file) throws NoSuchAlgorithmException {

		User user = new User();
		user.setUserName(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		LocalDate date = LocalDate.parse(dateOfBirth);// converting string into sql date
		user.setDateOfBirth(date);
		user.setStatement(file.getOriginalFilename());
		user.setPassword(password);
		user.setDrivingLinence(DrivingLicense);
		user.setType("CUSTOMER");
		user.setAddress(Address);
		user.setStatement("done");
		user.setState("Approved");

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// user.setDrivingLinence(fileName);

		user.setContact(contact);
		user.setStatement(fileName);
		try {
			Path path = Paths.get("C:\\Users\\User\\Desktop\\EIRLS\\BangerCoEirlss\\src\\asset\\images" + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// passwordEncoder.encode(user.getPassword())
		save(user);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (userDetails != null) {
			final String token = jwtTokenUtil.generateToken(userDetails);

			User loginUser = getByUserName(username);
			Long userId = loginUser.getUserid();
			return ResponseEntity.ok(new JwtResponse(token, username, "customer", firstName, lastName, userId,
					user.getDrivingLinence(), user.getState()));
		}

		return ResponseEntity.ok(new JwtResponse("", "", "", "", "", null, "", ""));
	}
	@Override
	public User getByUserName(String username) throws NoSuchAlgorithmException {

		List<User> userlist = userrepository.findUserByuserNAme(username);
		if (!userlist.isEmpty()) {
			User returnUser = userlist.get(0);
			return returnUser;
		}

		return null;

	}

	@Override
	public void delete(long id) {
		userrepository.deleteById(id);
	}
	@Override
	public boolean isValid(User user) {
		return user != null;
	}
	@Override
	public HashMap<Long, User> getPendingCustomers() {

		HashMap<Long, User> map = new HashMap<>();
		List<User> userList = userrepository.getPendingUsers();
		for (User u : userList) {

			map.put(u.getUserid(), u);
		}

		return map;

	}
	@Override
	public void uploadUserUtility(MultipartFile file, Long userid) {
		Optional<User> userOpt = userrepository.findById(userid);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			Path path = Paths
					.get("C:/Users/Keshini/Downloads/BangerCoKeshini/src/asset/images/license" + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			user.setState("pending");
			user.setUtilityBill(fileName);
			userrepository.save(user);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void approveUser(Long userId, String state) {
		Optional<User> userOpt = userrepository.findById(userId);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		user.setState(state);
		userrepository.save(user);
	}

}
