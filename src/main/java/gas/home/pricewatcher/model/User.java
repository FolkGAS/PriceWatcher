package gas.home.pricewatcher.model;

import java.time.LocalDateTime;
import java.util.Set;

public class User extends NamedEntity{

    private String email;

    private String password;

    private boolean enabled = true;

    private final LocalDateTime registered = LocalDateTime.now();

    private Set<Role> roles;
}
