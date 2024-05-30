package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.storeService.StoreCommandService;
import umc.spring.service.storeService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.store.StoreRequestDto;
import umc.spring.web.dto.store.StoreResponseDto;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Validated
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDto.AddStoreResponseDto>
    addStoreController(@RequestBody @Valid StoreRequestDto.AddStoreRequestDto request) {
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toAddResponseDto(store));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다." +
                    " query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable입니다."),
            @Parameter(name = "page", description = "페이지 번호 입니다.")
    })
    public ApiResponse<StoreResponseDto.ReviewPreViewListDto>
    getReviewList(@ExistStore @PathVariable("storeId") Long storeId,
                  @CheckPage @RequestParam("page") Integer page) {
        Page<Review> reviewResultPageList = storeQueryService.getReviewResultList(storeId, page - 1);
        StoreResponseDto.ReviewPreViewListDto reviewPreViewListDto = StoreConverter.reviewPreViewListDto(reviewResultPageList);
        return ApiResponse.onSuccess(reviewPreViewListDto);
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",
    description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다." +
            " pathVariable로 가게의 ID를, queryString으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "ID에 해당하는 가게가 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "Page 번호가 음수 혹은 잘못된 값입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable입니다."),
            @Parameter(name = "page", description = "페이지 번호 입니다.")
    })
    public ApiResponse<StoreResponseDto.StoreMissionListDto>
    getMissionList(@ExistStore @PathVariable("storeId") Long storeId,
                   @CheckPage @RequestParam("page") Integer page) {
        Page<Mission> missionResultList = storeQueryService.getMissionResultList(storeId, page - 1);
        StoreResponseDto.StoreMissionListDto storeMissionListDto = StoreConverter.toStoreMissionListDto(missionResultList);
        return ApiResponse.onSuccess(storeMissionListDto);
    }
}
