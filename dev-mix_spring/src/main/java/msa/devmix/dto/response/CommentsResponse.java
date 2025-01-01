package msa.devmix.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import msa.devmix.dto.CommentDto;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentsResponse {

    private Long commentId;
    private Long boardId;//N:1
    private Long userId;
    private String boardTitle;
    private String userNickName;
    private String userProfileImage;
    private String content;
    private String createdAt; //Auditing Fields
    private String lastModifiedAt; //Auditing Fields

    public static CommentsResponse of(Long commentId,
                                      Long boardId,
                                      Long userId,
                                      String boardTitle,
                                      String userNickName,
                                      String userProfileImage,
                                      String content,
                                      String createdAt,
                                      String lastModifiedAt) {
        return new CommentsResponse(commentId, boardId, userId, boardTitle, userNickName, userProfileImage, content, createdAt, lastModifiedAt);
    }

    public static CommentsResponse from(CommentDto commentDto) {
        String createdAtString = commentDto.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        String lastModifiedAtString = commentDto.getLastModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        return CommentsResponse.of(
                commentDto.getCommentId(),
                commentDto.getBoardId(),
                commentDto.getUser().getId(),
                commentDto.getBoardTitle(),
                commentDto.getUser().getNickname(),
                commentDto.getUser().getProfileImage(),
                commentDto.getContent(),
                createdAtString,
                lastModifiedAtString
        );
    }
}
