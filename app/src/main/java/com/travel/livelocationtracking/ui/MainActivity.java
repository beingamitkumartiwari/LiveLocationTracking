package com.travel.livelocationtracking.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.travel.livelocationtracking.R;
import com.travel.livelocationtracking.db.SessionPrefs;
import com.travel.livelocationtracking.db.UserEntity;
import com.travel.livelocationtracking.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    SessionPrefs sessionPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionPrefs = new SessionPrefs(this);
        if (!sessionPrefs.isLogin()) {
            sessionPrefs.setLogin(true);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UsersRecyclerAdapter adapter = new UsersRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this, userEntities -> {
            // Update the cached copy of the words in the adapter.
            adapter.setWords(userEntities);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionPrefs.setLogin(false);
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(MainActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                }, 1000);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
