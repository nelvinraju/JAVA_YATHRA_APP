package com.nrk.yathra.server.dao;

import com.nrk.yathra.server.dto.Post;
import com.nrk.yathra.server.dto.User;
import com.nrk.yathra.server.entity.Posts;
import com.nrk.yathra.server.entity.Users;
import com.nrk.yathra.server.repo.PostsRepository;
import com.nrk.yathra.server.repo.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserDAO {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PostsRepository postsRepository;
    public User registringNewuser(User user) throws Exception {
        try {
            Users users = new Users();
            BeanUtils.copyProperties(user, users);
            usersRepository.save(users);
            return user;
        }catch (Exception e){
            throw new Exception("failed to register new user.");
        }
    }

    public User updatingUserDetails(User user) throws Exception {
        if(usersRepository.existsById(user.getUserId())){
            Users users = new Users();
            BeanUtils.copyProperties(user, users);
            usersRepository.save(users);
            return user;
        }else{
            throw new Exception("failed to update user details.");
        }
    }

    public Boolean deletingUserDetails(int userId) throws Exception {
        if(usersRepository.existsById(userId)){
            usersRepository.deleteById(userId);
            return true;
        }else{
            throw new Exception("failed to delete user details.");
        }
    }

    public List<Post> gettingUserPostsDetails(int userId) throws Exception {
        try{
            List<Posts> posts = postsRepository.findAllByUserId(userId) ;
            List<Post> postList = new ArrayList<>();
            for(Posts eachPost:posts){
                Post post = new Post();
                BeanUtils.copyProperties(eachPost,post);
                postList.add(post);
            }

            return postList;
        }catch (Exception e){
            log.error("Error in reading user posts from database.");
            throw  new Exception("Error in reading user posts from database.");
        }
    }

    public List<Post> gettingOtherUsersPostsDetails(int currentUserId) throws Exception {
        try{
            List<Posts> posts = postsRepository.findAll();
            List<Post> postList = new ArrayList<>();
            for(Posts eachPost:posts){
                Post post = new Post();
                BeanUtils.copyProperties(eachPost,post);
                postList.add(post);
            }

            List<Post> postfilterdList = postList.stream()
                    .filter(post -> post.getUserId()!=currentUserId).filter(post -> post.getApprovedPost()).collect(Collectors.toList());

            return postfilterdList;

        }catch (Exception e){
            log.error("Error in reading user posts from database.",e.getMessage());
            throw new Exception("Error in reading user posts from database.");
        }
    }

    public List<Post> searchingPosts(Post post) {
        Posts posts = new Posts();
        List<Post> resultList = new ArrayList<>();
        BeanUtils.copyProperties(post,posts);
        List<Posts> listPosts = postsRepository.findByLike(post.getPostName(),post.getPostHeading());
        for(Posts eachPosts : listPosts){
            Post resultPost= new Post();
            BeanUtils.copyProperties(eachPosts,resultPost);
            resultList.add(resultPost);
        }
        return resultList;
    }
}
