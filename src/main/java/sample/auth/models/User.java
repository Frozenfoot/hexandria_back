package sample.auth.models;

public final class User {
    private final long userId;
    private final String login;
    private final String password;
    private final String email;

    public User(long userId, String login, String password, String email) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getlogin() {
        return login;
    }
}
