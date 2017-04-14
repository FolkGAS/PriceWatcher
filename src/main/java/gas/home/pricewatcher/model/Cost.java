package gas.home.pricewatcher.model;

public class Cost extends BaseEntity {

    private Integer cost;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Cost() {
    }

    public Cost(Integer cost) {
        this.cost = cost;
    }
}
