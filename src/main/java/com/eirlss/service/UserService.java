package com.eirlss.service;

import com.eirlss.dto.ModelUser;
import com.eirlss.model.JwtResponse;
import com.eirlss.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
public interface UserService {
    HashMap<Long, ModelUser> listAll();
    HashMap<Long, ModelUser> listProfile(String user);
    void save(User user);
    User get(long id);
    ModelUser getUser(long id);
    ResponseEntity<JwtResponse> registerUser(String username, String email, String password, String firstName, String lastName,
                                             String dateOfBirth, String contact, String DrivingLicense, String Address, MultipartFile file) throws NoSuchAlgorithmException;
    User getByUserName(String username) throws NoSuchAlgorithmException;
    void delete(long id);
    boolean isValid(User user);
    HashMap<Long, User> getPendingCustomers();
    void uploadUserUtility(MultipartFile file, Long userid);
    void approveUser(Long userId, String state);

}
