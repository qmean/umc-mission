package umc.spring.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ReasonDTO {
    private boolean isSuccess;
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
