package com.nrk.yathra.server.service;

import com.nrk.yathra.server.dao.UserDAO;
import com.nrk.yathra.server.dto.Post;
import com.nrk.yathra.server.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User newUserRegister(User user) throws Exception {
        return registringNewuser(user);
    }

    private User registringNewuser(User user) throws Exception {
        //hashing the password into db
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userDAO.registringNewuser(user);
    }

    public User updateUserDetails(User user) throws Exception {
        return updatingUserDetails(user);
    }

    private User updatingUserDetails(User user) throws Exception {
        return userDAO.updatingUserDetails(user);

    }

    public Boolean deleteUserDetails(int userId) throws Exception {
        return deletingUserDetails(userId);
    }

    private Boolean deletingUserDetails(int userId) throws Exception {
        return userDAO.deletingUserDetails(userId);
    }

    public List<Post> getUserPostsDetails(int userId) throws Exception {
        return gettingUserPostsDetails(userId);
    }

    private List<Post> gettingUserPostsDetails(int userId) throws Exception {
        return userDAO.gettingUserPostsDetails(userId);

    }

    public List<Post> getOtherUsersPosts(int currentUserId, String sort) throws Exception {
        return gettingOtherUsersPostsDetails(currentUserId,sort);
    }

    private List<Post> gettingOtherUsersPostsDetails(int currentUserId, String sort) throws Exception {
        List<Post>  unsortedList = userDAO.gettingOtherUsersPostsDetails(currentUserId);
        List<Post>  resultList = new ArrayList<>();
        if(sort.equalsIgnoreCase("A")){
            resultList = unsortedList.stream().sorted(Comparator.comparing(Post::getCreatedDate)).collect(Collectors.toList());
        }else if(sort.equalsIgnoreCase("D")){
            resultList = unsortedList.stream().sorted(Comparator.comparing(Post::getCreatedDate).reversed()).collect(Collectors.toList());
        }else{
            resultList = unsortedList.stream().sorted(Comparator.comparing(Post::getCreatedDate)).collect(Collectors.toList());
        }

        return resultList;
    }

    public List<Post> searchPosts(Post post) {
        return searchingPosts(post);
    }

    private List<Post> searchingPosts(Post post) {
        return userDAO.searchingPosts(post);
    }
}
