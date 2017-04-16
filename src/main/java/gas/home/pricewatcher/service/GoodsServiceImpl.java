package gas.home.pricewatcher.service;

import gas.home.pricewatcher.model.Goods;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Override
    public Goods save(Goods goods, int userId) {
        return null;
    }

    @Override
    public Goods update(Goods goods, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Goods get(int id, int userId) {
        return null;
    }

    @Override
    public List<Goods> getAll(int userId) {
        return null;
    }

    @Override
    public List<Goods> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
