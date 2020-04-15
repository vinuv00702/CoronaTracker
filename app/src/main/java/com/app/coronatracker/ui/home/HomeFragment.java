package com.app.coronatracker.ui.home;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment  {

    private TextView sglobal_text, sglobal_death, sglobal_recovered;
    private View root;
    private NiceSpinner niceSpinner;
    private HomeViewModel homeViewModel;
    private LottieDialogFragment  lottieDialogFragment ;
    private PieChart pieChart;
    Spinner spinner;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        setUpUI();
        lottieDialogFragment = new LottieDialogFragment().newInstance();
        prepareViewModel();
        observeViewModel();
        setPieChart();
        return root;
    }

    private void setPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.GRAY);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(40f);

        chartData();
    }

    private void chartData() {
        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(2011978,"Cases"));
        yValues.add(new PieEntry(489626,"Recovered"));
        yValues.add(new PieEntry(127492,"Deaths"));
        yValues.add(new PieEntry(51571,"Critical"));

        pieChart.animateY(1000, Easing.EaseInOutQuad);

        PieDataSet dataSet = new PieDataSet(yValues,"Corona Live Data");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);
    }

    //preparing viewModel for the module
    private void prepareViewModel(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void setUpUI(){
        niceSpinner = (NiceSpinner) root.findViewById(R.id.state_spinner);
        spinner =(Spinner)root.findViewById(R.id.state_spinner2);
        pieChart = (PieChart)root.findViewById(R.id.chart);
        sglobal_text = (TextView)root.findViewById(R.id.global_text);
        sglobal_death = (TextView)root.findViewById(R.id.global_death);
        sglobal_recovered = (TextView)root.findViewById(R.id.global_recovered);
    }


    private void showProgressDialog(){
        lottieDialogFragment.show(getFragmentManager(),"");
    }

    private void progressDialogDismiss(){ lottieDialogFragment.dismiss(); }

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
                populatePicker(indianStateModels);
            }
        });
    }

    private void populatePicker(ArrayList<HomeViewModel.IndianStateModel> indianStateModels){
        List<String> dataSet = new LinkedList<>();
        for (HomeViewModel.IndianStateModel st : indianStateModels){
            Log.e(" mainAction", "  states - "+ st.getName());
            dataSet.add(st.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,dataSet);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        niceSpinner.attachDataSource(dataSet);
    }


}