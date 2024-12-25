package com.github.init.controller;

import com.github.init.common.BaseResponse;
import com.github.init.common.ResultUtils;
import com.github.init.common.StateCode;
import com.github.init.exception.BusinessException;
import com.github.init.model.request.user.UpdateUserRequest;
import com.github.init.model.request.user.UserLoginRequest;
import com.github.init.model.request.user.UserRegisterRequest;
import com.github.init.model.vo.LoginResponse;
import com.github.init.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author iusie
 * @description
 * @date 2024/12/25
 */

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "不能提交空表单");
        }
        long userRegister = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(userRegister);
    }

    @PostMapping("/login")
    public BaseResponse<LoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        if (userLoginRequest == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "请求头为空");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "账号或密码为空");
        }
        LoginResponse result = userService.userLogin(userAccount, userPassword, response);
        return ResultUtils.success(result);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        int logout = userService.userLogout(request);
        return ResultUtils.success(logout);
    }

    @PutMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest request) {
        if (updateUserRequest == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR);
        }
        boolean result = userService.updateUser(updateUserRequest, request);
        return ResultUtils.success(result);
    }


}
