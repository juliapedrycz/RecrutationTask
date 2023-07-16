package recrutation.witek.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserQueryDto extends UserBaseDto {
    @JsonProperty("calculations")
    private Double calculations;

    @JsonCreator
    public UserQueryDto(@JsonProperty("id") String id,
                        @JsonProperty("login") String login,
                        @JsonProperty("name") String name,
                        @JsonProperty("type") String type,
                        @JsonProperty("avatar_url") String avatarURL,
                        @JsonProperty("created_at") Date createdAt,
                        @JsonProperty("calculations") Double calculations) {
        super(id, login, name, type, avatarURL, createdAt);
        this.calculations = calculations;
    }

    public UserQueryDto(UserGithubDto userGithubDto) {
        super();
        this.id = userGithubDto.id;
        this.login = userGithubDto.login;
        this.name = userGithubDto.name;
        this.type = userGithubDto.type;
        this.avatarURL = userGithubDto.avatarURL;
        this.createdAt = userGithubDto.createdAt;
        setCalculations(userGithubDto);
    }

    private void setCalculations(UserGithubDto userGithubDto) {
        if (userGithubDto.getFollowers() != null &&
                userGithubDto.getFollowers() != 0 &&
                userGithubDto.getPublic_repos() != null)
            this.calculations = ((6.0 / userGithubDto.getFollowers()) * (2 + userGithubDto.getPublic_repos()));
        else throw new IllegalArgumentException("Invalid values for calculations.");
    }

    public Double getCalculations() {
        return calculations;
    }
}

