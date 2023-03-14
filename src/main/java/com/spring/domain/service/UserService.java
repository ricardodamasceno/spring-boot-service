package com.spring.domain.service;

import com.spring.adapters.vo.request.UserRequestVO;
import com.spring.domain.entity.User;

public interface UserService {

    User create(UserRequestVO requestVO);
    User findById(String id);

}
