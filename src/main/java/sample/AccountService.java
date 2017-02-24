package sample;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import sample.auth.dao.UserDAO;
import sample.auth.utils.AuthorizationUtils;
import sample.auth.models.RegistrationCredentials;
import sample.auth.models.User;
import sample.auth.exceptions.InvalidPasswordException;

import java.util.Optional;

@Service
public class AccountService {
    /**
     * @return new User
     */
    public User register(RegistrationCredentials credentials) {
        final User newUser = new User(AuthorizationUtils.generateUID(), credentials.getLogin(), credentials.getPassword(), credentials.getEmail());
        UserDAO.save(newUser);
        return newUser;
    }

    /**
     * @return possibly existing User
     */
    public Optional<User> authUser(@NotNull String login, @NotNull String password) throws InvalidPasswordException {
        return UserDAO.load(login);
    }

    //we need more methods
}
