package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

import java.util.ArrayList;

public class StoreConverter {

    public static Store toStore(StoreRequestDto.AddStoreRequestDto request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .missions(new ArrayList<>())
                .reviews(new ArrayList<>())
                .build();
    }

    public static StoreResponseDto.AddStoreResponseDto toAddResponseDto(Store store) {
        return StoreResponseDto.AddStoreResponseDto.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .address(store.getAddress())
                .storeAddTime(store.getCreatedAt())
                .build();
    }
}
