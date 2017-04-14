package gas.home.pricewatcher.model;

public class Goods extends NamedEntity {

    private String description;

    private String url;

    private String itemNameFromSite;

    private String cost;

    private String costElementIndexes;

    private String costElementTagsAndClasses;

    public Goods() {
    }

    public Goods(Integer id,
                 String name,
                 String description,
                 String url,
                 String itemNameFromSite,
                 String cost,
                 String costElementIndexes,
                 String costElementTagsAndClasses) {
        super(id, name);
        this.description = description;
        this.url = url;
        this.itemNameFromSite = itemNameFromSite;
        this.cost = cost;
        this.costElementIndexes = costElementIndexes;
        this.costElementTagsAndClasses = costElementTagsAndClasses;
    }

    public Goods(String name,
                 String description,
                 String url,
                 String itemNameFromSite,
                 String cost) {
        this(null, name, description, url, itemNameFromSite, cost, null, null);
    }

    public Goods(String name,
                 String description,
                 String url,
                 String itemNameFromSite,
                 String cost,
                 String costElementIndexes,
                 String costElementTagsAndClasses) {
        this(null, name, description, url, itemNameFromSite, cost, costElementIndexes, costElementTagsAndClasses);
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

    public String getItemNameFromSite() {
        return itemNameFromSite;
    }

    public void setItemNameFromSite(String itemNameFromSite) {
        this.itemNameFromSite = itemNameFromSite;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCostElementIndexes() {
        return costElementIndexes;
    }

    public void setCostElementIndexes(String costElementIndexes) {
        this.costElementIndexes = costElementIndexes;
    }

    public String getCostElementTagsAndClasses() {
        return costElementTagsAndClasses;
    }

    public void setCostElementTagsAndClasses(String costElementTagsAndClasses) {
        this.costElementTagsAndClasses = costElementTagsAndClasses;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", itemNameFromSite='" + itemNameFromSite + '\'' +
                ", cost='" + cost + '\'' +
                ", dateTime=" + dateTime +
                ", costElementIndexes='" + costElementIndexes + '\'' +
                ", costElementTagsAndClasses='" + costElementTagsAndClasses + '\'' +
                '}';
    }
}
