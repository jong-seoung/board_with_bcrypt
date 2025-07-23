package com.jong.board_with_bcrypt.dto;

import com.jong.board_with_bcrypt.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private UserResponseDto author;

    public static CommentResponseDto fromEntity(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                UserResponseDto.fromEntity(comment.getAuthor())
        );
    }
}