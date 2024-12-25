package com.github.init.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.init.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.init.model.entityDO.UserDO;
import com.github.init.model.request.user.UpdateUserRequest;
import com.github.init.model.request.user.UserRegisterRequest;
import com.github.init.model.vo.LoginResponse;
import com.github.init.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @author admin
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-12-10 08:58:52
*/
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userRegisterRequest 注册信息实体
     * @return 返回用户id
     */
    long userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     *
     * @param userAccount 账号
     * @param userPassword 密码
     * @param response 请求对象
     * @return 用户信息
     */
    LoginResponse userLogin(String userAccount, String userPassword, HttpServletResponse response);

    /**
     * 用户注销
     *
     * @param request servlet对象
     * @return 1为退出成功
     */
    int userLogout(HttpServletRequest request);


    /**
     * 获取当前登录用户信息
     *
     * @param request
     * @return
     */
    UserDO getLoggingUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);
    boolean isAdmin(UserDO user);

    /**
     * 更新用户信息(同时更新缓存)
     * @param updateUserRequest 前端返回的数据
     * @param request
     * @return 1为更新成功，0为失败
     */
    boolean updateUser(UpdateUserRequest updateUserRequest, HttpServletRequest request);

    /**
     * 查看用户信息
     *
     * @param queryById id实体
     * @param loggingUser 返回的用户数据
     * @return User
     */
    UserDO getUserInfoById(Long queryById, UserDO loggingUser);

    /**
     * 用户搜索
     *
     * @param userAccount 搜索实体
     * @param loggingUser 返回的用户数据
     * @return List<User>
     */
    List<UserVO> searchUsers(String userAccount , String userName, UserDO loggingUser);

    /**
     * 分页获取用户列表（仅管理员）
     *
     * @return Wrapper<User>
     */
    Wrapper<User> getQueryWrapper();

}
