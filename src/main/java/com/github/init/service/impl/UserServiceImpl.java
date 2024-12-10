package com.github.init.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.init.model.entity.User;
import com.github.init.service.UserService;
import com.github.init.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-12-10 08:58:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




