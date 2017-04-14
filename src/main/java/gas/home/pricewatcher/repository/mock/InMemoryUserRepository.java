package gas.home.pricewatcher.repository.mock;

import gas.home.pricewatcher.repository.UserRepository;
import gas.home.pricewatcher.model.User;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryUserRepository implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        Objects.requireNonNull(user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        return repository.put(user.getId(), user);
    }

    @Override
    public boolean delete(int id) {
        User user = repository.get(id);
        return user != null && repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email);
        return getAllAsStream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return getAllAsStream().collect(Collectors.toList());
    }

    @Override
    public List<User> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream()
//                .filter()
                .collect(Collectors.toList());
    }

    private Stream<User> getAllAsStream() {
        return repository.isEmpty() ?
                Stream.empty() : repository.values().stream().sorted(Comparator.comparing(User::getDateTime).thenComparing(User::getId).reversed());
    }
}
