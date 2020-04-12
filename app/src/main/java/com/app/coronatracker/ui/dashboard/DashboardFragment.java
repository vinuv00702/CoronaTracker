package com.app.coronatracker.ui.dashboard;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.coronatracker.R;
import com.app.coronatracker.ui.dashboard.model.Country;
import com.app.coronatracker.ui.dashboard.viewModel.DashboardViewModel;

import java.util.ArrayList;

import butterknife.BindView;

public class DashboardFragment extends Fragment {

//    @BindView(R.id.text_dashboard) TextView dummyTextView;
    @BindView(R.id.text_country) TextView stext_country;

    private ArrayList<Country> countryArrayList = new ArrayList<>();
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private DashboardViewModel dashboardViewModel;
    private LinearLayoutManager linearLayoutManager;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_countries, container, false);

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
        dashboardViewModel.init();
        dashboardViewModel.getDataRepository().observe(getViewLifecycleOwner(), new Observer<Country>() {
            @Override
            public void onChanged(@Nullable Country s) {
//                dummyTextView.setText(s);
//                stext_country.setText(s.getCountry());

            }
        });
    }
}
