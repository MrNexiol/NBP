package tomasz.kopycinski.nbp.models;

import java.util.List;

public class Table {

    private char table;
    private String no;
    private String tradingDate;
    private String effectiveDate;
    private List<TableRate> rates;

    public char getTable() {
        return table;
    }

    public String getNo() {
        return no;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public List<TableRate> getRates() {
        return rates;
    }
}
