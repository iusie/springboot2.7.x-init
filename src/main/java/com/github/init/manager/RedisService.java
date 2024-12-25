package com.github.init.manager;

import com.github.init.common.StateCode;
import com.github.init.exception.BusinessException;
import com.github.init.model.entityDO.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.github.init.constant.UserConstant.EXPIRE_TIME;

/**
 * @author iusie
 * @description
 * @date 2024/12/9
 */
@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisService;

    // 保存token
    public void saveToken(Long userId, String token) {
        String tokenKey = "token:" + userId;
        ValueOperations<String, Object> operations = redisService.opsForValue();
        operations.set(tokenKey, token, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    // 删除token
    public boolean removeToken(Long userId) {
        String tokenKey = "token:" + userId;
        return redisService.delete(tokenKey) != null;
    }

    // 获取token
    public String getToken(Long userId) {
        String tokenKey = "token:" + userId;  // 使用相同的键格式
        ValueOperations<String, Object> operations = redisService.opsForValue();
        return (String) operations.get(tokenKey);
    }

    // 进行登录用户信息缓存
    public void UserInfoCache(Long userId, UserDO userDO) {
        String userCacheKey = "UserInfo:";
        String userKey = "UserId:" + userId;
        HashOperations<String, String, Object> operations = redisService.opsForHash();
        operations.put(userCacheKey, userKey, userDO);
        redisService.expire(userCacheKey, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    // 得到登录用户缓存信息
    public UserDO getUserInfoCache(Long userId) {
        String userCacheKey = "UserInfo:";
        String userKey = "UserId:" + userId;
        HashOperations<String, String, Object> operations = redisService.opsForHash();
        UserDO userDO = (UserDO) operations.get(userCacheKey, userKey);
        if (userId == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "用户缓存异常");
        }
        return userDO;
    }

    // 删除用户缓存信息
    public void removeUserInfoCache(Long userId) {
        String userCacheKey = "UserInfo:";
        String userKey = "UserId:" + userId;
        HashOperations<String, String, Object> operations = redisService.opsForHash();
        if (userId == null) {
            throw new BusinessException(StateCode.PARAMS_ERROR, "用户Id不存在");
        }
        // 从 Redis 删除 UserVO
        operations.delete(userCacheKey, userKey);
    }
}
