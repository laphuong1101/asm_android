package com.example.assignment;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchFrg extends BaseFragment<OnMainActCallback> implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText edtKeyword;
    private String rs = "";
    private boolean flag = false;
    private List<String> searchBy = new ArrayList<>();
    private List<Task> taskList = new ArrayList<>();
    @Override
    protected void initViews() {
        flag = false;
        searchBy.add("Choose Type Select");
        searchBy.add("Name");
        searchBy.add("Category");
        searchBy.add("Datetime");
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, searchBy);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        edtKeyword = findViewById(R.id.edt_keyword);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_search;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (flag == false) {
            flag = true;
            return;
        } else {
            if (searchBy.get(i).equals("Name")) {
                flag = false;
                getAllData("Name", edtKeyword.getText().toString());
                callback.showListDataSearch(taskList);
            } else if (searchBy.get(i).equals("Category")) {
                flag = false;
                getAllData("category", edtKeyword.getText().toString());
            } else if (searchBy.get(i).equals("Datetime")) {
                flag = false;
            } else if (searchBy.get(i).equals("Choose Type Select")) {
                flag = false;
                return;
            }


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        rs = "";
    }

    private void getAllData(String condition, String keyword) {
        taskList.clear();
        Cursor cursor = App.getInstance().getDatabase().getData("SELECT * FROM Task WHERE " + condition + "=" + "'" + keyword + "'");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String taskName = cursor.getString(1);
            String description = cursor.getString(2);
            String information = cursor.getString(3);
            String money = cursor.getString(4);
            String datetime = cursor.getString(5);
            String category = cursor.getString(6);
            taskList.add(new Task(id, taskName, description, information, money, datetime, category));
        }

    }
}
