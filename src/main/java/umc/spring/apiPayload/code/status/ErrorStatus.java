package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // member error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다"),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다"),

    // 예시
    ARCTICLE_NOT_FOUND(HttpStatus.BAD_REQUEST, "ARTICLE4001", "게시글이 없습니다."),

    // store
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "가게가 없습니다"),

    // mission
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "미션이 없습니다"),
    MISSION_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4002", "미션이 이미 진행중입니다"),
    MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4003", "미션 진행중이 아닙니다"),
    MISSION_NOT_ASSOCIATED(HttpStatus.BAD_REQUEST, "MISSION4004", "회원이 미션을 등록하지 않았습니다."),
    // paging
    WRONG_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "PAGE4001", "잘못된 페이지 번호입니다"),

    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
