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

import com.app.coronatracker.R;

import butterknife.BindView;

public class DashboardFragment extends Fragment {

    @BindView(R.id.text_dashboard) TextView dummyTextView;



    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_countries, container, false);
        prepareViewModel();
        setTextView();
        return root;
    }

    // preparing view model for the module
    private void prepareViewModel(){
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
    }

    // Setting text from model to TextView
    private void setTextView(){
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                dummyTextView.setText(s);
            }
        });
    }
}
