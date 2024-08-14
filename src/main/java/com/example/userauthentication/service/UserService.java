package com.example.userauthentication.service;

import com.example.userauthentication.entity.UserDtls;
import com.example.userauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDtls user) {
        userRepository.save(user);
    }

    public UserDtls findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	public UserDtls authenticate(Object email, Object password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserDtls authenticate(String email, String password) {
	    // Example logic for authentication
	    UserDtls user = userRepository.findByEmail(email);
	    if (user != null && user.getPassword().equals(password)) {
	        return user;
	    }
	    return null;
	}

}
