package gas.home.pricewatcher.service;

import gas.home.pricewatcher.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }
}
