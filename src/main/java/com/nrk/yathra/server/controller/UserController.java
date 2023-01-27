package com.nrk.yathra.server.controller;

import com.nrk.yathra.server.dto.Post;
import com.nrk.yathra.server.dto.User;
import com.nrk.yathra.server.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User")
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/newUserRegister")
    public User newUserRegister(@RequestBody User user) throws Exception {
        return userServiceImpl.newUserRegister(user);
    }
    @PostMapping("/updateUserDetails")
    public User updateUserDetails(@RequestBody User user) throws Exception {
        return userServiceImpl.updateUserDetails(user);
    }

    @DeleteMapping("/deleteUserDetails/{userId}")
    public Boolean deleteUserDetails(@PathVariable int userId) throws Exception {
        return userServiceImpl.deleteUserDetails(userId);
    }

    @GetMapping("/userPosts/{userId}")
    public List<Post> getUserPostsDetails(@PathVariable int userId) throws Exception {
        return userServiceImpl.getUserPostsDetails(userId);
    }
    @GetMapping("/otherUsersPosts/{currentUserId}")
    public List<Post> getOtherUsersPosts(@PathVariable int currentUserId, @RequestParam(required = false) String sort) throws Exception {
        return userServiceImpl.getOtherUsersPosts(currentUserId,sort);
    }

    @GetMapping("/searchPosts")
    public List<Post> searchPosts(@RequestBody(required = false)  Post post) throws Exception {
        return userServiceImpl.searchPosts(post);
    }
}
