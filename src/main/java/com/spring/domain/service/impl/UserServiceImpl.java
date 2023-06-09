package com.spring.domain.service.impl;

import com.spring.adapters.vo.request.UserRequestVO;
import com.spring.domain.entity.User;
import com.spring.domain.exception.UserNotFoundException;
import com.spring.domain.repository.UserRepository;
import com.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User create(UserRequestVO requestVO) {
        return userRepository.save(
                new User(null, requestVO.getName(), requestVO.getEmail(), LocalDateTime.now()));
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

    }
}
