package com.app.coronatracker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.coronatracker.R;
import com.app.coronatracker.ui.home.api.RetrofitApi;
import com.app.coronatracker.ui.home.model.HomeViewModel;
import com.app.coronatracker.ui.home.model.ModelPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

//    @BindView(R.id.global_text) TextView sglobal_text;
//    @BindView(R.id.global_recovered) TextView sglobal_recovered;
//    @BindView(R.id.global_death) TextView sglobal_death;

    TextView sglobal_text, sglobal_death, sglobal_recovered;


    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sglobal_text = (TextView)root.findViewById(R.id.global_text);
        sglobal_death = (TextView)root.findViewById(R.id.global_death);
        sglobal_recovered = (TextView)root.findViewById(R.id.global_recovered);
        prepareViewModel();
        setTextView();
        getJsonData();
        return root;
    }

//    preparing viewModel for the module
    private void prepareViewModel(){

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

//    setting text from model to TextView
    private void setTextView(){

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                global_text.setText(s);
            }
        });
    }

//    get Json data

    public void getJsonData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coronavirus-19-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi service  = retrofit.create(RetrofitApi.class);
        Call<ModelPojo> call = service.getJsonData();
        call.enqueue(new Callback<ModelPojo>() {
            @Override
            public void onResponse(Call<ModelPojo> call, Response<ModelPojo> response) {
                Log.e(" mainAction", "  response "+ response.body().toString());
                Log.e(" mainAction", "  cases - "+ response.body().getCases().toString());
                sglobal_text.setText(response.body().getCases().toString());

                Log.e(" mainAction", "  cases - "+ response.body().getDeaths().toString());
                sglobal_death.setText(response.body().getDeaths().toString());

                Log.e(" mainAction", "  cases - "+ response.body().getRecovered().toString());
                sglobal_recovered.setText(response.body().getRecovered().toString());
            }

            @Override
            public void onFailure(Call<ModelPojo> call, Throwable t) {

                Log.e("MainActivity ", "  error "+ t.toString());
            }
        });
    }
}