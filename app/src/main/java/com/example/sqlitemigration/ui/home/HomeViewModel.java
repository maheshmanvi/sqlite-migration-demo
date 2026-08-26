package com.example.sqlitemigration.ui.home;



import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sqlitemigration.App;
import com.example.sqlitemigration.data.model.User;
import com.example.sqlitemigration.data.repository.UserRepository;
import com.example.sqlitemigration.di.AppContainer;
import com.example.sqlitemigration.utils.ConnectivityMonitor;
import com.example.sqlitemigration.utils.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private final UserRepository userRepository;


    public HomeViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    MutableLiveData<Resource<List<User>>> users = new MutableLiveData<>();

    public LiveData<Resource<List<User>>> getUsers() { return users; }


    public void loadUsers(){
        users.setValue(Resource.loading());
        userRepository.getUsers().enqueue(new Callback<>(){
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful() && response.body() != null)
                    users.setValue(Resource.success(response.body()));
                else
                    users.setValue(Resource.error("Unable to load users."));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.setValue(Resource.error(t.getLocalizedMessage() != null ? t.getLocalizedMessage() : "Something went wrong."));
            }
        });
    }

}
