package gas.home.pricewatcher.Repository;

import gas.home.pricewatcher.model.Goods;

import java.util.List;

public interface GoodsRepository {

    Goods save(Goods goods);

    Goods delete(int id);

    Goods get(int id);

    List<Goods> getAll();
}
