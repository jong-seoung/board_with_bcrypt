package com.jong.board_with_bcrypt.service;

import com.jong.board_with_bcrypt.dto.CommentRequestDto;
import com.jong.board_with_bcrypt.dto.CommentResponseDto;
import com.jong.board_with_bcrypt.dto.UserRequestDto;
import com.jong.board_with_bcrypt.model.Comment;
import com.jong.board_with_bcrypt.model.Post;
import com.jong.board_with_bcrypt.model.User;
import com.jong.board_with_bcrypt.repository.CommentRepository;
import com.jong.board_with_bcrypt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto create(Long postId, CommentRequestDto dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post를 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(dto.getContent());

        return CommentResponseDto.fromEntity(commentRepository.save(comment));
    }

    public CommentResponseDto update(Long postId, Long commentId, CommentRequestDto dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post를 찾을 수 없습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment를 찾을 수 없습니다."));

        if(!user.getUsername().equals(comment.getAuthor().getUsername())) {
            throw new RuntimeException("다른 유저의 댓글은 삭제할 수 없습니다.");
        }

        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(dto.getContent());

        return CommentResponseDto.fromEntity(commentRepository.save(comment));
    }

    public void delete(Long id, UserRequestDto dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 댓글을 찾을 수 없습니다."));

        if(!user.getUsername().equals(comment.getAuthor().getUsername())) {
            throw new RuntimeException("다른 유저의 댓글은 삭제할 수 없습니다.");
        }

        commentRepository.deleteById(id);
    }
}
