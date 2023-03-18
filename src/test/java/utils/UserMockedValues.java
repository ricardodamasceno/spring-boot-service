package utils;

import com.spring.adapters.vo.request.UserRequestVO;
import com.spring.domain.entity.User;

import java.time.LocalDateTime;

public class UserMockedValues {

    public static UserRequestVO getUserRequestVO(){
        return new UserRequestVO("integration test user", "integrationuser@email.com");
    }

    public static User getMockedUser(){
        User user = new User();
        user.setName("User Test");
        user.setEmail("usertest@email.com");
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

}
