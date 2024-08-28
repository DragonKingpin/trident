package com.sauron.saurye.domain.pojo;

import java.time.LocalDateTime;

public class User {

    //用户主键
    private Long id;

    //用户名
    private String username;

    //密码
    private String password;

    //昵称
    private String nickname;

    //头像
    private String avatar;

    //创建时间
    private LocalDateTime creatDateTime;

    //索引id
    private String guid;

    public User() {
    }

    public User(Long id, String username, String password, String nickname, String avatar, LocalDateTime creatDateTime, String guid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.creatDateTime = creatDateTime;
        this.guid = guid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreatDateTime() {
        return creatDateTime;
    }

    public void setCreatDateTime(LocalDateTime creatDateTime) {
        this.creatDateTime = creatDateTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", creatDateTime=" + creatDateTime +
                ", guid='" + guid + '\'' +
                '}';
    }
}
