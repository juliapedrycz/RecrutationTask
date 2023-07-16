package recrutation.witek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recrutation.witek.provider.UserProvider;
import recrutation.witek.dto.UserGithubDto;
import recrutation.witek.dto.UserQueryDto;
import recrutation.witek.entity.ApiCalls;
import recrutation.witek.repository.ApiCallsRepository;

@Service
public class UserService {

    @Autowired
    ApiCallsRepository apiCallsRepository;

    public UserQueryDto getUser(String login) {
        UserGithubDto userGithubDto = UserProvider.fetchInfo(login);
        incrementRequestCalls(login);
        return new UserQueryDto(userGithubDto);
    }

    @Transactional
    private void incrementRequestCalls(String login) {
        if (apiCallsRepository.findById(login).isPresent()) {
            apiCallsRepository.incrementRequestCalls(login);
            return;
        }
        apiCallsRepository.save(new ApiCalls(login));
    }
}
