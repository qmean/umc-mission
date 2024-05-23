package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.SocialLogin;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.member.MemberRequestDto;
import umc.spring.web.dto.member.MemberResponseDto;

import java.time.LocalDateTime;

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
}
