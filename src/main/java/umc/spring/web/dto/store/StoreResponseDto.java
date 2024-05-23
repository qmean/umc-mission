package umc.spring.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.common.Address;

import java.time.LocalDateTime;

public class StoreResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreResponseDto {
        private Long storeId;
        private String storeName;
        private Address address;
        private LocalDateTime storeAddTime;
    }
}
