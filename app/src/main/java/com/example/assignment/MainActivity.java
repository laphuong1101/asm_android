package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnMainActCallback {
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        bottomNavigationView = findViewById(R.id.bnv_menu_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        FragmentFactory.getInstance().showFragment(ListTaskFrg.class.getName(), this, getSupportFragmentManager(), R.id.ln_main);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bottom_list) {
            FragmentFactory.getInstance().showFragment(ListTaskFrg.class.getName(), this, getSupportFragmentManager(), R.id.ln_main);
        } else if (item.getItemId() == R.id.bottom_add) {
            FragmentFactory.getInstance().showFragment(AddTaskFrg.class.getName(), this, getSupportFragmentManager(), R.id.ln_main);
        }else if (item.getItemId() == R.id.bottom_search) {
            FragmentFactory.getInstance().showFragment(SearchFrg.class.getName(), this, getSupportFragmentManager(), R.id.ln_main);
        }
        return true;
    }

    @Override
    public void showListDataSearch(List<Task> taskList) {
        FragmentFactory.getInstance().showFragment(ListTaskFrg.class.getName(), this, getSupportFragmentManager(), R.id.ln_main);
//        ListTaskFrg frg = ((ListTaskFrg) FragmentFactory.getInstance().getFragment(ListTaskFrg.class.getName(), this));
//        frg.getAllDataSearch(taskList);
        bottomNavigationView.getMenu().findItem(R.id.bottom_list).setChecked(true);
    }
}