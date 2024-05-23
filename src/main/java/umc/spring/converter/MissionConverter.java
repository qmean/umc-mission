package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDto;
import umc.spring.web.dto.mission.MissionResponseDto;

import java.util.ArrayList;

public class MissionConverter {

    public static MissionResponseDto.AddMissionResponseDto toAddResponseDto(Mission mission) {
        return MissionResponseDto.AddMissionResponseDto.builder()
                .missionId(mission.getId())
                .missionCreateTime(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(MissionRequestDto.AddMissionDto request) {
        return Mission.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .deadline(request.getDeadline())
                .reward(request.getReward())
                .missionUrl(request.getMissionUrl())
                .memberMissions(new ArrayList<>())
                .build();
    }
}
