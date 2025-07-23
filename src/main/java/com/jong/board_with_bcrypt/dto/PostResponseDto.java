package com.jong.board_with_bcrypt.dto;


import com.jong.board_with_bcrypt.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private UserResponseDto author;
    private List<CommentResponseDto> comments;

    public static PostResponseDto fromEntity(Post post) {
        List<CommentResponseDto> commentResponseDtos = post.getComments()
                .stream()
                .map(CommentResponseDto::fromEntity)
                .toList();

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                UserResponseDto.fromEntity(post.getAuthor()),
                commentResponseDtos
        );
    }
}