package sample.auth.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO REFACTOR ME PLS
public final class RegistrationCredentials {
    @JsonProperty("login")
    String login;
    @JsonProperty("password")
    String password;
    @JsonProperty("email")
    String email;
    @JsonCreator
    RegistrationCredentials(@JsonProperty("login") String login,@JsonProperty("password") String password,@JsonProperty("email") String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}