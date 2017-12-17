package skadihouse.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo,String> {
    Page<UserInfo> findAll( Pageable pageable);
    Page<UserInfo> findByEmail(@Param("email") String email, Pageable pageable);

}
