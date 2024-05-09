package umc.spring.domain.mapping;

import jakarta.persistence.*;
import umc.spring.domain.Cond;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;

@Entity
@Table(name = "member_cond")
public class MemberCond extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "cond_id")
    private Cond cond;
}
