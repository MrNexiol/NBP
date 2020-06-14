package tomasz.kopycinski.nbp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import tomasz.kopycinski.nbp.CurrencyActivity;
import tomasz.kopycinski.nbp.HelperMethods;
import tomasz.kopycinski.nbp.R;
import tomasz.kopycinski.nbp.models.TableRate;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {

    private List<TableRate> data;
    private char tableCharacter;

    static class RateViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        TextView currencyName;
        TextView currencyCode;
        TextView currencyMidBid;
        TextView currencyAsk;

        RateViewHolder(View v){
            super(v);
            constraintLayout = v.findViewById(R.id.parent_c_currency);
            currencyName = v.findViewById(R.id.currencyName);
            currencyCode = v.findViewById(R.id.currencyCode);
            currencyMidBid = v.findViewById(R.id.currencyMidBid);
            currencyAsk = v.findViewById(R.id.currencyAsk);
        }
    }

    public RateAdapter(List<TableRate> data, char tableCharacter){
        this.data = data;
        this.tableCharacter = tableCharacter;
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item,parent,false);
        return new RateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        holder.currencyName.setText(HelperMethods.capitalize(data.get(position).getCurrency()));
        holder.currencyCode.setText(data.get(position).getCode());
        if(data.get(position).getMid() != 0){
            holder.currencyMidBid.setText(String.valueOf(data.get(position).getMid()));
        }else{
            holder.currencyMidBid.setText(String.valueOf(data.get(position).getBid()));
            holder.currencyAsk.setText(String.valueOf(data.get(position).getAsk()));
        }
        holder.constraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(holder.constraintLayout.getContext(), CurrencyActivity.class);
            intent.putExtra(CurrencyActivity.CUREENCY_TABLE, tableCharacter);
            intent.putExtra(CurrencyActivity.CUREENCY_CODE, data.get(position).getCode());
            holder.constraintLayout.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
