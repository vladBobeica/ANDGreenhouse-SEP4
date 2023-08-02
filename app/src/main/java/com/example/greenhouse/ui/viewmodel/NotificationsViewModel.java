package com.example.greenhouse.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenhouse.model.GreenHouseModel;
import com.example.greenhouse.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<List<NotificationModel>> notificationsLiveData = new MutableLiveData<>();

    public NotificationsViewModel() {
        List<NotificationModel> initialNotifications = new ArrayList<>();

        initialNotifications.add(new NotificationModel(1, "Vlad's garden", "Alert 1"));
        initialNotifications.add(new NotificationModel(2,  "Alin's garden","Alert 2"));
        initialNotifications.add(new NotificationModel(3, "Pula=n cur", "Alert 3"));

        notificationsLiveData.setValue(initialNotifications);
    }

    public LiveData<List<NotificationModel>> getNotifications() {
        return notificationsLiveData;
    }
}

