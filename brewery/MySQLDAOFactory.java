package brewery;

import brewery.persistence.dao.IBusinessFactory;
import brewery.persistence.dao.ICity;
import brewery.persistence.mysql.BusinessFactoryDAO;
import brewery.persistence.mysql.CityDAO;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {
    private BasicDataSource dataSource;
    private Connection connection;

    MySQLDAOFactory() {
        this.dataSource = new MySQLDataSource();
        this.connection = dataSource.getConnection();
    }

    @Override
    public void closeSession() {
        this.dataSource.closeConnection();
    }

    @Override
    public ICity getCityDAO() {
        return new CityDAO(this.connection);
    }

    @Override
    public IBusinessFactory getBusinessFactory() {
        return new BusinessFactoryDAO(this.connection);
    }

}
