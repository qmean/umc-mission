package umc.spring.service.reviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDto;

public interface reviewCommandService {
    public Review addReview(ReviewRequestDto.AddReviewDto request);
}
