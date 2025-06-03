package com.gemapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gemapi.dto.BlogDTO;
import com.gemapi.entity.Blog;
import com.gemapi.entity.BlogComment;
import com.gemapi.entity.User;
import com.gemapi.repository.BlogCommentRepository;
import com.gemapi.repository.BlogRepository;
import com.gemapi.repository.UserRepository;
import com.gemapi.service.BlogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final BlogCommentRepository blogCommentRepository;
    private final UserRepository userRepository;

    @Override
    public BlogDTO createBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        updateBlogFromDTO(blog, blogDTO);
        return BlogDTO.fromEntity(blogRepository.save(blog));
    }

    @Override
    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
        updateBlogFromDTO(blog, blogDTO);
        return BlogDTO.fromEntity(blogRepository.save(blog));
    }

    @Override
    public BlogDTO findById(Long id) {
        return BlogDTO.fromEntity(blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id)));
    }

    @Override
    public Page<BlogDTO> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable).map(BlogDTO::fromEntity);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public BlogDTO updatePublishStatus(Long id, boolean published) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
        blog.setPublished(published);
        return BlogDTO.fromEntity(blogRepository.save(blog));
    }

    @Override
    public void deleteComment(Long blogId, Long commentId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));
        BlogComment comment = blogCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        
        if (!comment.getBlog().getId().equals(blogId)) {
            throw new RuntimeException("Comment does not belong to the specified blog");
        }
        
        blogCommentRepository.delete(comment);
    }

    private void updateBlogFromDTO(Blog blog, BlogDTO blogDTO) {
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        blog.setImageUrl(blogDTO.getImageUrl());
        blog.setPublished(blogDTO.isPublished());
        
        if (blogDTO.getAuthorId() != null) {
            User author = userRepository.findById(blogDTO.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + blogDTO.getAuthorId()));
            blog.setAuthor(author);
        }
    }
} 