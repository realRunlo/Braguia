package com.example.braguia.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.room.ColumnInfo;

import com.example.braguia.model.TrailMetrics.TrailMetrics;
import com.example.braguia.model.trails.Trail;
import com.example.braguia.model.user.User;
import com.example.braguia.repositories.TrailRepository;
import com.example.braguia.repositories.UserRepository;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.http.Body;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    public LiveData<User> user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository= new UserRepository(application,false);
        Log.e("DEBUG","New user View Model");
        user = repository.getUser();
    }

    public void login(String username, String password, Context context, final LoginCallback callback) throws IOException {
        repository.makeLoginRequest(username,password,context, new UserRepository.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                callback.onLoginSuccess();
            }

            @Override
            public void onLoginFailure() {
                callback.onLoginFailure();
            }
        });
    }

    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure();
    }

    public void logOut(Context context , LogOutCallback callback) throws IOException {
        repository.makeLogOutRequest(context, new UserRepository.LogoutCallback() {
            @Override
            public void onLogoutSuccess() {
                callback.onLogOutSuccess();
            }

            @Override
            public void onLogoutFailure() {
                callback.onLogOutFailure();
            }
        });
    }

    public interface LogOutCallback {
        void onLogOutSuccess();
        void onLogOutFailure();
    }

    public LiveData<User> getUser() throws IOException {
        return user;
    }

    public LiveData<List<TrailMetrics>> getMetrics(){
        return repository.getTrailMetrics();
    }

    public LiveData<TrailMetrics> getMetricsById(int id){
        return repository.getTrailMetricsById(id);
    }

    public void addMetrics(int trailId,float completedPercentage,float timeTaken,List<Integer> vizitedPins){
        repository.addTrailMetrics(trailId,
                completedPercentage,
                timeTaken,
                TrailMetrics.formatPinList(vizitedPins));
    }
}
