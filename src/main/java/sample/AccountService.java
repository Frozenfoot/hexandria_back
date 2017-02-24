package sample;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import sample.auth.utils.AuthorizationUtils;
import sample.auth.models.RegistrationCredentials;
import sample.auth.models.User;
import sample.auth.exceptions.InvalidPasswordException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    //<login, User>
    private Map<String, User> registeredUsers = new HashMap<>();

    //TODO delete this
    {
      registeredUsers.put("test-user", new User(AuthorizationUtils.generateUID(),"test-user", "test-password","test_email@test.ru"));
    }
    /**
     * @return new User
     */
    public long register(@NotNull String login, @NotNull String password, @NotNull String email) {
        final long newUid = AuthorizationUtils.generateUID();
        registeredUsers.put(login, new User(newUid, login, password, email));
        return newUid;
    }

    public User register(RegistrationCredentials credentials) {
        final User newUser = new User(AuthorizationUtils.generateUID(), credentials.getLogin(), credentials.getPassword(), credentials.getEmail());
        registeredUsers.put(credentials.getLogin(), newUser);
        return newUser;
    }

    /**
     * @return possibly existing User
     */
    public Optional<User> authUser(@NotNull String login, @NotNull String password) throws InvalidPasswordException {
        final User user = registeredUsers.get(login);
        if (user == null) {
            return Optional.empty();
        } else if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException();
        } else {
            return Optional.of(user);
        }
    }

    //we need more methods
}
