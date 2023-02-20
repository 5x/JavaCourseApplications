package brewery.persistence.entities;

import java.io.Serializable;

public class BeerStyle implements Serializable {
    private Integer id;
    private String name;

    public BeerStyle(String name) {
        this.name = name;
    }

    public BeerStyle(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("BeerStyle(id: %d, name: %s)", this.getId(),
                this.getName());
    }
}
