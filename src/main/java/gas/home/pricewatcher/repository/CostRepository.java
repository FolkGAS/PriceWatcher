package gas.home.pricewatcher.repository;

import gas.home.pricewatcher.model.Cost;

import java.time.LocalDateTime;
import java.util.List;

public interface CostRepository {
    Cost save(Cost cost, int goodsId);

    boolean delete(int id, int goodsId);

    Cost get(int id, int goodsId);

    List<Cost> getAll(int goodsId);

    List<Cost> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int goodsId);
}
