package com.nrk.yathra.server.service;

import com.nrk.yathra.server.dao.PostDAO;
import com.nrk.yathra.server.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostServiceImpl {
    @Autowired
    private PostDAO postDAO;

    public Post createNewPost(Post post) throws Exception {
        return  creatingNewPost(post);
    }
    private Post creatingNewPost(Post post) throws Exception {
      return  postDAO.creatingNewPost(post);
    }

    public List<Post> readAllPosts() throws Exception {
        return  readingAllPosts();
    }

    private List<Post> readingAllPosts() throws Exception {
        return  postDAO.readingAllPosts();
    }

    public String addNewComment(int postId,String comment) throws Exception {
        return  addingNewComment(postId,comment);

    }

    private String addingNewComment(int postId,String comment) throws Exception {
        return  postDAO.addingNewComment(postId,comment);
    }

    public Post updatePost(Post post) throws Exception {
        return  updatingExsistingPost(post);
    }

    private Post updatingExsistingPost(Post post) throws Exception {
        return  postDAO.updatingExsistingPost(post);
    }

    public String deletePost(int postId) throws Exception {
        return  deletingExsistingPost(postId);

    }

    private String deletingExsistingPost(int postId) throws Exception {
        return  postDAO.deletingExsistingPost(postId);
    }

    public Post readSpecificPost(int postId) throws Exception {
        return  readingSpecificPost(postId);
    }

    private Post readingSpecificPost(int postId) throws Exception {
        return  postDAO.readingSpecificPost(postId);
    }

    public String uploadImageForPost(Post post) throws Exception {
        return uploadingImageForPost(post);
    }

    private String uploadingImageForPost(Post post) throws Exception {
        return  postDAO.uploadingImageForPost(post);
    }

    public List<Post> readAllImagesOfPost(int postId) throws Exception {
        return gettingImagesOfPost(postId);
    }

    private List<Post> gettingImagesOfPost(int postId) throws Exception {
        return  postDAO.gettingImagesOfPost(postId);
    }
}
