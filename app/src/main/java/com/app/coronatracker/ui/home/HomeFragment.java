package com.app.coronatracker.ui.home;

import android.animation.ValueAnimator;
import android.os.Bundle;
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
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.viewModel.HomeViewModel;

import butterknife.BindView;

public class HomeFragment extends Fragment {

    @BindView(R.id.global_text) TextView sglobal_text;
    @BindView(R.id.global_recovered) TextView sglobal_recovered;
    @BindView(R.id.global_death) TextView sglobal_death;

//    public TextView sglobal_text, sglobal_death, sglobal_recovered;
    private View root;


    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

//        sglobal_text = (TextView)root.findViewById(R.id.global_text);
//        sglobal_death = (TextView)root.findViewById(R.id.global_death);
//        sglobal_recovered = (TextView)root.findViewById(R.id.global_recovered);

        prepareViewModel();
        setTextView();
//        getJsonData();
        startCountAnimation();
        return root;
    }

//    preparing viewModel for the module
    private void prepareViewModel(){

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

//    setting text from model to TextView
    private void setTextView(){

        homeViewModel.init();
        homeViewModel.getDataRepository().observe(getViewLifecycleOwner(), new Observer<Dashboard>() {
            @Override
            public void onChanged(@Nullable Dashboard s) {
                sglobal_text.setText(s.getCases());
                sglobal_death.setText(s.getDeaths());
                sglobal_recovered.setText(s.getRecovered());
            }
        });
    }

    private void startCountAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(9999999, 0);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                sglobal_text.setText(animation.getAnimatedValue().toString());
                sglobal_death.setText(animation.getAnimatedValue().toString());
                sglobal_recovered.setText(animation.getAnimatedValue().toString());

            }
        });
        animator.start();
    }



//    get Json data

//    public void getJsonData(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://coronavirus-19-api.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RetrofitApi service  = retrofit.create(RetrofitApi.class);
//        Call<Dashboard> call = service.getDashboardData();
//        call.enqueue(new Callback<Dashboard>() {
//            @Override
//            public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {
//                Log.e(" mainAction", "  response "+ response.body().toString());
//                Log.e(" mainAction", "  cases - "+ response.body().getCases().toString());
//                sglobal_text.setText(response.body().getCases().toString());
//
//                Log.e(" mainAction", "  cases - "+ response.body().getDeaths().toString());
//                sglobal_death.setText(response.body().getDeaths().toString());
//
//                Log.e(" mainAction", "  cases - "+ response.body().getRecovered().toString());
//                sglobal_recovered.setText(response.body().getRecovered().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Dashboard> call, Throwable t) {
//
//                Log.e("MainActivity ", "  error "+ t.toString());
//            }
//        });
//    }
}