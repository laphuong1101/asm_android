package com.example.assignment;

import android.app.Dialog;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListTaskFrg extends BaseFragment<OnMainActCallback> implements TaskAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private Dialog dialogEdit;


    @Override
    protected void initViews() {

        App.getInstance().getDatabase().queryData("CREATE TABLE IF NOT EXISTS Task (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR(200)," +
                "description VARCHAR(200)," +
                "information VARCHAR(200)," +
                "money VARCHAR(200)," +
                "datetime VARCHAR(200)," +
                "category VARCHAR(200)" +
                ")");
        recyclerView = rootView.findViewById(R.id.recyclerView);
        adapter = new TaskAdapter(taskList, mContext, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        getAllData();
    }

    private void getAllData() {
        taskList.clear();
        Cursor cursor = App.getInstance().getDatabase().getData("SELECT * FROM Task");
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

        adapter.notifyDataSetChanged();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.frg_list;
    }

    @Override
    public void deleteItem(Task task) {

    }

    @Override
    public void editItem(Task task) {

    }

    @Override
    public void detail(Task task) {
        if (dialogEdit == null) {
            dialogEdit = new Dialog(mContext);
            dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.detail_item, null, false);
            dialogEdit.setContentView(itemView);
            int with = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            dialogEdit.getWindow().setLayout(with, height);

        }

        dialogEdit.show();
    }
}
