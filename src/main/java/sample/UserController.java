package sample;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sample.auth.RegistrationCredentials;

import javax.servlet.http.HttpSession;

/**
 * Created by Solovyev on 09/02/2017.
 */
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
    @RequestMapping(path = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public RegistrationCredentials register(@RequestBody RegistrationCredentials credentials, HttpSession httpSession) {
        final String login = credentials.getLogin();
        logger.debug("/register called with login:{}", login);
        final long uid = accountService.register(credentials);
        httpSession.setAttribute("userId", uid);
        return credentials;
    }

    /**
     * Конструктор тоже будет вызван с помощью reflection'а. Другими словами, объект создается через ApplicationContext.
     * Поэтому в нем можно использовать DI. Подробнее про это расскажу на лекции.
     */
    public UserController(@NotNull AccountService accountService) {
        this.accountService = accountService;
    }
}
