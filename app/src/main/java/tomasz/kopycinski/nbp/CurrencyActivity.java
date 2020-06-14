package tomasz.kopycinski.nbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tomasz.kopycinski.nbp.models.Currency;
import tomasz.kopycinski.nbp.models.NBPApi;

public class CurrencyActivity extends AppCompatActivity {

    public static final String CUREENCY_CODE = "Code";
    public static final String CUREENCY_TABLE = "Table";

    private String code;
    private char table;
    private TextView currencyName;
    private TextView currencyCode;
    private TextView currencyMidBid;
    private TextView currencyAsk;
    private TextView currencyTable;
    private TextView currencyDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.currency_title);

        currencyName = findViewById(R.id.currencyName);
        currencyCode = findViewById(R.id.currencyCode);
        currencyMidBid = findViewById(R.id.currencyMidBid);
        currencyAsk = findViewById(R.id.currencyAsk);
        currencyTable = findViewById(R.id.currencyTable);
        currencyDate = findViewById(R.id.currencyDate);

        code = getIntent().getStringExtra(CUREENCY_CODE);
        table = getIntent().getCharExtra(CUREENCY_TABLE, 'A');

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nbp.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NBPApi nbpApi = retrofit.create(NBPApi.class);

        Call<Currency> call = nbpApi.getCurrency(table, code);

        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(@NonNull Call<Currency> call, @NonNull Response<Currency> response) {
                if(!response.isSuccessful()){
                    return;
                }

                Currency currency = response.body();
                assert currency != null;
                currencyName.setText(HelperMethods.capitalize(currency.getCurrency()));
                currencyName.setTypeface(null, Typeface.BOLD);
                currencyCode.append(currency.getCode());

                if(currency.getRates()[0].getMid() == 0){
                    currencyMidBid.setText(R.string.currency_bid_value);
                    currencyMidBid.append(String.valueOf(currency.getRates()[0].getBid()));
                    currencyAsk.setVisibility(View.VISIBLE);
                    currencyAsk.append(String.valueOf(currency.getRates()[0].getAsk()));
                }else{
                    currencyMidBid.append(String.valueOf(currency.getRates()[0].getMid()));
                    currencyAsk.setVisibility(View.GONE);
                }

                CurrencyActivity.this.currencyTable.append(String.valueOf(currency.getTable()));
                currencyDate.append(currency.getRates()[0].getEffectiveDate());

                String title = getString(R.string.currency_title);
                Objects.requireNonNull(getSupportActionBar()).setTitle(title + currency.getCode());
            }

            @Override
            public void onFailure(@NonNull Call<Currency> call, @NonNull Throwable t) {
                currencyName.setText(t.toString());
            }
        });
    }
}
