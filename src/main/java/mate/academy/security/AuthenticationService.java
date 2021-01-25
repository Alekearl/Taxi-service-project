package mate.academy.security;

import java.util.Optional;
import mate.academy.exception.AuthenticationException;
import mate.academy.model.Driver;

public interface AuthenticationService {
    Optional<Driver> login(String login, String password) throws AuthenticationException;
}
