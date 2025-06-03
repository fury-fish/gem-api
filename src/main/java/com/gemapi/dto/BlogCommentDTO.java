package com.gemapi.dto;

import com.gemapi.entity.BlogComment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogCommentDTO {
    private Long id;
    private Long blogId;
    private Long userId;
    private String userName;
    
    @NotBlank(message = "Content is required")
    private String content;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BlogCommentDTO fromEntity(BlogComment comment) {
        return BlogCommentDTO.builder()
                .id(comment.getId())
                .blogId(comment.getBlog().getId())
                .userId(comment.getUser().getId())
                .userName(comment.getUser().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
} 