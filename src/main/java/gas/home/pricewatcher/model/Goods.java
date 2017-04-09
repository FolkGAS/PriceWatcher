package gas.home.pricewatcher.model;

import gas.home.pricewatcher.util.PageParser;

import java.time.LocalDateTime;
import java.util.List;

public class Goods extends NamedEntity {

    private Integer userId;

    private String description;

    private String url;

    private String itemNameFromSite;

    private String cost;

    private final LocalDateTime registered = LocalDateTime.now();

    private List<Integer> costElementIndexes;

    private List<PageParser.ElementEntry> costElementTagsAndClasses;

    public Goods(Integer id,
                 Integer userId,
                 String name,
                 String description,
                 String url,
                 String itemNameFromSite,
                 String cost,
                 List<Integer> costElementIndexes,
                 List<PageParser.ElementEntry> costElementTagsAndClasses) {
        super(id, name);
        this.userId = userId;
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
        this(null, null, name, description, url, itemNameFromSite, cost, null, null);
    }

    public Goods(String name,
                 String description,
                 String url,
                 String itemNameFromSite,
                 String cost,
                 List<Integer> costElementIndexes,
                 List<PageParser.ElementEntry> costElementTagsAndClasses) {
        this(null, null, name, description, url, itemNameFromSite, cost, costElementIndexes, costElementTagsAndClasses);
    }

    public Goods(Integer id, String name, Integer userId, String description, String url, String itemNameFromSite, String cost, List<Integer> costElementIndexes, List<PageParser.ElementEntry> costElementTagsAndClasses) {
        super(id, name);
        this.userId = userId;
        this.description = description;
        this.url = url;
        this.itemNameFromSite = itemNameFromSite;
        this.cost = cost;
        this.costElementIndexes = costElementIndexes;
        this.costElementTagsAndClasses = costElementTagsAndClasses;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public LocalDateTime getRegistered() {
        return registered;
    }

    public List<Integer> getCostElementIndexes() {
        return costElementIndexes;
    }

    public void setCostElementIndexes(List<Integer> costElementIndexes) {
        this.costElementIndexes = costElementIndexes;
    }

    public List<PageParser.ElementEntry> getCostElementTagsAndClasses() {
        return costElementTagsAndClasses;
    }

    public void setCostElementTagsAndClasses(List<PageParser.ElementEntry> costElementTagsAndClasses) {
        this.costElementTagsAndClasses = costElementTagsAndClasses;
    }
}
