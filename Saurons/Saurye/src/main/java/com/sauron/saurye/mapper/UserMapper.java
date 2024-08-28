package com.sauron.saurye.mapper;

import com.sauron.saurye.domain.dto.UserDTO;
import com.sauron.saurye.domain.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from trident_user_info where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(UserDTO userDTO);
}
