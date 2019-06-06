package logic.Sell_Buy;

import logic.Info.FinancialPeriod;
import logic.Info.Item;

import java.util.ArrayList;

/**
 * Created by Hana on 5/7/2019.
 */
public class Off {

    private FinancialPeriod financialPeriod;
    ArrayList<Item> items = new ArrayList<Item>();

    public FinancialPeriod getFinancialPeriod() {
        return financialPeriod;
    }

    public void setFinancialPeriod(FinancialPeriod financialPeriod) {
        this.financialPeriod = financialPeriod;
    }

}

