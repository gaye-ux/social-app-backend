package com.socialapp.graphql;

import com.socialapp.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import com.socialapp.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserResolver {

    private final UserService userService;

    @QueryMapping
    public List<Users> getAllUsers(){return userService.getAllUsers();}

    @QueryMapping
    public Optional<Users> getUserByPhoneNo(@Argument String phoneNo){ return userService.findByPhoneNo(phoneNo);}
}
