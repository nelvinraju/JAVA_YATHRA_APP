package com.nrk.yathra.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="yathra_images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private  int  imageId;
    private String imageName;
    @Column(name = "post_id")
    private int postId;
    private byte[] postImage;
}
