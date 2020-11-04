package com.example.elderease;

import android.content.Context;
import androidx.annotation.NonNull;

import com.example.elderease.source.MedicineRepository;
import com.example.elderease.source.local.MedicinesLocalDataSource;




public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance(MedicinesLocalDataSource.getInstance(context));
    }
}
