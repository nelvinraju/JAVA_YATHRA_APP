package com.nrk.yathra.server.repo;

import com.nrk.yathra.server.dto.Post;
import com.nrk.yathra.server.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Integer> , JpaSpecificationExecutor<Posts> {
    List<Posts> findAllByUserId(int userId);


    @Query("SELECT u FROM Posts u WHERE (u.postName like '%' || :postName" +
            " || '%' or :postName is null) and (u.postHeading like '%' || :postHeading || '%' or :postHeading is null)")
    List<Posts> findByLike(@Param("postName") String postName ,
                           @Param("postHeading") String postHeading
                           );
}
