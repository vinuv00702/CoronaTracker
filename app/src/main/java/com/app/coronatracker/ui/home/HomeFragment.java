package com.app.coronatracker.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.coronatracker.R;
import com.app.coronatracker.ui.LottieDialogFragment;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.viewModel.HomeViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {

    public TextView sglobal_text, sglobal_death, sglobal_recovered;
    private View root;

    private HomeViewModel homeViewModel;
    ProgressDialog progressDialog;
    LottieDialogFragment  lottieDialogFragment ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        setUpUI();
        lottieDialogFragment = new LottieDialogFragment().newInstance();
        prepareViewModel();
        observeViewModel();
        return root;
    }

    //preparing viewModel for the module
    private void prepareViewModel(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void setUpUI(){
        sglobal_text = (TextView)root.findViewById(R.id.global_text);
        sglobal_death = (TextView)root.findViewById(R.id.global_death);
        sglobal_recovered = (TextView)root.findViewById(R.id.global_recovered);
    }

    private void setUpProgressHud(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void showProgressDialog(){
        lottieDialogFragment.show(getFragmentManager(),"");
    }

    private void progressDialogDismiss(){
        Toast.makeText(getContext(),"Loaded",Toast.LENGTH_SHORT).show();
        lottieDialogFragment.dismiss();

    }

    //Observe changes on viewmodel
    private void observeViewModel(){
        homeViewModel.init();
        showProgressDialog();
        observeGlobalData();
        observeIndianStates();
    }

    private void  observeGlobalData(){
        homeViewModel.getDashBoardData().observe(getViewLifecycleOwner(), new Observer<Dashboard>() {
            @Override
            public void onChanged(@Nullable Dashboard s) {

                progressDialogDismiss();
                Log.e(" mainAction", "  cases - "+ s.getCases());
                sglobal_text.setText(s.getCases());
                Log.e(" mainAction", "  death - "+ s.getDeaths());
                sglobal_death.setText(s.getDeaths());
                Log.e(" mainAction", "  recovered - "+ s.getRecovered());
                sglobal_recovered.setText(s.getRecovered());
            }
        });
    }

    private void observeIndianStates(){
        homeViewModel.getStates().observe(getViewLifecycleOwner(), new Observer<ArrayList<HomeViewModel.IndianStateModel>>() {
            @Override
            public void onChanged(ArrayList<HomeViewModel.IndianStateModel> indianStateModels) {
                for (HomeViewModel.IndianStateModel st : indianStateModels){
                    Log.e(" mainAction", "  states - "+ st.getName());
                }
            }
        });
    }


}