package com.example.gestor_contas.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gestor_contas.model.UserAuth;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> userID;
    private MutableLiveData<String> userEmail;

    private UserAuth userAuth = new UserAuth();

    public HomeViewModel() {
        userID = new MutableLiveData<>();
        userID.setValue("User ID:"+userAuth.getCurrentUserUID());
        userEmail = new MutableLiveData<>();
        userEmail.setValue("User Email:"+userAuth.getCurrentUserEmail());
    }

    public LiveData<String> getUserID() {
        return userID;
    }
    public LiveData<String> getUserEmail() {
        return userEmail;
    }
}