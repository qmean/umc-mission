package umc.spring.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.store.StoreRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDto.AddStoreRequestDto request) {
        Store store = StoreConverter.toStore(request);
        storeRepository.save(store);
        return store;
    }

    @Override
    public boolean existStoreById(Long id) {
        return storeRepository.existsById(id);
    }
}
