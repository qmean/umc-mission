package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.memberService.MemberCommandService;
import umc.spring.validation.annotation.NotChallengingMission;
import umc.spring.web.dto.member.MemberRequestDto;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<NotChallengingMission, MemberRequestDto.JoinMissionDto> {

    private final MemberCommandService memberCommandService;

    @Override
    public void initialize(NotChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDto.JoinMissionDto value, ConstraintValidatorContext context) {
        boolean challenging = memberCommandService.isMemberChallengingMission(value);
        if (!challenging) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_CHALLENGING.toString()).addConstraintViolation();
        }
        return challenging;
    }
}
