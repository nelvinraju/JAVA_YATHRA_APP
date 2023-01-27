package com.nrk.yathra.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Post extends PostProperties{
    @JsonInclude(NON_DEFAULT)
    private  int  postId;
    private String postName;
    private  String postHeading;
    private String  postDescription;
    private Boolean approvedPost;
    @JsonInclude(NON_DEFAULT)
    private  int  imageId;
    private String imageName;
    private byte[] postImage;

}
