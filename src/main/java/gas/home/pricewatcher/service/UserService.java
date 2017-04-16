package gas.home.pricewatcher.service;

import gas.home.pricewatcher.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    User save(User user);

    User update(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    List<User> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
