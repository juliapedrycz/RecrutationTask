package recrutation.witek.provider;

import recrutation.witek.dto.UserGithubDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserProvider {

    public static UserGithubDto fetchInfo(String login) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.github.com/users/%s", login);
        ResponseEntity<UserGithubDto> response = restTemplate.getForEntity(url, UserGithubDto.class);
        return response.getBody();
    }
}
