package gas.home.pricewatcher.model;

import gas.home.pricewatcher.util.FileListConverter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Goods extends NamedEntity {

    private String description;

    private String url;

    private String itemNameFromSite;

    private String cost;

    private final LocalDateTime registered = LocalDateTime.now();

    private List<Integer> costElementIndexes;

    private List<ElementEntry> costElementTagsAndClasses;

    public Goods() {
    }

    public Goods(Integer id,
                 String name,
                 String description,
                 String url,
                 String itemNameFromSite,
                 String cost,
                 List<Integer> costElementIndexes,
                 List<ElementEntry> costElementTagsAndClasses) {
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
                 List<Integer> costElementIndexes,
                 List<ElementEntry> costElementTagsAndClasses) {
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

    public LocalDateTime getRegistered() {
        return registered;
    }

    public List<Integer> getCostElementIndexes() {
        return costElementIndexes;
    }

    public void setCostElementIndexes(List<Integer> costElementIndexes) {
        this.costElementIndexes = costElementIndexes;
    }

    public List<ElementEntry> getCostElementTagsAndClasses() {
        return costElementTagsAndClasses;
    }

    public void setCostElementTagsAndClasses(List<ElementEntry> costElementTagsAndClasses) {
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
                ", registered=" + registered +
                ", costElementIndexes=" + costElementIndexes +
                ", costElementTagsAndClasses=" + costElementTagsAndClasses +
                '}';
    }

    public static class ElementEntry implements Serializable {
        private String tag;
        private String clasz;

        public ElementEntry() {
        }

        public ElementEntry(String gson) {
            FileListConverter.getFromGson(gson,ElementEntry.class);
        }

        public ElementEntry(String tagName, String className) {
            this.tag = tagName;
            this.clasz = className;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getClasz() {
            return clasz;
        }

        public void setClasz(String clasz) {
            this.clasz = clasz;
        }

        @Override
        public String toString() {
            return "ElementEntry{" +
                    "tag='" + tag + '\'' +
                    ", clasz='" + clasz + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ElementEntry that = (ElementEntry) o;

            return (tag != null ? tag.equals(that.tag) : that.tag == null) && (clasz != null ? clasz.equals(that.clasz) : that.clasz == null);
        }

        @Override
        public int hashCode() {
            int result = tag != null ? tag.hashCode() : 0;
            result = 31 * result + (clasz != null ? clasz.hashCode() : 0);
            return result;
        }
    }
}
