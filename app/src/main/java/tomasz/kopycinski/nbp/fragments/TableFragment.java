package tomasz.kopycinski.nbp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tomasz.kopycinski.nbp.R;
import tomasz.kopycinski.nbp.adapters.RateAdapter;
import tomasz.kopycinski.nbp.models.NBPApi;
import tomasz.kopycinski.nbp.models.Table;
import tomasz.kopycinski.nbp.models.TableRate;

public class TableFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private char tableCharacter;

    public TableFragment(char tableCharacter) {
        this.tableCharacter = tableCharacter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String title = getString(R.string.table_menu) + " " + tableCharacter;

        if(tableCharacter == 'A'){
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle(title);
        }else if(tableCharacter == 'B'){
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle(title);
        }else {
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setTitle(title);
        }

        View rootView = inflater.inflate(R.layout.fragment_table, container, false);
        List<TableRate> rates = new LinkedList<>();

        TextView tableCharacterView = rootView.findViewById(R.id.tableCharacter);
        TextView tableNumber = rootView.findViewById(R.id.tableNumber);
        TextView tableEffective = rootView.findViewById(R.id.tableEffectDate);
        TextView tableTrading = rootView.findViewById(R.id.tableTradingDate);

        recyclerView = rootView.findViewById(R.id.tableRecycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RateAdapter(rates, tableCharacter);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nbp.pl/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NBPApi nbpApi = retrofit.create(NBPApi.class);

        Call<Table[]> call = nbpApi.getTable(tableCharacter);

        call.enqueue(new Callback<Table[]>() {
            @Override
            public void onResponse(@NonNull Call<Table[]> call, @NonNull Response<Table[]> response) {
                if(!response.isSuccessful()){
                    return;
                }

                Table[] table = response.body();
                assert table != null;

                tableCharacterView.append(String.valueOf(table[0].getTable()));
                tableNumber.setText(table[0].getNo());
                tableEffective.append(table[0].getEffectiveDate());

                if(table[0].getTradingDate() != null) {
                    tableTrading.setVisibility(View.VISIBLE);
                    tableTrading.append(table[0].getTradingDate());
                }

                adapter = new RateAdapter(table[0].getRates(), tableCharacter);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<Table[]> call, @NonNull Throwable t) {

            }
        });

        return rootView;
    }
}
