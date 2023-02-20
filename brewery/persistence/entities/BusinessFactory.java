package brewery.persistence.entities;

import java.io.Serializable;

public class BusinessFactory implements Serializable {
    private Integer id;
    private City city;
    private String title;

    public BusinessFactory(City city, String title) {
        this.city = city;
        this.title = title;
    }

    public BusinessFactory(Integer id, City city, String title) {
        this.id = id;
        this.city = city;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("Factory(id: %d, title: %s, city: %s)",
                this.id, this.title, this.city.toString());
    }
}
