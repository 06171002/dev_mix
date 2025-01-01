package msa.devmix.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import msa.devmix.domain.board.Board;
import msa.devmix.domain.board.Comment;
import msa.devmix.domain.user.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private Long boardId; //N:1
    private String boardTitle;
    private User user; //N:1, UserDto 대신 User 사용
    private String content;
    private LocalDateTime createdAt; //Auditing Fields
    private LocalDateTime lastModifiedAt; //Auditing Fields
    private String userProfileImage;

    public static CommentDto of(Long boardId, User user, String content) {
        return CommentDto.of(null, boardId, user, content, null, null);
    }

    public static CommentDto of(Long commentId, Long boardId, User user, String content) {
        return CommentDto.of(commentId, boardId, user, content, null, null);
    }

    public static CommentDto of(Long commentId, Long boardId, User user, String content, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        return CommentDto.of(commentId, boardId,null, user, content, createdAt, lastModifiedAt, null);
    }

    public static CommentDto of(Long commentId, Long boardId, String boardTitle, User user, String content, LocalDateTime createdAt, LocalDateTime lastModifiedAt, String userProfileImage) {
        return new CommentDto(commentId, boardId, boardTitle, user, content, createdAt, lastModifiedAt, userProfileImage);
    }

    public static CommentDto from(Comment comment) {
        return CommentDto.of(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getBoard().getTitle(),
                comment.getUser(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getLastModifiedAt(),
                comment.getUser().getProfileImage()
        );
    }

    public Comment toEntity(Board board, User user) {
        return Comment.of(
                board,
                user,
                content
        );
    }
}
