package umc.spring.web.dto.mission;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDateTime;

public class MissionRequestDto {
    @Getter
    public static class AddMissionDto {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotNull
        private LocalDateTime deadline;
        @Min(0)
        @NotNull
        private Integer reward;
        @URL
        @NotNull
        private String missionUrl;
        @ExistStore
        private Long storeId;
    }
}
