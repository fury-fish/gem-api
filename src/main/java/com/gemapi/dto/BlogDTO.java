package com.gemapi.dto;

import com.gemapi.entity.Blog;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Content is required")
    private String content;
    
    private Long authorId;
    private String authorName;
    private String imageUrl;
    private boolean published;
    private Integer viewCount;
    private List<BlogCommentDTO> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BlogDTO fromEntity(Blog blog) {
        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .authorId(blog.getAuthor().getId())
                .authorName(blog.getAuthor().getName())
                .imageUrl(blog.getImageUrl())
                .published(blog.isPublished())
                .viewCount(blog.getViewCount())
                .comments(blog.getComments().stream()
                        .map(BlogCommentDTO::fromEntity)
                        .collect(Collectors.toList()))
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }
} 