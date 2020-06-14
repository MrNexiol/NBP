package tomasz.kopycinski.nbp.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NBPApi {

    @GET("api/exchangerates/tables/{table}?format=json")
    Call<Table[]> getTable(@Path("table") char table);

    @GET("api/exchangerates/rates/{table}/{code}?format=json")
    Call<Currency> getCurrency(@Path("table") char table, @Path("code") String code);
}
