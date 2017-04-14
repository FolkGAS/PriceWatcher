package gas.home.pricewatcher.repository;

import gas.home.pricewatcher.model.Goods;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsRepository {

    Goods save(Goods goods, int userId);

    boolean delete(int id, int userId);

    Goods get(int id, int userId);

    List<Goods> getAll(int userId);

    List<Goods> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
