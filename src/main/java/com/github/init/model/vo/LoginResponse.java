package com.github.init.model.vo;

import lombok.Data;

/**
 * @author iusie
 * @description
 * @date 2024/12/25
 */
@Data
public class LoginResponse  extends UserVO{
    private String token;
}
