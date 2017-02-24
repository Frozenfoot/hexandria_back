package sample.auth.utils;

import org.springframework.util.StringUtils;
import sample.auth.ERRORSTATE;
import sample.auth.dao.UserDAO;
import sample.auth.models.ErrorResponse;
import sample.auth.models.RegistrationCredentials;

import java.util.Optional;

public class RequestValidator {
    /**
     *
     * @param credentials - registration data
     * @return Optional<ErrorResponse> - if credentials incorrect, returns corresponding ErrorResponse
     */
    public static Optional<ErrorResponse> validateRegistrationCredentials(RegistrationCredentials credentials){
        if (StringUtils.isEmpty(credentials.getLogin()) || StringUtils.isEmpty(credentials.getEmail())
                || StringUtils.isEmpty(credentials.getPassword())){
            return Optional.of(new ErrorResponse("All mandatory fields should be non-empty!", ERRORSTATE.BAD_REQUEST));
        } else if (UserDAO.load(credentials.getLogin()).isPresent()){
            return Optional.of(new ErrorResponse("User with that login already exists!", ERRORSTATE.BAD_REQUEST));
        }
        return Optional.empty();
    }
}
