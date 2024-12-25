package com.github.init.model.request.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author iusie
 * @description 登录请求体
 * @date 2024/11/23
 */
@Data
public class UserLoginRequest  implements Serializable {

    private static final long serialVersionUID = -7019882044768349706L;
    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
