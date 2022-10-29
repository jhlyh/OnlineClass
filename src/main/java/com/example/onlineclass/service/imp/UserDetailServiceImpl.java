package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.User;
import com.example.onlineclass.repository.UserRepository;
import com.example.onlineclass.service.UserDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(
                    "User " + username + "not found"
            );
        }
    }
}
