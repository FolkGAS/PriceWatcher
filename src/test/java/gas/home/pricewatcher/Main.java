package gas.home.pricewatcher;

import gas.home.pricewatcher.model.Cost;
import gas.home.pricewatcher.model.Goods;
import gas.home.pricewatcher.repository.CostRepository;
import gas.home.pricewatcher.repository.GoodsRepository;
import gas.home.pricewatcher.util.FormatConverter;
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
            System.out.print(".");
            String cost = goods.getInUrlCost();
            if (goods.getRouteByIndexes() == null || goods.getRouteByTags() == null) {
                saveNewGoods(goods);
                addToCostTable(goods.getId(), goods.getInUrlCost());
            }

            if (goods.getRouteByIndexes() != null || goods.getRouteByTags() != null) {
                String costByIndexChain = PageParser.getCostByIndexChain(goods);
                String costByTagChain = PageParser.getCostByTagChain(goods);
                if (costByIndexChain == null || !FormatConverter.getDigital(costByIndexChain).matches("\\d+")
                        || costByTagChain == null || !FormatConverter.getDigital(costByTagChain).matches("\\d+")) {
                    System.out.println("\nError parsing " + goods.getId());
                    saveNewGoods(goods);
                    costByIndexChain = PageParser.getCostByIndexChain(goods);
                    costByTagChain = PageParser.getCostByTagChain(goods);
                }
                List<String> strings = Arrays.asList(FormatConverter.getDigital(cost), FormatConverter.getDigital(costByIndexChain), FormatConverter.getDigital(costByTagChain));
                if (strings.stream().distinct().count() != 1) {
                    printGoods(goods, costByIndexChain, costByTagChain);
                    if (cost != null
                            && costByIndexChain != null && costByIndexChain.replace(" ", "").matches("\\d+")
                            && costByTagChain != null && costByTagChain.replace(" ", "").matches("\\d+")) {
                        goods.setInUrlCost(costByTagChain);
                        goodsRepository.save(goods, 100000);
                        System.out.println("\nCost updated.");
                    }
                }
            }
        });
        System.out.println("\nEND.");
    }

    private static void printGoods(Goods goods, String costByIndexChain, String costByTagChain) {
        System.out.println(goods.getId());
        System.out.println(goods.getName());
        System.out.println(goods.getDescription());
        System.out.println(goods.getInUrlName());
        System.out.println();
        System.out.println("Cost:\t" + goods.getInUrlCost());
        System.out.println("Index:\t" + costByIndexChain);
        System.out.println("Tag:\t" + costByTagChain);
        if (costByIndexChain.equals(costByTagChain) && costByIndexChain.replace(" ", "").matches("\\d+")) {
            addToCostTable(goods.getId(), costByIndexChain);
        }
        System.out.println("----------------------------------------------");
    }

    private static void saveNewGoods(Goods goods) {
        System.out.println("\nFound Entry " + goods.getId());
        System.out.println();
        PageParser.fillGoodsRoute(goods);
        goodsRepository.save(goods, 100000);
    }

    private static void addToCostTable(Integer id, String cost) {
        String digitalCost = FormatConverter.getDigital(cost);
        if (!"".equals(digitalCost)) {
            Cost c = new Cost(Integer.parseInt(digitalCost));
            costRepository.save(c, id);
            System.out.println("\nNew cost saved.");
        }
    }

}
