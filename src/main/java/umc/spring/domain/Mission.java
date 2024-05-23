package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime deadline;

    @Column(nullable = false)
    private int reward;

    @Column(columnDefinition = "TEXT")
    private String missionUrl;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
