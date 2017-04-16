package gas.home.pricewatcher.model;

public class Goods extends NamedEntity {

    private String description;

    private String url;

    private String inUrlName;

    private String inUrlCost;

    private String routeByIndexes;

    private String routeByTags;

    public Goods() {
    }

    public Goods(Integer id,
                 String name,
                 String description,
                 String url,
                 String inUrlName,
                 String inUrlCost,
                 String routeByIndexes,
                 String routeByTags) {
        super(id, name);
        this.description = description;
        this.url = url;
        this.inUrlName = inUrlName;
        this.inUrlCost = inUrlCost;
        this.routeByIndexes = routeByIndexes;
        this.routeByTags = routeByTags;
    }

    public Goods(String name,
                 String description,
                 String url,
                 String inUrlName,
                 String inUrlCost) {
        this(null, name, description, url, inUrlName, inUrlCost, null, null);
    }

    public Goods(String name,
                 String description,
                 String url,
                 String inUrlName,
                 String inUrlCost,
                 String routeByIndexes,
                 String routeByTags) {
        this(null, name, description, url, inUrlName, inUrlCost, routeByIndexes, routeByTags);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInUrlName() {
        return inUrlName;
    }

    public void setInUrlName(String inUrlName) {
        this.inUrlName = inUrlName;
    }

    public String getInUrlCost() {
        return inUrlCost;
    }

    public void setInUrlCost(String inUrlCost) {
        this.inUrlCost = inUrlCost;
    }

    public String getRouteByIndexes() {
        return routeByIndexes;
    }

    public void setRouteByIndexes(String routeByIndexes) {
        this.routeByIndexes = routeByIndexes;
    }

    public String getRouteByTags() {
        return routeByTags;
    }

    public void setRouteByTags(String routeByTags) {
        this.routeByTags = routeByTags;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", inUrlName='" + inUrlName + '\'' +
                ", inUrlCost='" + inUrlCost + '\'' +
                ", dateTime=" + dateTime +
                ", routeByIndexes='" + routeByIndexes + '\'' +
                ", routeByTags='" + routeByTags + '\'' +
                '}';
    }
}
