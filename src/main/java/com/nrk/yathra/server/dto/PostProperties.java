package com.nrk.yathra.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PostProperties {
    private Date createdDate;
    private Date LastModifiedDate;
    private Integer likes;
    private List<String> comments;
    private int userId;
}
