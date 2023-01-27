package com.nrk.yathra.server.controller;

import com.nrk.yathra.server.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @PostMapping("/approvePost/{postId}")
    public Boolean approvePostsByUser(@PathVariable int postId , @RequestParam(required = false) Boolean status) throws Exception {
        return adminServiceImpl.approvePostsByUser(postId,status);
    }
}
