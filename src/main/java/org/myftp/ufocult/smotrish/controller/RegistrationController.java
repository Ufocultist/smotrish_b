package org.myftp.ufocult.smotrish.controller;

import org.myftp.ufocult.smotrish.domain.Role;
import org.myftp.ufocult.smotrish.domain.User;
import org.myftp.ufocult.smotrish.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object>model){
        User userFromDn = userRepo.findByUsername(user.getUsername());

        if(userFromDn != null){
            model.put("message", "User already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
