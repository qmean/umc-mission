package umc.spring.service.storeService;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDto;

public interface StoreCommandService {
    public Store addStore(StoreRequestDto.AddStoreRequestDto request);

    public boolean existStoreById(Long id);
}
