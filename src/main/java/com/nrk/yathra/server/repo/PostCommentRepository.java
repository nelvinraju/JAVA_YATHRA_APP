package com.nrk.yathra.server.repo;

import com.nrk.yathra.server.entity.PostComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComments, Integer> {

}
