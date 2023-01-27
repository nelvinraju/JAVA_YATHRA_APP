package com.nrk.yathra.server.repo;

import com.nrk.yathra.server.entity.Images;
import com.nrk.yathra.server.entity.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImagesRepo extends JpaRepository<Images, Integer> {

    List<Images> findAllByPostId(int postId);
}
