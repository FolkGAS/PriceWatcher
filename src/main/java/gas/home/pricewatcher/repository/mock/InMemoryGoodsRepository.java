package gas.home.pricewatcher.repository.mock;

import gas.home.pricewatcher.repository.GoodsRepository;
import gas.home.pricewatcher.model.Goods;
import gas.home.pricewatcher.util.MockValues;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryGoodsRepository implements GoodsRepository {
    private Map<Integer, Map<Integer, Goods>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MockValues.goodsList.forEach(goods -> save(goods, 1));
    }

    @Override
    public Goods save(Goods goods, int userId) {
        Objects.requireNonNull(goods);
        if (goods.isNew()) {
            goods.setId(counter.incrementAndGet());
        } else if (get(goods.getId(), userId) == null) {
            return null;
        }
        Map<Integer, Goods> goodsMap = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        goodsMap.put(goods.getId(), goods);
        return goods;
    }


    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Goods> goodsMap = repository.get(userId);
        return goodsMap != null && goodsMap.remove(id) != null;
    }

    @Override
    public Goods get(int id, int userId) {
        Map<Integer, Goods> goodsMap = repository.get(userId);
        return goodsMap == null ? null : goodsMap.get(id);
    }

    @Override
    public List<Goods> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public List<Goods> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream(userId)
//                .filter()
                .collect(Collectors.toList());
    }

    private Stream<Goods> getAllAsStream(int userId) {
        Map<Integer, Goods> meals = repository.get(userId);
        return meals == null ?
                Stream.empty() : meals.values().stream().sorted(Comparator.comparing(Goods::getDateTime).thenComparing(Goods::getId).reversed());
    }
}
