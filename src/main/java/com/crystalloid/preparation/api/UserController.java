package com.crystalloid.preparation.api;

import com.crystalloid.preparation.model.UserDetails;
import com.crystalloid.preparation.service.UserService;
import com.google.api.auth.UnauthenticatedException;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.inject.Inject;

@Api(name = "user", canonicalName = "User Api", version = "v1", description = "User api management")
public class UserController {

    @Inject
    public UserService userService;

    @ApiMethod(name = "saveUser", httpMethod = ApiMethod.HttpMethod.POST)
    public void saveUser(UserDetails user) {
        userService.save(user);
    }

    @ApiMethod(name = "getUserById", path = "getUserById/{id}", httpMethod = ApiMethod.HttpMethod.GET)
    public UserDetails getUserById(@Named("id") Long id, User auth) {
        if (auth == null) {
            throw new UnauthenticatedException();
        }
        return userService.getUserById(id);
    }
}
