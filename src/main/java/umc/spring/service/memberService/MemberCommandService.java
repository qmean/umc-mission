package umc.spring.service.memberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.member.MemberRequestDto;
import umc.spring.web.dto.member.MemberResponseDto;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDto.JoinMemberDto request);

    public MemberResponseDto.JoinMissionResultDto joinMission(MemberRequestDto.JoinMissionDto request);

    public boolean memberExistsById(Long id);

    public boolean isMemberChallengingMission(MemberRequestDto.JoinMissionDto request);
}
