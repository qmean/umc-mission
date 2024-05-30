package umc.spring.service.memberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;

public interface MemberQueryService {
    Page<Review> getReviewResultList(Long memberId, Integer page);
    Page<MemberMission> getMissionResultLIst(Long memberId, Integer page);
    void completeMission(Long memberId, Long missionId);
    Page<MemberMission> getCompleteMissionList(Long memberId, Integer page);
}
