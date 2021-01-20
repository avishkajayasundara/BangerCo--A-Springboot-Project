package com.eirlss.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.eirlss.repository.VehicleTypeRepository;
import com.eirlss.service.JWTUserDetailsService;
import com.eirlss.service.UserService;
import com.eirlss.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eirlss.dto.ModelUser;
import com.eirlss.model.User;
import com.eirlss.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
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
		List<User> userList = new ArrayList<>();
		userList.add(userrepository.findByUserName(user));
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
	public User registerUser(String username, String email, String password, String firstName, String lastName,
			String dateOfBirth, String contact, MultipartFile DrivingLicense,String Address, MultipartFile file) throws NoSuchAlgorithmException {

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
		user.setType("CUSTOMER");
		user.setAddress(Address);
		user.setStatement("done");
		user.setState("Approved");
		user.setRole("CUSTOMER");
		user.setContact(contact);
		uploadUserUtility(file,DrivingLicense,user);
		return user;
	}
	@Override
	public User getByUserName(String username) throws NoSuchAlgorithmException {

		return userrepository.findByUserName(username);

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
	public void uploadUserUtility(MultipartFile file,MultipartFile licence, User user) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String licenceName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			Path path = Paths
					.get("C:\\Users\\User\\Desktop\\EIRLS\\BangerCoEirlss\\src\\main\\webapp\\images\\licences" + fileName);
			Files.copy(licence.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			path = Paths
					.get("C:\\Users\\User\\Desktop\\EIRLS\\BangerCoEirlss\\src\\main\\webapp\\images\\utility\\" + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			user.setState("pending");
			user.setUtilityBill(fileName);
			user.setDrivingLinence(licenceName);
			save(user);

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
