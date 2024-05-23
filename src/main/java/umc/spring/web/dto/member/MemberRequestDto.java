package umc.spring.web.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.common.Address;
import umc.spring.domain.enums.Gender;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.NotChallengingMission;

import java.time.LocalDate;

public class MemberRequestDto {
    @Getter
    public static class JoinMemberDto {
        @NotBlank
        private String name;
        @NotNull
        private Gender gender;
        @NotNull
        private LocalDate birth;
        @NotNull
        private Address address;
    }

    @Getter
//    @NotChallengingMission
    public static class JoinMissionDto{
        @ExistMission
        private Long missionId;
        @ExistMember
        private Long memberId;
    }
}
