package gas.home.pricewatcher;

import gas.home.pricewatcher.Repository.GoodsRepository;
import gas.home.pricewatcher.Repository.mock.InMemoryGoodsRepository;
import gas.home.pricewatcher.model.Goods;
import gas.home.pricewatcher.util.PageParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GoodsRepository repository = new InMemoryGoodsRepository();
        List<Goods> goodsList = repository.getAll();
        goodsList.forEach(PageParser::temp);

    }
}
