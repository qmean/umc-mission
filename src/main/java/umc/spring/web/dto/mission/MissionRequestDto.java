package umc.spring.web.dto.mission;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDateTime;

public class MissionRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddMissionDto {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotNull
        private LocalDateTime deadline;
        @Min(0)
        private Integer reward;
        @URL
        private String missionUrl;
        @ExistStore
        private Long storeId;
    }
}
