package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDto;
import umc.spring.web.dto.review.ReviewResponseDto;

public class ReviewConverter {

    public static ReviewResponseDto.AddReviewResponseDto toAddResponseDto(Review review) {
        return ReviewResponseDto.AddReviewResponseDto.builder()
                .reviewId(review.getId())
                .reviewDate(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDto.AddReviewDto request) {
        return Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .build();
    }
}
