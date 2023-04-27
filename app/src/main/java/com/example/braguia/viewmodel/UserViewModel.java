package com.example.braguia.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.braguia.model.trails.Trail;
import com.example.braguia.model.user.User;
import com.example.braguia.repositories.TrailRepository;
import com.example.braguia.repositories.UserRepository;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import retrofit2.http.Body;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    public LiveData<User> user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository= new UserRepository(application);
        user = repository.getUser();
    }

    public void login(String username, String password, Context context, final LoginCallback callback) throws IOException {
        JsonObject body = new JsonObject();
        body.addProperty("username", username);
        body.addProperty("email", "");
        body.addProperty("password", password);

        repository.makeLoginRequest(body,context, new UserRepository.LoginCallback() {
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

    public LiveData<User> getUser() throws IOException {
        return user;
    }
}
