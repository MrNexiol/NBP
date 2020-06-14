package tomasz.kopycinski.nbp.models;

public class Currency {
    private char table;
    private String currency;
    private String code;
    private CurrencyRate[] rates;

    public char getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public CurrencyRate[] getRates() {
        return rates;
    }
}
