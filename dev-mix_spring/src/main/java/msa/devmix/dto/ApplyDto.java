package msa.devmix.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import msa.devmix.domain.board.Apply;
import msa.devmix.domain.board.BoardPosition;
import msa.devmix.domain.constant.Location;
import msa.devmix.domain.constant.ParticipationStatus;
import msa.devmix.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDto {

    //연관관계 필드
    private User user; //N:1
    private Long boardId;
    private String boardTitle;
    private String positionName;
    private Location location;
    private String createdBy;
    private LocalDate recruitEndDate;

    //일반 필드
    private ParticipationStatus participationStatus;
    private String note;

    private LocalDateTime applyDate;

    public static ApplyDto of(User user, Long boardId, String positionName, ParticipationStatus participationStatus, String note) {
        return new ApplyDto(user, boardId, null, positionName, null, null, null, participationStatus, note, null);
    }

    public static ApplyDto of(User user, Long boardId, String boardTitle, String positionName, ParticipationStatus participationStatus, String note, LocalDateTime applyDate) {
        return new ApplyDto(user, boardId, boardTitle, positionName, null, null, null, participationStatus, note, applyDate);
    }

    public static ApplyDto of(Long boardId, String boardTitle, Location location,  String createdBy, LocalDate recruitEndDate) {
        return new ApplyDto(null, boardId, boardTitle, null, location, createdBy, recruitEndDate, null, null, null);
    }

    public Apply toEntity(User user, BoardPosition boardPosition) {
        return Apply.of(user, boardPosition, participationStatus, note);
    }

    public static ApplyDto from(Apply apply) {
        return ApplyDto.of(
                apply.getUser(),
                apply.getBoardPosition().getBoard().getId(),
                apply.getBoardPosition().getBoard().getTitle(),
                apply.getBoardPosition().getPosition().getPositionName(),
                apply.getParticipationStatus(),
                apply.getNote(),
                apply.getCreatedAt());
    }




}
