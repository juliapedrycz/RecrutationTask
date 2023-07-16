package recrutation.witek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import recrutation.witek.entity.ApiCalls;

@Repository
public interface ApiCallsRepository extends JpaRepository<ApiCalls, String> {

    @Transactional
    @Modifying
    @Query("update APICALLS ac set ac.REQUEST_COUNT = ac.REQUEST_COUNT+1 where LOGIN = :LOGIN")
    void incrementRequestCalls(@Param("LOGIN") String login);
}
