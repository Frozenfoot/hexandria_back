package sample;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import sample.auth.AuthorizationUtils;
import sample.auth.RegistrationCredentials;
import sample.auth.User;
import sample.auth.exceptions.InvalidPasswordException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    //<login, User>
    private Map<String, User> registeredUsers = new HashMap<>();

    /**
     * @return new User ID
     */
    public long register(@NotNull String login, @NotNull String password, @NotNull String email) {
        final long newUid = AuthorizationUtils.generateUID();
        registeredUsers.put(login, new User(newUid, login, password, email));
        return newUid;
    }
    public long register(RegistrationCredentials credentials) {
        final long newUid = AuthorizationUtils.generateUID();
        registeredUsers.put(credentials.getLogin(), new User(newUid, credentials.getLogin(), credentials.getPassword(), credentials.getEmail()));
        return newUid;
    }

    /**
     * @return possibly existing User
     */
    public Optional<User> authUser(@NotNull String login, @NotNull String password) throws InvalidPasswordException {
        final User user = registeredUsers.get(login);
        if (user == null){
            return Optional.empty();
        } else if (!user.getPassword().equals(password)){
            throw new InvalidPasswordException();
        } else {
            return Optional.of(user);
        }
    }

    //we need more methods
}
