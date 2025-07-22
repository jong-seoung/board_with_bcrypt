package com.jong.board_with_bcrypt.dto;

import com.jong.board_with_bcrypt.model.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    @NotBlank
    private String title;

    private String content;

    private UserResponseDto author;

    public static PostResponseDto fromEntity(Post post) {
        return new PostResponseDto(
                post.getTitle(),
                post.getContent(),
                UserResponseDto.fromEntity(post.getAuthor())
        );
    }
}