package gas.home.pricewatcher.repository;

import gas.home.pricewatcher.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    List<User> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
