package gas.home.pricewatcher.repository.mock;

import gas.home.pricewatcher.repository.CostRepository;
import gas.home.pricewatcher.model.Cost;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryCostsRepository implements CostRepository {

    private Map<Integer, Map<Integer, Cost>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    
    @Override
    public Cost save(Cost cost, int goodsId) {
        Objects.requireNonNull(cost);
        if (cost.isNew()) {
            cost.setId(counter.incrementAndGet());
        } else if (get(cost.getId(), goodsId) == null) {
            return null;
        }
        Map<Integer, Cost> costMap = repository.computeIfAbsent(goodsId, ConcurrentHashMap::new);
        costMap.put(cost.getId(), cost);
        return cost;
    }

    @Override
    public boolean delete(int id, int goodsId) {
        Map<Integer, Cost> costMap = repository.get(goodsId);
        return costMap != null && costMap.remove(id) != null;
    }

    @Override
    public Cost get(int id, int goodsId) {
        Map<Integer, Cost> costMap = repository.get(goodsId);
        return costMap == null ? null : costMap.get(id);
    }

    @Override
    public List<Cost> getAll(int goodsId) {
        return getAllAsStream(goodsId).collect(Collectors.toList());
    }

    @Override
    public List<Cost> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int goodsId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream(goodsId)
//                .filter()
                .collect(Collectors.toList());
    }

    private Stream<Cost> getAllAsStream(int goodsId) {
        Map<Integer, Cost> meals = repository.get(goodsId);
        return meals == null ?
                Stream.empty() : meals.values().stream().sorted(Comparator.comparing(Cost::getDateTime).thenComparing(Cost::getId).reversed());
    }
}
