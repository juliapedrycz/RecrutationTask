package recrutation.witek.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recrutation.witek.dto.UserGithubDto;
import recrutation.witek.dto.UserQueryDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UserQueryDtoTest {

    @Test
    public void whenUserGithubDtoValidThenValidCalculations() throws ParseException {
        //given
        String dateString = "2023-07-13";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        UserGithubDto userGithubDto = new UserGithubDto("id", "login", "name",
                "type", "avatar_url", dateFormat.parse(dateString), 6, 2);
        UserQueryDto userQueryDto = new UserQueryDto(userGithubDto);

        //when & then
        Assertions.assertEquals(((6.0 / userGithubDto.getFollowers()) * (2 + userGithubDto.getPublic_repos())),
                userQueryDto.getCalculations());
    }

    @Test
    public void whenFollowersInvalidThenThrowException() throws ParseException {
        //given
        String dateString = "2023-07-13";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        UserGithubDto userGithubDto = new UserGithubDto("id", "login", "name",
                "type", "avatar_url", dateFormat.parse(dateString), 0, 2);

        //when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserQueryDto(userGithubDto),
                "Invalid values for calculations.");
    }

    @Test
    public void whenPublicReposInvalidThenThrowException() throws ParseException {
        //given
        String dateString = "2023-07-13";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        UserGithubDto userGithubDto = new UserGithubDto("id", "login", "name",
                "type", "avatar_url", dateFormat.parse(dateString), 2, null);

        //when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserQueryDto(userGithubDto),
                "Invalid values for calculations.");
    }

    @Test
    public void whenCreatingUserQueryDtoFromUserGithubDtoThenEqualValues() throws ParseException {
        //given
        String dateString = "2023-07-13";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        UserGithubDto userGithubDto = new UserGithubDto("id", "login", "name",
                "type", "avatar_url", dateFormat.parse(dateString), 6, 2);

        //when
        UserQueryDto userQueryDto = new UserQueryDto(userGithubDto);

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(userGithubDto.getId(), userQueryDto.getId()),
                () -> Assertions.assertEquals(userGithubDto.getLogin(), userQueryDto.getLogin()),
                () -> Assertions.assertEquals(userGithubDto.getName(), userQueryDto.getName()),
                () -> Assertions.assertEquals(userGithubDto.getType(), userQueryDto.getType()),
                () -> Assertions.assertEquals(userGithubDto.getAvatarURL(), userQueryDto.getAvatarURL()),
                () -> Assertions.assertEquals(userGithubDto.getCreatedAt(), userQueryDto.getCreatedAt())
        );
    }
}
