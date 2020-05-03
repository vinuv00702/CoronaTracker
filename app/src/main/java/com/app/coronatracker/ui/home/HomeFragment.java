package com.app.coronatracker.ui.home;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.coronatracker.R;
import com.app.coronatracker.ui.LottieDialogFragment;
import com.app.coronatracker.ui.home.model.Dashboard;
import com.app.coronatracker.ui.home.viewModel.HomeViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.angmarch.views.NiceSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView sglobal_text, sglobal_death, sglobal_recovered,
            slocal_recvrd_text, slocal_death_text, slocal_cnfd_text, slocal_actv_text;
    private View root;
    private HomeViewModel homeViewModel;
    private LottieDialogFragment  lottieDialogFragment ;
    private PieChart pieChart;
    private Spinner spinner;
    private TextView date;
    private Animation animation;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        setUpUI();
        setDate();
        lottieDialogFragment = new LottieDialogFragment().newInstance();
        prepareViewModel();
        observeViewModel();
        setPieChart();
        return root;
    }

    private void setDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd, MMM yyyy");
        String currentDate = simpleDateFormat.format(new Date());
        date.setText(currentDate);
    }

    private void setPieChart() {

//        Legend l = pieChart.getLegend();
//        l.setForm(Legend.LegendForm.CIRCLE);
//        l.setFormSize(20);
//        l.setTextSize(13);
//
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);

        pieChart.getLegend().setEnabled(true);
        pieChart.setUsePercentValues(true);

        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawSliceText(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(60f);

        //chartData();
    }

    private void chartData( ArrayList<PieEntry> chartData) {
        pieChart.animateY(1000, Easing.EaseInOutQuad);

        PieDataSet dataSet = new PieDataSet(chartData,"Corona Live Data");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(10);
        dataSet.setHighlightEnabled(true);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        //add colors to dataSet
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ContextCompat.getColor(getContext(),R.color.pieclr));
        colors.add(Color.RED);
        colors.add(ContextCompat.getColor(getContext(),R.color.btmNavIcnBgnd2));

        dataSet.setColors(colors);



        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(20);
        pieData.setValueFormatter(new PercentFormatter(pieChart));

        pieData.setValueTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
    }

    //preparing viewModel for the module
    private void prepareViewModel(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    private void setUpUI(){
        date = (TextView)root.findViewById(R.id.text_date);
        spinner =(Spinner)root.findViewById(R.id.state_spinner);
        pieChart = (PieChart)root.findViewById(R.id.chart);
        sglobal_text = (TextView)root.findViewById(R.id.global_text);
        sglobal_death = (TextView)root.findViewById(R.id.global_death);
        sglobal_recovered = (TextView)root.findViewById(R.id.global_recovered);
        slocal_recvrd_text = (TextView)root.findViewById(R.id.local_recvrd_text);
        slocal_death_text = (TextView)root.findViewById(R.id.local_death_text);
        slocal_cnfd_text = (TextView)root.findViewById(R.id.local_cnfd_text);
        slocal_actv_text = (TextView)root.findViewById(R.id.local_actv_text);
        animation = AnimationUtils.loadAnimation(getContext(),R.anim.text_anim);

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

//                progressDialogDismiss();
                setAnimation();
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
            Log.e(" mainAction", "  states - "+ st.getState());
            dataSet.add(st.getState());
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,dataSet);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select State");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                progressDialogDismiss();
                Log.e(" mainAction", "  selected state - "+ indianStateModels.get(i).getState());
                populateChart(preparePieChartEntry(i,indianStateModels));
                setCardView(i,indianStateModels);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setCardView(int i, ArrayList<HomeViewModel.IndianStateModel> indianStateModels) {

        setAnimation();
        slocal_recvrd_text.setText(Integer.toString(indianStateModels.get(i).getRecovered()));
        slocal_death_text.setText(Integer.toString(indianStateModels.get(i).getDeath()));
        slocal_cnfd_text.setText(Integer.toString(indianStateModels.get(i).getConfirmed()));
        slocal_actv_text.setText(Integer.toString(indianStateModels.get(i).getActive()));
    }

    private void setAnimation() {
        slocal_recvrd_text.startAnimation(animation);
        slocal_death_text.startAnimation(animation);
        slocal_cnfd_text.startAnimation(animation);
        slocal_actv_text.startAnimation(animation);

        sglobal_text.startAnimation(animation);
        sglobal_death.startAnimation(animation);
        sglobal_recovered.startAnimation(animation);
    }

    private ArrayList<PieEntry> preparePieChartEntry(int i, ArrayList<HomeViewModel.IndianStateModel> indianStateModels){
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(indianStateModels.get(i).getRecovered(),"Recovered"));
        yValues.add(new PieEntry(indianStateModels.get(i).getDeath(),"Deaths"));
        yValues.add(new PieEntry(indianStateModels.get(i).getConfirmed(),"Confirmed"));
        return  yValues;
    }

    private void populateChart(ArrayList<PieEntry> chartData){
        chartData(chartData);
    }

}