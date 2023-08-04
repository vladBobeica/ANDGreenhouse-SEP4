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
    private List<NotificationModel> notificationsList = new ArrayList<>();
    public NotificationsViewModel() {
        List<NotificationModel> initialNotifications = new ArrayList<>();

        initialNotifications.add(new NotificationModel(1, "Vlad's garden", "Temperature", "Temperature is too low!"));
        initialNotifications.add(new NotificationModel(2, "Alin's garden", "Humidity", "Humidity is too high!"));
        initialNotifications.add(new NotificationModel(3, "Pula=n cur", "Light", "Light is too low!"));

    }
    public LiveData<List<NotificationModel>> getNotifications() {
        return notificationsLiveData;
    }
    public void addNotification(NotificationModel notification) {
        notificationsList.add(notification);
        notificationsLiveData.setValue(notificationsList);
    }
}

