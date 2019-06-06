package logic.Info;

import java.sql.Date;
import java.sql.Time;

public class Quantity {
    private Time startTime;
    private Date startDate;
    private Date endDate;
    private Time endTime;

    private int itemID;
    private int quantity;


    public Quantity(Date startDate, Time startTime, int itemID, int quantity){

        this.itemID = itemID;
        this.quantity = quantity;
        this.startDate = startDate;
        this.startTime = startTime;

    }


    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemID() {
        return itemID;
    }
}
