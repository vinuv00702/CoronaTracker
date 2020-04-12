package com.app.coronatracker.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.coronatracker.R;
import com.app.coronatracker.ui.LottieDialogFragment;
import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.dashboard.viewModel.DashboardViewModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private ArrayList<Country> countryArrayList = new ArrayList<>();
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;
    private LinearLayoutManager linearLayoutManager;
    private View root;
    private LottieDialogFragment lottieDialogFragment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_countries, container, false);

        lottieDialogFragment = new LottieDialogFragment().newInstance();
        prepareViewModel();
        setTextView();
        initialization();
        return root;
    }

    private void initialization() {
        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.hasFixedSize();

        customAdapter = new CustomAdapter(getContext(), countryArrayList);
        recyclerView.setAdapter(customAdapter);
    }

//     preparing view model for the module
    private void prepareViewModel(){
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
    }

    // Setting text from model to TextView
    private void setTextView(){
        showProgressDialog();
        dashboardViewModel.init();
        dashboardViewModel.getGlobalDataRepository().observe(getViewLifecycleOwner(),
                                                        new Observer<ArrayList<Country>>() {
            @Override
            public void onChanged(ArrayList<Country> countryArrayList) {
                progressDialogDismiss();
                customAdapter.countryArrayList = countryArrayList;
                customAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showProgressDialog(){
        lottieDialogFragment.show(getFragmentManager(),"");
    }

    private void progressDialogDismiss(){
        Toast.makeText(getContext(),"Loaded",Toast.LENGTH_SHORT).show();
        lottieDialogFragment.dismiss();

    }
}
