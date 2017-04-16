package gas.home.pricewatcher.service;

import gas.home.pricewatcher.model.Cost;

import java.time.LocalDateTime;
import java.util.List;

public class CostServiceImpl implements CostService{

    @Override
    public Cost save(Cost cost, int goodsId) {
        return null;
    }

    @Override
    public Cost update(Cost cost, int goodsId) {
        return null;
    }

    @Override
    public boolean delete(int id, int goodsId) {
        return false;
    }

    @Override
    public Cost get(int id, int goodsId) {
        return null;
    }

    @Override
    public List<Cost> getAll(int goodsId) {
        return null;
    }

    @Override
    public List<Cost> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int goodsId) {
        return null;
    }
}
