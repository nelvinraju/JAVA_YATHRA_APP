package com.nrk.yathra.server.dao;

import com.nrk.yathra.server.dto.Post;
import com.nrk.yathra.server.entity.Images;
import com.nrk.yathra.server.entity.PostComments;
import com.nrk.yathra.server.entity.Posts;
import com.nrk.yathra.server.repo.PostCommentRepository;
import com.nrk.yathra.server.repo.PostImagesRepo;
import com.nrk.yathra.server.repo.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.ImageUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PostDAO {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private PostImagesRepo postImagesRepo;
    public Post creatingNewPost(Post post) throws Exception {
        try {
            Posts posts = new Posts();
            BeanUtils.copyProperties(post,posts);
            postsRepository.save(posts);
            return post;
        }catch (Exception e){
            log.error("Error in saving new post to database.");
            throw  new Exception("Error in saving new post to database.");
        }
    }

    public List<Post> readingAllPosts() throws Exception {
        try{
           List<Posts> posts = postsRepository.findAll() ;
           List<Post> postList = new ArrayList<>();
            for(Posts eachPost:posts){
                Post post = new Post();
                BeanUtils.copyProperties(eachPost,post);
                postList.add(post);
            }
           return postList;
        }catch (Exception e){
            log.error("Error in reading all posts from database.");
            throw  new Exception("Error in reading all posts from database.");
        }
    }

    public String addingNewComment(int postId,String comment) throws Exception {
        Optional<Posts> posts = postsRepository.findById(postId);
         if(!posts.isEmpty()){
             PostComments postComments = new PostComments();
             postComments.setPostId(postId);
             postComments.setComments(comment);
             postCommentRepository.save(postComments);
             return "comment added";
         }else{
             throw new Exception("Failed to add comment");
         }
    }

    public Post updatingExsistingPost(Post post) throws Exception {
        if(postsRepository.existsById(post.getPostId())){
            Posts posts = new Posts();
            BeanUtils.copyProperties(post,posts);
            postsRepository.save(posts);
            return  post;
        }else{
            throw new Exception("Failed to update the post.");
        }
    }

    public String deletingExsistingPost(int postId) throws Exception {
        if(postsRepository.existsById(postId)){
            postsRepository.deleteById(postId);
            return  "Post deleted successfully";
        }else{
            throw new Exception("Failed to delete the post.");
        }
    }

    public Post readingSpecificPost(int postId) throws Exception {
        try{
           Optional<Posts> posts = postsRepository.findById(postId) ;
                Post post = new Post();
                BeanUtils.copyProperties(posts.get(),post);
                return post;
        }catch (Exception e){
            log.error("Error in reading all posts from database.");
            throw  new Exception("Error in reading the post from database.");
        }
    }

    public String uploadingImageForPost(Post post) throws Exception {
        try {
            Images image = new Images();
            image.setImageName(post.getImageName());
            image.setPostId(post.getPostId());
            image.setPostImage(post.getPostImage());
            if(postsRepository.existsById(post.getPostId())){
                postImagesRepo.save(image);
                return "Image uploaded";
            }

        }catch (Exception e){
            log.error("Error in saving new image for post to database.");
            throw  new Exception("Error in saving image for post to database.");
        }
        return "Image Not uploaded";
    }

    public List<Post> gettingImagesOfPost(int postId) throws Exception {
        List<Post> postList = new ArrayList<>();
        try {

            if (postsRepository.existsById(postId)) {
                List<Images> images = postImagesRepo.findAllByPostId(postId);
                for (Images image: images){
                    Post post = new Post();
                    post.setImageId(image.getImageId());
                    post.setImageName(image.getImageName());
                    post.setPostImage(ImageUtility.decompressImage(image.getPostImage()));
                    postList.add(post);
                }
                return postList;
            }
        }catch (Exception e){
            throw new Exception("Exception in fetching images");
        }
        return postList;
    }
}
