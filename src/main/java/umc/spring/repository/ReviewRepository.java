package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @EntityGraph(attributePaths = {"member"})
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    @EntityGraph(attributePaths = {"member"})
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
