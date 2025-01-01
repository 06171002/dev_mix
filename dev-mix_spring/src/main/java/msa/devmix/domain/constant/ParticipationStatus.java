package msa.devmix.domain.constant;

import lombok.Getter;
import msa.devmix.exception.CustomException;
import msa.devmix.exception.ErrorCode;

import java.util.Arrays;

@Getter
public enum ParticipationStatus {
    YES("승인"), NO("거절"), UNKNOWN("미정");

    private String value;

    ParticipationStatus(String value) {
        this.value = value;
    }

    public static String getValue(String value) {
        return ParticipationStatus.valueOf(value).getValue();
    }

    public static ParticipationStatus getParticipationStatus(String value) {
        return Arrays.stream(ParticipationStatus.values())
                .filter(participationStatus -> participationStatus.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.PARTICIPATION_STATUS_NOT_FOUND));
    }
}
