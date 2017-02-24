package sample;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.auth.models.ErrorResponse;
import sample.auth.models.RegistrationCredentials;
import sample.auth.utils.RequestValidator;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @NotNull
    private final AccountService accountService;
    /**
     * Данный метод вызывается с помощью reflection'a, поэтому Spring позволяет инжектить в него аргументы.
     * Подробнее можно почитать в сорцах к аннотации {@link RequestMapping}. Там описано как заинжектить различные атрибуты http-запроса.
     * Возвращаемое значение можно так же варьировать. Н.п. Если отдать InputStream, можно стримить музыку или видео
     */
    @RequestMapping(path = "api/signup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity register(@RequestBody RegistrationCredentials credentials, HttpSession httpSession) {
        logger.debug("/signup called with login: {}", credentials.getLogin());
        final Optional<ErrorResponse> validationError = RequestValidator.validateRegistrationCredentials(credentials);
        if (validationError.isPresent()){
            httpSession.setAttribute("error", validationError.get().getErrorText());
            final ErrorResponse error = validationError.get();
            return ResponseEntity.status(error.getErrorStatus().getCode()).body(error);
        }
        accountService.register(credentials);
        return ResponseEntity.ok("User successfully registered!");
    }

    /**
     * Конструктор тоже будет вызван с помощью reflection'а. Другими словами, объект создается через ApplicationContext.
     * Поэтому в нем можно использовать DI. Подробнее про это расскажу на лекции.
     */
    public UserController(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }
}
