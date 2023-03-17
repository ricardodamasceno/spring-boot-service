package utils;

import com.spring.adapters.vo.request.UserRequestVO;

public class UserMockedValues {

    public static UserRequestVO getUserRequestVO(){
        return new UserRequestVO("integration test user", "integrationuser@email.com");
    }

}
