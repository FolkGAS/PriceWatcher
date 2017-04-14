package gas.home.pricewatcher;

import gas.home.pricewatcher.model.Cost;
import gas.home.pricewatcher.model.Goods;
import gas.home.pricewatcher.repository.CostRepository;
import gas.home.pricewatcher.repository.GoodsRepository;
import gas.home.pricewatcher.util.PageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {

    @Autowired
    private static GoodsRepository goodsRepository;
    @Autowired
    private static CostRepository costRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        goodsRepository = context.getBean(GoodsRepository.class);
        costRepository = context.getBean(CostRepository.class);
        List<Goods> goodsList = goodsRepository.getAll(100000);
        goodsList.stream().skip(0).forEach(goods -> {
//            PageParser.fillGoodsRoute(goods);
//            goodsRepository.save(goods, 100000);
            System.out.print(".");
            String cost = goods.getCost();
            String costByIndexChain = PageParser.getCostByIndexChain(goods);
            System.out.print("|");
            String costByTagChain = PageParser.getCostByTagChain(goods);
            System.out.print("-");
            if (costByIndexChain == null || !costByIndexChain.replace(" ", "").matches("\\d+")
                    || costByTagChain == null || !costByTagChain.replace(" ", "").matches("\\d+")) {
                System.out.println("\nError parsing " + goods.getId());
                System.out.println();
                PageParser.fillGoodsRoute(goods);
                goodsRepository.save(goods, 100000);
                costByIndexChain = PageParser.getCostByIndexChain(goods);
                costByTagChain = PageParser.getCostByTagChain(goods);
            }
            List<String> strings = Arrays.asList(cost, costByIndexChain, costByTagChain);
            if (strings.stream().distinct().count() != 1) {
                printGoods(goods, costByIndexChain, costByTagChain);
                if (cost != null
                        && costByIndexChain != null && costByIndexChain.replace(" ", "").matches("\\d+")
                        && costByTagChain != null && costByTagChain.replace(" ", "").matches("\\d+")) {
                    goods.setCost(costByTagChain);
                    goodsRepository.save(goods, 100000);
                    System.out.println("Cost updated.");
                }
            }
            System.out.print("+  ");
        });
        System.out.println();
        System.out.println("END.");
    }

    private static void printGoods(Goods goods, String costByIndexChain, String costByTagChain) {
        System.out.println();
        System.out.println(goods.getId());
        System.out.println(goods.getName());
        System.out.println(goods.getDescription());
        System.out.println(goods.getItemNameFromSite());
        System.out.println();
        System.out.println("Cost:\t" + goods.getCost());
        System.out.println("Index:\t" + costByIndexChain);
        System.out.println("Tag:\t" + costByTagChain);
        if (costByIndexChain.equals(costByTagChain) && costByIndexChain.replace(" ", "").matches("\\d+")) {
            Cost cost = new Cost(Integer.parseInt(costByIndexChain.replace(" ", "")));
            costRepository.save(cost, goods.getId());
            System.out.println("New cost saved.");
        }
        System.out.println("----------------------------------------------");
    }
}
