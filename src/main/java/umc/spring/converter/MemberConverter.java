package umc.spring.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.SocialLogin;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberRequestDto;
import umc.spring.web.dto.member.MemberResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class MemberConverter {

    public static MemberResponseDto.JoinMemberResultDto toJoinResultDto(Member member) {
        return MemberResponseDto.JoinMemberResultDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toJoinMember(MemberRequestDto.JoinMemberDto request) {
        return Member.builder()
                .name(request.getName())
                .gender(request.getGender())
                .birth(request.getBirth())
                .address(request.getAddress())
                .socialLogin(SocialLogin.NONE)
                .build();
    }

    public static MemberResponseDto.JoinMissionResultDto toJoinMissionResultDto(Member member, Mission mission, MemberMission memberMission) {
        return MemberResponseDto.JoinMissionResultDto.builder()
                .memberId(member.getId())
                .missionId(mission.getId())
                .memberMissionId(memberMission.getId())
                .missionTime(memberMission.getCreatedAt())
                .build();
    }

    public static MemberResponseDto.MemberReviewListDto toMemberReviewListDto(Page<Review> reviewPage) {
        List<MemberResponseDto.MemberReviewDto> reviewPreiewList = reviewPage.stream()
                .map(MemberConverter::toMemberReviewDto)
                .toList();

        return MemberResponseDto.MemberReviewListDto.builder()
                .reviewPreviewList(reviewPreiewList)
                .listSize(reviewPreiewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public static MemberResponseDto.MemberReviewDto toMemberReviewDto(Review review) {
        return MemberResponseDto.MemberReviewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static MemberResponseDto.MemberMissionListDto toMemberMissionListDto(Page<MemberMission> missionPage) {
        List<MemberResponseDto.MemberMissionDto> missionDtoList = missionPage.getContent().stream()
                .map(MemberMission::getMission)
                .map(MemberConverter::toMemberMissionDto)
                .toList();
        log.info("missionDtoList: {}", missionDtoList);

        return MemberResponseDto.MemberMissionListDto.builder()
                .missionList(missionDtoList)
                .listSize(missionDtoList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MemberResponseDto.MemberMissionDto toMemberMissionDto(Mission mission) {
        return MemberResponseDto.MemberMissionDto.builder()
                .title(mission.getTitle())
                .content(mission.getContent())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .missionUrl(mission.getMissionUrl())
                .build();
    }

    public static MemberResponseDto.MemberMissionCompleteDto toMemberMissionSuccessDto(Page<MemberMission> memberMissionPage) {
        List<MemberResponseDto.MemberMissionDto> missionDtoList = memberMissionPage.getContent().stream()
                .map(MemberMission::getMission)
                .map(MemberConverter::toMemberMissionDto)
                .toList();

        return MemberResponseDto.MemberMissionCompleteDto.builder()
                .successMissionList(missionDtoList)
                .listSize(missionDtoList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

}
