package com.spring.adapters.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestVO {

    @NonNull
    private String name;

    @NonNull
    private String email;

}
