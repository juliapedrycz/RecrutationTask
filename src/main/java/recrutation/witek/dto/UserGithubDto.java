package recrutation.witek.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserGithubDto extends UserBaseDto {

    @JsonProperty("followers")
    private Integer followers;
    @JsonProperty("public_repos")
    private Integer public_repos;

    public UserGithubDto(String id, String login, String name, String type, String avatarURL, Date createdAt,
                         Integer followers, Integer public_repos) {
        super(id, login, name, type, avatarURL, createdAt);
        this.followers = followers;
        this.public_repos = public_repos;
    }

    public UserGithubDto() {
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getPublic_repos() {
        return public_repos;
    }
}
