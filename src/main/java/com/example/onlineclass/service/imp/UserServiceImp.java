package com.example.onlineclass.service.imp;

import com.example.onlineclass.props.UserProps;
import com.example.onlineclass.repository.UserRepository;
import com.example.onlineclass.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    private final CommonServiceImp commonServiceImp;
    private final UserRepository userRepository;
    private final UserProps userProps;

    public UserServiceImp(CommonServiceImp commonServiceImp, UserRepository userRepository, UserProps userProps) {
        this.commonServiceImp = commonServiceImp;
        this.userRepository = userRepository;
        this.userProps = userProps;
    }

    @Override
    public Map<String, Object> getAllUsersPage(Integer page, Integer size, String[] sort) {
        return commonServiceImp.getAllPage(page, size, sort, userRepository, userProps);
    }
}
