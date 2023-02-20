package brewery.persistence.entities;

import java.io.Serializable;

public class Warehouse implements Serializable {
    private Integer id;
    private FactoryUnit factoryUnit;
    private Integer capacity;
    private String title;
    private String description;

    public Warehouse(FactoryUnit factoryUnit, String title) {
        this.factoryUnit = factoryUnit;
        this.title = title;
    }

    public Warehouse(Integer id, FactoryUnit factoryUnit, Integer capacity, String title, String description) {
        this.id = id;
        this.factoryUnit = factoryUnit;
        this.capacity = capacity;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FactoryUnit getFactoryUnit() {
        return factoryUnit;
    }

    public void setFactoryUnit(FactoryUnit factoryUnit) {
        this.factoryUnit = factoryUnit;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Warehouse(id: %d, title: %s)", this.getId(),
                this.getTitle());
    }
}
