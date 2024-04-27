package com.example.file.controller;

import com.example.file.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    @Value("${user.id}")
    private String userId;
    @Value("${user.password}")
    private String password;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin( User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (user.getUserId() == null || user.getPassword() == null) {
            response.sendRedirect("/login");
        }

        if (userId.equals(user.getUserId()) && password.equals(user.getPassword())) {
            request.getSession().setAttribute("User",user);
            response.sendRedirect("/");
        }
        return "login";
    }
}
