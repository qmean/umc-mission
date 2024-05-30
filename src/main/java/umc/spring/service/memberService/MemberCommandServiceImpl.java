package umc.spring.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.member.MemberRequestDto;
import umc.spring.web.dto.member.MemberResponseDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDto.JoinMemberDto request) {
        Member member = MemberConverter.toJoinMember(request);
        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public MemberResponseDto.JoinMissionResultDto joinMission(MemberRequestDto.JoinMissionDto request) {
        if (isMemberChallengingMission(request)) {
            throw new MissionHandler(ErrorStatus.MISSION_CHALLENGING);
        }
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        memberMission.addMember(member);
        memberMission.addMission(mission);
        memberMissionRepository.save(memberMission);
        return MemberConverter.toJoinMissionResultDto(member, mission, memberMission);
    }

    @Override
    public boolean memberExistsById(Long id) {
        return memberRepository.existsById(id);
    }

    @Override
    public boolean isMemberChallengingMission(MemberRequestDto.JoinMissionDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return member.getMissions().stream()
                .anyMatch(memberMission -> {
                    Mission mission = memberMission.getMission();
                    return mission.getId().equals(request.getMissionId()) &&
                            memberMission.getStatus() == MissionStatus.CHALLENGING;
                });
    }
}
