package logic.Info;

import java.util.Date;

/**
 * Created by Hana on 6/6/2019.
 */
public class FinancialPeriod {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    Date startDate;
    Date endDate;


}
