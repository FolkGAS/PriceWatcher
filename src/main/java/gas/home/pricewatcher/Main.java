package gas.home.pricewatcher;

import gas.home.pricewatcher.Repository.GoodsRepository;
import gas.home.pricewatcher.Repository.jdbc.JdbcGoodsRepositoryImpl;
import gas.home.pricewatcher.model.Goods;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        GoodsRepository repository = new InMemoryGoodsRepository();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pricewatch");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        GoodsRepository repository = new JdbcGoodsRepositoryImpl(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
        List<Goods> goodsList = repository.getAll(100000);
//        GoodsRepository mockRepository = new InMemoryGoodsRepository();
//        List<Goods> goodsList = mockRepository.getAll(1);
//        goodsList.forEach(goods -> goods.setId(null));
//        goodsList.forEach(goods -> {
//            PageParser.fillGoodsRoute(goods);
//            goods.setCostElementIndexes(FileListConverter.getIndexFromFile(goods));
//            goods.setCostElementTagsAndClasses(FileListConverter.getTagsFromFile(goods));
//            repository.save(goods, 100000);
        Goods goods = repository.get(100005, 100000);
            System.out.println(goods.getId());
            System.out.println(goods.getName());
            System.out.println(goods.getDescription());
            System.out.println(goods.getUrl());
            System.out.println(goods.getItemNameFromSite());
            System.out.println(goods.getCostElementIndexes() == null);
            System.out.println(goods.getCostElementTagsAndClasses() == null);
//            System.out.println("Indexes " + goods.getCostElementIndexes().size());
//            System.out.println("Tags" + goods.getCostElementTagsAndClasses().size());
            System.out.println();
            System.out.println("Cost:\t" + goods.getCost());
//            System.out.println("Index:\t" + PageParser.getCostByIndexChain(goods));
//            System.out.println("Tag:\t" + PageParser.getCostByTagChain(goods));
//            System.out.println("\n----------------------------------------------\n");
//        });
    }
}
