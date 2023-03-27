package com.crystalloid.preparation.repository;

import com.crystalloid.preparation.model.UserDetails;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserRepository {

    public UserDetails findById(Long id) {
        return ObjectifyService.run(() -> ofy().load().type(UserDetails.class).id(id).now());
    }

    public void save(UserDetails user) {
        ObjectifyService.run(() -> ofy().save().entity(user).now());
    }
}
