package com.example.onlineclass.controller;

import com.example.onlineclass.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;
}
