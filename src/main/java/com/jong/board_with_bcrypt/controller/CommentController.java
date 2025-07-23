package com.jong.board_with_bcrypt.controller;

import com.jong.board_with_bcrypt.dto.CommentRequestDto;
import com.jong.board_with_bcrypt.dto.CommentResponseDto;
import com.jong.board_with_bcrypt.dto.UserRequestDto;
import com.jong.board_with_bcrypt.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponseDto create(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        return commentService.create(postId, dto);
    }

    @PutMapping("/{id}")
    public CommentResponseDto update(
            @PathVariable Long postId,
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        return commentService.update(postId, id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        commentService.delete(id, dto);
    }
}