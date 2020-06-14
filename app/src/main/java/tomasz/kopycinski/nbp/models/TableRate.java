package tomasz.kopycinski.nbp.models;

public class TableRate {

    private String currency;
    private String code;
    private double bid;
    private double mid;
    private double ask;

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public double getBid() {
        return bid;
    }

    public double getMid() {
        return mid;
    }

    public double getAsk() {
        return ask;
    }
}