package top.cusie.springinit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.cusie.springinit.common.BaseResponse;
import top.cusie.springinit.common.ErrorCode;
import top.cusie.springinit.common.ResultUtils;
import top.cusie.springinit.exception.BusinessException;
import top.cusie.springinit.model.dto.postthumb.PostThumbAddRequest;
import top.cusie.springinit.model.entity.User;
import top.cusie.springinit.service.PostThumbService;
import top.cusie.springinit.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 */
@RestController
@RequestMapping("/post_thumb")
@Slf4j
@RequiredArgsConstructor
public class PostThumbController {

    private final PostThumbService postThumbService;

    private final UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddRequest postThumbAddRequest,
                                         HttpServletRequest request) {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.doPostThumb(postId, loginUser);
        return ResultUtils.success(result);
    }

}
