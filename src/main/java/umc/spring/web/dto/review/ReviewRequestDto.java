package umc.spring.web.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDto {
    @Getter
    public static class AddReviewDto {
        @ExistMember
        private Long memberId;
        @NotBlank
        private String content;
        @Max(5)
        @Min(1)
        @NotNull
        private Integer rating;
        @ExistStore
        private Long storeId;
    }
}
