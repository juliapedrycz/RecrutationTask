package recrutation.witek.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import recrutation.witek.dto.UserGithubDto;
import recrutation.witek.dto.UserQueryDto;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient

public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void whenGetUserThenStatusOkAndProperValues() {
        //given
        String login = "juliapedrycz";
        UserGithubDto githubUser = fetchGitInfo(login);
        String url = "http://localhost:" + port + "/users/" + login;

        //when
        ResponseEntity<UserQueryDto> response = restTemplate
                .exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });

        //then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(githubUser.getLogin(), response.getBody().getLogin());
    }

    @Test
    public void whenGetNonExistingUserThenStatus404() {
        //given
        String login = "juliapedryczcz";
        UserGithubDto githubUser = fetchGitInfo(login);
        String url = "http://localhost:" + port + "/users/" + login;

        //when
        ResponseEntity<UserQueryDto> response = restTemplate
                .exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });

        //then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private static UserGithubDto fetchGitInfo(String login) {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String url = String.format("https://api.github.com/users/%s", login);
        ResponseEntity<UserGithubDto> response = restTemplate.getForEntity(url, UserGithubDto.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
}
