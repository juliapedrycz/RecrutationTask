package recrutation.witek.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserBaseDto {

    @JsonProperty("id")
    protected String id;

    @JsonProperty("login")
    protected String login;
    @JsonProperty("name")
    protected String name;
    @JsonProperty("type")
    protected String type;
    @JsonProperty("avatar_url")
    protected String avatarURL;
    @JsonProperty("created_at")
    protected Date createdAt;

    public UserBaseDto(String id, String login, String name, String type, String avatarURL, Date createdAt) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarURL = avatarURL;
        this.createdAt = createdAt;
    }

    public UserBaseDto() {

    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
