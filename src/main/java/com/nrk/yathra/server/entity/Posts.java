package com.nrk.yathra.server.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="yathra_posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private  int  postId;
    private String postName;
    private  String postHeading;
    private String  postDescription;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id",referencedColumnName = "post_id")
    private Set<Images> postImages;
    @Column(name = "user_id")
    private  int  userId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date LastModifiedDate;
    private Integer likes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id",referencedColumnName = "post_id")
    private List<PostComments> postComments;
    @ColumnDefault("false")
    private Boolean approvedPost;


}
