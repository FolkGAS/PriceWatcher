package gas.home.pricewatcher.Repository.mock;

import gas.home.pricewatcher.Repository.GoodsRepository;
import gas.home.pricewatcher.model.Goods;
import gas.home.pricewatcher.util.MockValues;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryGoodsRepository implements GoodsRepository {
    private Map<Integer, Goods> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MockValues.goodsList.forEach(goods -> {
            goods.setUserId(1);
            save(goods);
        });
    }

    @Override
    public Goods save(Goods goods) {
        if (goods.isNew()) {
            goods.setId(counter.incrementAndGet());
        }
        repository.put(goods.getId(), goods);
        return goods;
    }

    @Override
    public Goods delete(int id) {
        return repository.remove(id);
    }

    @Override
    public Goods get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Goods> getAll() {
        Collection<Goods> goodsCollection = repository.values();
        return goodsCollection.isEmpty() ? Collections.EMPTY_LIST :
                goodsCollection
                        .stream()
                        .sorted(Comparator.comparing(Goods::getRegistered).thenComparing(Goods::getId).reversed())
                        .collect(Collectors.toList());
    }
}
