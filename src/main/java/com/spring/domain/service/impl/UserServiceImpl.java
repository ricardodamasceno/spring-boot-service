package com.spring.domain.service.impl;

import com.spring.adapters.vo.request.UserRequestVO;
import com.spring.domain.entity.User;
import com.spring.domain.exception.UserNotFoundException;
import com.spring.domain.repository.UserRepository;
import com.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User create(UserRequestVO requestVO) {
        return userRepository.save(new User(null, requestVO.getName(), requestVO.getEmail()));
    }

    public User findById(String id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new UserNotFoundException("User not found");
        }
    }
}
