package com.eirlss.auth;

import com.eirlss.model.SystemUser;
import com.eirlss.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailService implements UserDetailsService {
    @Autowired
    SystemUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> user = userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
        return user.map(LoggedInUserDetails::new).get();
    }
}
