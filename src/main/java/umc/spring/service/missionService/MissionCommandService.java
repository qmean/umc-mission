package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDto;

public interface MissionCommandService {
    public Mission addMission(MissionRequestDto.AddMissionDto request);

    public boolean existMissionById(Long id);
}
