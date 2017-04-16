package gas.home.pricewatcher.model;

import java.time.LocalDateTime;

public class BaseEntity {
    Integer id;

    LocalDateTime dateTime = LocalDateTime.now();

    BaseEntity() {
    }

    BaseEntity(Integer id) {
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
