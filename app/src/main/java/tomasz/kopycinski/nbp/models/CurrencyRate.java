package tomasz.kopycinski.nbp.models;

public class CurrencyRate {
    private String no;
    private String effectiveDate;
    private double mid;
    private double bid;
    private double ask;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public double getMid() {
        return mid;
    }

    public double getBid() {
        return bid;
    }

    public double getAsk() {
        return ask;
    }
}
