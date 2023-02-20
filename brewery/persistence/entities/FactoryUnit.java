package brewery.persistence.entities;

import java.io.Serializable;

public class FactoryUnit implements Serializable {
    private Integer id;
    private BusinessFactory businessFactory;
    private String description;

    public FactoryUnit(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }

    public FactoryUnit(Integer id, BusinessFactory businessFactory, String description) {
        this.id = id;
        this.businessFactory = businessFactory;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BusinessFactory getBusinessFactory() {
        return businessFactory;
    }

    public void setBusinessFactory(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("FactoryUnit(id: %d, factory: %s)", this.getId(),
                this.getBusinessFactory());
    }
}
