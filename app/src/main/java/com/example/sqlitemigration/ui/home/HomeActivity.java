package com.example.sqlitemigration.ui.home;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.lifecycle.ViewModelProvider;

import com.example.sqlitemigration.App;
import com.example.sqlitemigration.data.repository.UserRepository;
import com.example.sqlitemigration.databinding.ActivityHomeBinding;
import com.example.sqlitemigration.di.AppContainer;
import com.example.sqlitemigration.di.InlineFactory;
import com.example.sqlitemigration.ui.common.BaseActivity;
import com.example.sqlitemigration.utils.ConnectivityMonitor;
import com.example.sqlitemigration.utils.Logger;
import com.example.sqlitemigration.utils.Status;

import java.util.Objects;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // 1. Inflate and set content view
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Apply window insets
        applyWindowInsets(binding.getRoot());

        // 3. Get your repository
        AppContainer container = ((App) getApplication()).getAppContainer();
        UserRepository repository = container.userRepository();

        // 4. Create the ViewModel using an Anonymous Factory right here (THIS IS TO AVOID CREATING NEW FACTORY CLASS)
        // Using helper 'InlineFactory' class to use lambda here
        viewModel = new ViewModelProvider(this, InlineFactory.of(() -> new HomeViewModel(repository))).get(HomeViewModel.class);
        // OR
        /*
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            @SuppressWarnings("unchecked")
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                // You just pass the repository directly into the constructor here
                return (T) new HomeViewModel(repository);
            }
        }).get(HomeViewModel.class);
        */
        initViews();


    }

    private void initViews() {
        binding.downloadBtn.setOnClickListener(v -> {
            downloadUsers();
        });

    }

    private void downloadUsers() {
        if(!ConnectivityMonitor.isConnected(this)){
            Toast.makeText(this, "No Internet!", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.loadUsers();

        viewModel.getUsers().observe(this, listResource -> {
            if(listResource != null) {
                if (listResource.getStatus() == Status.LOADING){
                    binding.loadingCircle.setVisibility(View.VISIBLE);
                } else if (listResource.getStatus() == Status.SUCCESS) {
                    binding.loadingCircle.setVisibility(View.GONE);
                    if (listResource.getData() != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                            binding.dataTextView.setText(String.format("%s, SIZE: %d", listResource.getData().getFirst().getName(), listResource.getData().size()));
                        }
                    }
                } else if (listResource.getStatus() == Status.ERROR) {
                    binding.loadingCircle.setVisibility(View.GONE);
                    binding.dataTextView.setText(listResource.getMessage());
                }
            }
        });

    }
}

