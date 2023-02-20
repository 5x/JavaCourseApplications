package brewery.persistence.entities;

import java.io.Serializable;
import java.util.Date;

public class Batch implements Serializable {
    private Integer id;
    private Warehouse warehouse;
    private Trader trader;
    private Date createDate;
    private Date shipmentDate;
    private String note;

    public Batch(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Batch(Integer id, Warehouse warehouse, Trader trader, Date createDate, Date shipmentDate, String note) {
        this.id = id;
        this.warehouse = warehouse;
        this.trader = trader;
        this.createDate = createDate;
        this.shipmentDate = shipmentDate;
        this.note = note;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("Batch(id: %d, warehouse: %s)",
                this.getId(), this.getWarehouse());
    }
}
