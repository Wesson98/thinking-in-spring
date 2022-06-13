package com.huweicheng.thinking.in.spring.bean.factory;

import com.huweicheng.thinking.in.spring.ioc.overview.domain.User;

public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
