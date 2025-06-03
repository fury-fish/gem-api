package com.gemapi.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gemapi.dto.ApiResponse;
import com.gemapi.dto.BlogDTO;
import com.gemapi.dto.PageResponse;
import com.gemapi.service.BlogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/blogs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Blog Management", description = "Admin APIs for blog management")
public class AdminBlogController {

    private final BlogService blogService;

    @PostMapping
    @Operation(summary = "Create a new blog", description = "Create a new blog post")
    public ResponseEntity<ApiResponse<BlogDTO>> createBlog(@Valid @RequestBody BlogDTO blogDTO) {
        BlogDTO createdBlog = blogService.createBlog(blogDTO);
        return ResponseEntity.ok(ApiResponse.success("Blog created successfully", createdBlog));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a blog", description = "Update an existing blog post")
    public ResponseEntity<ApiResponse<BlogDTO>> updateBlog(
            @PathVariable Long id,
            @Valid @RequestBody BlogDTO blogDTO) {
        BlogDTO updatedBlog = blogService.updateBlog(id, blogDTO);
        return ResponseEntity.ok(ApiResponse.success("Blog updated successfully", updatedBlog));
    }

    @GetMapping
    @Operation(summary = "Get all blogs", description = "Get all blogs with pagination and sorting")
    public ResponseEntity<ApiResponse<PageResponse<BlogDTO>>> getAllBlogs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(sortDirection, sortBy));
        Page<BlogDTO> blogPage = blogService.findAll(pageRequest);
        return ResponseEntity.ok(ApiResponse.success(PageResponse.of(blogPage, page)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a blog by ID", description = "Get a blog post by its ID")
    public ResponseEntity<ApiResponse<BlogDTO>> getBlogById(@PathVariable Long id) {
        BlogDTO blog = blogService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(blog));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a blog", description = "Delete a blog post by its ID")
    public ResponseEntity<ApiResponse<Void>> deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Blog deleted successfully", null));
    }

    @PatchMapping("/{id}/publish")
    @Operation(summary = "Publish/Unpublish a blog", description = "Update blog's publish status")
    public ResponseEntity<ApiResponse<BlogDTO>> updatePublishStatus(
            @PathVariable Long id,
            @RequestParam boolean published) {
        BlogDTO updatedBlog = blogService.updatePublishStatus(id, published);
        return ResponseEntity.ok(ApiResponse.success(
                published ? "Blog published successfully" : "Blog unpublished successfully",
                updatedBlog));
    }

    @DeleteMapping("/{blogId}/comments/{commentId}")
    @Operation(summary = "Delete a blog comment", description = "Delete a comment from a blog post")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long blogId,
            @PathVariable Long commentId) {
        blogService.deleteComment(blogId, commentId);
        return ResponseEntity.ok(ApiResponse.success("Comment deleted successfully", null));
    }
} 