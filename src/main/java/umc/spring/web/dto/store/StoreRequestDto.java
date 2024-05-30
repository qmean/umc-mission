package umc.spring.web.dto.store;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.common.Address;

public class StoreRequestDto {
    @Getter
    public static class AddStoreRequestDto {
        @NotBlank
        private String name;
        @Valid
        @NotNull
        private Address address;
    }

    @Getter
    public static class ReviewDto {
        private Integer rating;
        private String content;
    }
}
