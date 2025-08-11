package com.festival.everyday.core.security;

import com.festival.everyday.core.domain.user.User;
import lombok.Getter;

@Getter
public class UserDetailsImpl {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }
}
