package com.nrk.yathra.server.service;

import com.nrk.yathra.server.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl {
    @Autowired
    private AdminDAO adminDAO;

    public Boolean approvePostsByUser(int postId, Boolean status) throws Exception {
        return  approvingPostsByUser(postId,status);
    }

    private Boolean approvingPostsByUser(int postId, Boolean status) throws Exception {
        return adminDAO.approvingPostsByUser(postId,status);
    }
}
