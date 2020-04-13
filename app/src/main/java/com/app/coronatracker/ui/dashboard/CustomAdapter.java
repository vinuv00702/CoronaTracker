package com.app.coronatracker.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.coronatracker.R;
import com.app.coronatracker.ui.dashboard.model.Country;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    ArrayList<Country> countryArrayList;

    public CustomAdapter(Context context, ArrayList<Country> countryArrayList){
        this.context = context;
        this.countryArrayList = countryArrayList;
    }
    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_list,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        Country country = countryArrayList.get(position);
        holder.stext_country.setText(country.getCountry());
        holder.scaSes.setText("Cases: "+country.getCases());
        holder.stodayCases.setText("Today Cases: "+country.getTodayCases());
        holder.sdeAth.setText("Deaths: "+country.getDeaths());
        holder.stodayDeaths.setText("Today Deaths: "+country.getTodayDeaths());
        holder.srecoVered.setText("Recovered: "+country.getRecovered());
        holder.sacTive.setText("Active: "+country.getActive());
        holder.scriTical.setText("Critical: "+country.getCritical());
    }

    @Override
    public int getItemCount() {
        return countryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView stext_country, scaSes, stodayCases, sdeAth, stodayDeaths,
                                                        srecoVered, sacTive, scriTical;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stext_country =(TextView)itemView.findViewById(R.id.text_country);
            scaSes =(TextView)itemView.findViewById(R.id.text_cases);
            stodayCases =(TextView)itemView.findViewById(R.id.text_todayCases);
            sdeAth =(TextView)itemView.findViewById(R.id.text_death);
            stodayDeaths =(TextView)itemView.findViewById(R.id.text_todayDeath);
            srecoVered =(TextView)itemView.findViewById(R.id.text_recovered);
            sacTive =(TextView)itemView.findViewById(R.id.text_active);
            scriTical =(TextView)itemView.findViewById(R.id.text_critical);
        }
    }
}
