package gas.home.pricewatcher.model;

import java.time.LocalDateTime;

public class BaseEntity {
    protected Integer id;

    protected final LocalDateTime dateTime = LocalDateTime.now();

    public BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
