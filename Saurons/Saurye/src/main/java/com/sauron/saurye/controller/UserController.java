package com.sauron.saurye.controller;

import com.sauron.saurye.context.UserContext;
import com.sauron.saurye.domain.dto.UserDTO;
import com.sauron.saurye.domain.pojo.User;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户接口
 * @author azlml
 * @date 2024/8/21
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BasicServiceResponse<Long> login(@RequestBody UserDTO userDTO){

        User user = userService.login(userDTO);
        UserContext.setCurrentId(user.getId());
        log.info("用户id为:{}", UserContext.getCurrentId());

        return BasicServiceResponse.success(user.getId());
    }


}
