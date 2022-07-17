package com.huweicheng.thinking.in.spring.ioc.dependency.injection;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;

public class UserHolder {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
