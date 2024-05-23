package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionChallengingValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengingValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotChallengingMission {
    String message() default "이미 미션 진행중입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
