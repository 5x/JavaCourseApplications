package brewery;

import brewery.persistence.dao.IBusinessFactory;
import brewery.persistence.dao.ICity;

/**
 * Abstract class DAO Factory
 */
public abstract class DAOFactory {

    public static brewery.DAOFactory getDAOFactory(FactoryEnum whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                throw new DAOException("DAO Provider don`t supported.");
        }
    }

    public abstract void closeSession();

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract ICity getCityDAO();

    public abstract IBusinessFactory getBusinessFactory();

    public enum FactoryEnum {
        MYSQL
    }

}
