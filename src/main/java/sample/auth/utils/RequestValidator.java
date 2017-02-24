package sample.auth.utils;

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
        return Optional.empty();
    }
}
