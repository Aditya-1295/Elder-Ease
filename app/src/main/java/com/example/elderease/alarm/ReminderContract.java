package com.example.elderease.alarm;

import com.example.elderease.BasePresenter;
import com.example.elderease.BaseView;
import com.example.elderease.source.History;
import com.example.elderease.source.MedicineAlarm;

/**
 * Created by aditya on Oct 31 20.
 */

public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
