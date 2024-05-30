package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberService.MemberCommandService;
import umc.spring.service.memberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.member.MemberRequestDto;
import umc.spring.web.dto.member.MemberResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberRestController {

    private static final Logger log = LoggerFactory.getLogger(MemberRestController.class);
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDto.JoinMemberResultDto>
    join(@RequestBody @Valid MemberRequestDto.JoinMemberDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }

    @PostMapping("/mission")
    public ApiResponse<MemberResponseDto.JoinMissionResultDto>
    joinMission(@RequestBody @Valid MemberRequestDto.JoinMissionDto request) {
        MemberResponseDto.JoinMissionResultDto result = memberCommandService.joinMission(request);
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 회원의 리뷰 목록 조회 API",
            description = "특정 회원의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다." +
                    " pathVariable로 회원의 ID를, queryString으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "ID에 해당하는 회원이 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "Page 번호가 음수 혹은 잘못된 값입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable입니다."),
            @Parameter(name = "page", description = "페이지 번호 입니다.")
    })
    public ApiResponse<MemberResponseDto.MemberReviewListDto>
    getReviewList(@PathVariable("memberId") Long memberId,
                  @CheckPage @RequestParam("page") Integer page) {
        Page<Review> reviewPage = memberQueryService.getReviewResultList(memberId, page - 1);
        MemberResponseDto.MemberReviewListDto memberReviewListDto = MemberConverter.toMemberReviewListDto(reviewPage);
        return ApiResponse.onSuccess(memberReviewListDto);
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 회원의 미션 목록 조회 API",
            description = "특정 회원의 미션 목록을 조회하는 API이며, 페이징을 포함합니다." +
                    " pathVariable로 회원의 ID를, queryString으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "ID에 해당하는 회원이 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "Page 번호가 음수 혹은 잘못된 값입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable입니다."),
            @Parameter(name = "page", description = "페이지 번호 입니다.")
    })
    public ApiResponse<MemberResponseDto.MemberMissionListDto>
    getMissionList(@PathVariable("memberId") Long memberId,
                   @CheckPage @RequestParam("page") Integer page) {
        Page<MemberMission> missionResultLIst = memberQueryService.getMissionResultLIst(memberId, page - 1);
        MemberResponseDto.MemberMissionListDto memberMissionListDto = MemberConverter.toMemberMissionListDto(missionResultLIst);
        return ApiResponse.onSuccess(memberMissionListDto);
    }

    @Operation(summary = "회원의 미션을 성공하는 API",
            description = "회원의 특정 미션을 성공 상태로 만들고 성공한 미션 목록을 조회하는 API이며, 페이징을 포함합니다." +
                    " pathVariable로 회원의 ID를, queryString으로 page 번호와 미션의 ID를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "ID에 해당하는 회원이 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "Page 번호가 음수 혹은 잘못된 값입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4003", description = "미션 진행중이 아닙니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4004", description = "회원이 미션을 등록하지 않았습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable입니다."),
            @Parameter(name = "page", description = "페이지 번호 입니다."),
            @Parameter(name = "missionId", description = "미션 ID 입니다.")
    })
    @PatchMapping("/{memberId}/mission")
    public ApiResponse<MemberResponseDto.MemberMissionCompleteDto>
    completeMission(@PathVariable("memberId") Long memberId,
                    @CheckPage @RequestParam("page") Integer page,
                    @ExistMission @RequestParam("missionId") Long missionId) {
        memberQueryService.completeMission(memberId, missionId);
        Page<MemberMission> successMissionList = memberQueryService.getCompleteMissionList(memberId, page - 1);
        MemberResponseDto.MemberMissionCompleteDto memberMissionSuccessDto = MemberConverter.toMemberMissionSuccessDto(successMissionList);
        return ApiResponse.onSuccess(memberMissionSuccessDto);
    }
}
