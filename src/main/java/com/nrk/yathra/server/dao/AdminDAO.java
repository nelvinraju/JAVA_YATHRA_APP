package com.nrk.yathra.server.dao;

import com.nrk.yathra.server.entity.Posts;
import com.nrk.yathra.server.repo.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AdminDAO {
    @Autowired
    private PostsRepository postsRepository;
    public Boolean approvingPostsByUser(int postId, Boolean status) throws Exception {
        Optional<Posts> posts= postsRepository.findById(postId);
        if(!posts.isEmpty()) {
            if(status !=null && status ==false){
                posts.get().setApprovedPost(false);
            }else {
                posts.get().setApprovedPost(true);
            }
            postsRepository.save(posts.get());
            return true;
        }else{
            throw new Exception("Failed to approve the post");
        }
    }
}
