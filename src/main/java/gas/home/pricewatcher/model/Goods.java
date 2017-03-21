package gas.home.pricewatcher.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Goods {

    private int id;
    private String name;
    private String description;
    private String url;
    private String url_name;
    private int url_price;
    private Map<LocalDateTime, Integer> prices;

}
