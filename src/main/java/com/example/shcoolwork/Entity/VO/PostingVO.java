package com.example.shcoolwork.Entity.VO;

import com.example.shcoolwork.Entity.Comment;
import com.example.shcoolwork.Entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostingVO implements Serializable{
    private String abstractContent;//*

    private String detail;//*

    private List<String> images;//*

    private String avatar;//*

    private String username;//*

    private LocalDateTime createTime;//*

    private Integer likeNum;//*

    private Integer readNum;//*

    private boolean liked;

    private List<Comment> comments;//*
}
