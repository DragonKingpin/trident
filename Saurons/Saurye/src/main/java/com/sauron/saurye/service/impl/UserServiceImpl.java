package com.sauron.saurye.service.impl;

import com.sauron.saurye.domain.dto.UserDTO;
import com.sauron.saurye.domain.pojo.User;
import com.sauron.saurye.mapper.UserMapper;
import com.sauron.saurye.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(UserDTO userDTO) {

        return userMapper.selectByUsernameAndPassword(userDTO);
    }
}
