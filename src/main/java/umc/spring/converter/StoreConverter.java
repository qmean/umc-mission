package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Review toReview(StoreRequestDto.ReviewDto request) {
        return Review.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }

    public static StoreResponseDto.CreateReviewResultDto toCreateReviewResultDto(Review review) {
        return StoreResponseDto.CreateReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDto.ReviewPreViewDto reviewPreViewDto(Review review) {
        return StoreResponseDto.ReviewPreViewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();

    }

    public static StoreResponseDto.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList) {
        List<StoreResponseDto.ReviewPreViewDto> reviewPreViewDtoList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDto)
                .collect(Collectors.toList());

        return StoreResponseDto.ReviewPreViewListDto.builder()
                .reviewPreViewList(reviewPreViewDtoList)
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDtoList.size())
                .build();
    }

    public static StoreResponseDto.StoreMissionListDto toStoreMissionListDto(Page<Mission> missionList) {
        List<StoreResponseDto.StoreMissionDto> missionDtoList = missionList.map(StoreConverter::toStoreMissionDto).toList();

        return StoreResponseDto.StoreMissionListDto.builder()
                .missionList(missionDtoList)
                .listSize(missionDtoList.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }

    public static StoreResponseDto.StoreMissionDto toStoreMissionDto(Mission mission) {
        return StoreResponseDto.StoreMissionDto.builder()
                .title(mission.getTitle())
                .content(mission.getContent())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .missionUrl(mission.getMissionUrl())
                .build();
    }
}
