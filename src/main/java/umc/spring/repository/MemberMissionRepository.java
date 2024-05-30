package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @EntityGraph(attributePaths = {"mission"})
    Page<MemberMission> findAllByMemberAndStatusEquals(Member member, MissionStatus missionStatus, PageRequest pageRequest);

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);
}
