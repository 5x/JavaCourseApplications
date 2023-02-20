package brewery;

import brewery.persistence.dao.IBusinessFactory;
import brewery.persistence.dao.ICity;
import brewery.persistence.entities.BusinessFactory;
import brewery.persistence.entities.City;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DAOFactory mySqlFactory = brewery.DAOFactory.getDAOFactory(DAOFactory.FactoryEnum.MYSQL);
        ICity cityDAO = mySqlFactory.getCityDAO();

        // Create
        City paris = new City("Paris");
        cityDAO.create(paris);

        // Find all
        List<City> cities = cityDAO.findAll();
        for (City city : cities) {
            System.out.println(city.getName());
        }

        // Get by id
        City krasnopavlika = cityDAO.read(3);
        System.out.println(krasnopavlika);

        // Update
        krasnopavlika.setName("KRASNO-PAVLIVKA");
        cityDAO.update(krasnopavlika);

        // Delete
        cityDAO.delete(paris.getId());

        // Find all
        cities = cityDAO.findAll();
        for (City city : cities) {
            System.out.println(city.getName());
        }

        IBusinessFactory businessFactoryDAO = mySqlFactory.getBusinessFactory();
        List<BusinessFactory> businessFactories = businessFactoryDAO.findAll();
        for (BusinessFactory obj : businessFactories) {
            System.out.println(obj);
        }

        mySqlFactory.closeSession();
    }
}
