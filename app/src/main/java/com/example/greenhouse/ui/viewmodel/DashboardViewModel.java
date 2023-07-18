package com.example.greenhouse.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.repository.GreenHouseRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<List<GreenHouseModel>> greenhousesLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private GreenHouseRepository repository = new GreenHouseRepository();

    public void fetchGreenhouses() {
        loadingLiveData.setValue(true);
        repository.getAllGreenHouses(new Callback<List<GreenHouseModel>>() {
            @Override
            public void onResponse(Call<List<GreenHouseModel>> call, Response<List<GreenHouseModel>> response) {
                loadingLiveData.setValue(false);
                if (response.isSuccessful()) {
                    List<GreenHouseModel> greenhouses = response.body();
                    greenhousesLiveData.setValue(greenhouses);
                } else {
                    errorLiveData.setValue("Failed to get greenhouses. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<GreenHouseModel>> call, Throwable t) {
                loadingLiveData.setValue(false);
                errorLiveData.setValue("Failed to get greenhouses. Error: " + t.getMessage());
            }
        });
    }

    public LiveData<List<GreenHouseModel>> getGreenhousesLiveData() {
        return greenhousesLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }
}