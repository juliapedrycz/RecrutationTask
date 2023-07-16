package recrutation.witek.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recrutation.witek.entity.ApiCalls;
import recrutation.witek.repository.ApiCallsRepository;


@SpringBootTest
public class ApiCallsRepositoryTest {

    @Autowired
    private ApiCallsRepository apiCallsRepository;

    @Test
    public void whenNewRecordThenRequestCountEquals1() {
        //given & when
        apiCallsRepository.save(new ApiCalls("login"));

        //then
        Assertions.assertEquals(apiCallsRepository.findById("login").get().getREQUEST_COUNT(), 1);
    }

    @Test
    public void whenInvokeIncrementThenRequestCountIncrements() {
        //given & when
        apiCallsRepository.save(new ApiCalls("login"));
        apiCallsRepository.incrementRequestCalls("login");

        //then
        Assertions.assertEquals(apiCallsRepository.findById("login").get().getREQUEST_COUNT(), 2);
    }

}
