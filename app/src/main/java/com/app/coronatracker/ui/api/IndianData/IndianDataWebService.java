package com.app.coronatracker.ui.api.IndianData;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.coronatracker.ui.api.APIConstant;
import com.app.coronatracker.ui.api.utils.APIError;
import com.app.coronatracker.ui.api.utils.CTError;
import com.app.coronatracker.ui.home.model.State;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndianDataWebService implements IndianDataWebInterface {

    public class Result{
        @SerializedName("key_values")
        @Expose
        private String key_values;

        @SerializedName("state_wise")
        @Expose
        private HashMap<String, State> states = new HashMap<String, State>();;

        public String getKey_values() {
            return key_values;
        }

        public void setKey_values(String key_values) {
            this.key_values = key_values;
        }

        public HashMap<String, State> getStates() {
            return states;
        }

        public void setStates(HashMap<String, State> states) {
            this.states = states;
        }


    }


    IndianDataApi indianDataApi;
    MutableLiveData<CTError> apiError;
    public static  IndianDataWebService indianDataWebService;
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIConstant.coronaTrackerBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public IndianDataWebService(){
        apiError = new MutableLiveData<>();
        indianDataApi = retrofit.create(IndianDataApi.class);
    }

    // Singleton instance
    public static IndianDataWebService getInstance(){
        if (indianDataWebService == null){
            indianDataWebService = new IndianDataWebService();
        }
        return indianDataWebService;
    }

    @Override
    public MutableLiveData<ArrayList<State>> getStateWise() {

        final MutableLiveData<ArrayList<State>> liveData = new MutableLiveData<>();


        indianDataApi.getStateWise().enqueue(new Callback<IndianDataWebService.Result>() {
            @Override
            public void onResponse(Call<IndianDataWebService.Result> call, Response<IndianDataWebService.Result> response) {
                if(response.isSuccessful()){
                    Log.i("Api",response.body().states.toString());
                    HashMap<String, State> states1 =  response.body().getStates();
                    //Getting Collection of values from HashMap
                    Collection<State> values = states1.values();
                    //Creating an ArrayList of values
                    ArrayList<State> states = new ArrayList<State>(values);
                    liveData.setValue(states);
                }else {
                    APIError _apiError = new APIError();
                    _apiError.setCode(response.code());
                    _apiError.setMessage(response.message());
                    apiError.setValue(_apiError);
                }
            }

            @Override
            public void onFailure(Call<IndianDataWebService.Result> call, Throwable t) {
                APIError _apiError = new APIError();
                _apiError.setCode(-1000);
                _apiError.setMessage(t.getLocalizedMessage());
                apiError.setValue(_apiError);
            }

        });

        return liveData;
    }

    @Override
    public MutableLiveData<CTError> onError() {
        return apiError;
    }
}
