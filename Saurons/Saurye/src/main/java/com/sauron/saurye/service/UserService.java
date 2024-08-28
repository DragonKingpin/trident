package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.UserDTO;
import com.sauron.saurye.domain.pojo.User;

public interface UserService {


    /**
     * 登录
     * @param userDTO
     * @return
     */
    User login(UserDTO userDTO);
}
