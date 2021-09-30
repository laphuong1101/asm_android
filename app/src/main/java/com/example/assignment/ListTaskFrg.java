package com.example.assignment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListTaskFrg extends BaseFragment<OnMainActCallback> implements TaskAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private Dialog dialog;

    public TaskAdapter getAdapter() {
        return adapter;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

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
    public void getAllDataSearch(List<Task> taskList) {
        this.taskList.clear();
        this.taskList.addAll(taskList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_list;
    }

    @Override
    public void deleteItem(Task task) {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Do you want to delete Task ?");
        alertDialog.setIcon(App.getInstance().getDrawable(R.drawable.ic_delete));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = task.getId();
                App.getInstance().getDatabase().queryData("DELETE FROM Task WHERE Id = " + id);
                getAllData();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alertDialog.show();
    }

    @Override
    public void editItem(Task task) {
        Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void detail(Task task) {
        if (dialog == null) {
            dialog = new Dialog(mContext);
        }

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.detail_item, null, false);
        ((EditText) itemView.findViewById(R.id.edt_taskName)).setText(task.getName());
        ((EditText) itemView.findViewById(R.id.edt_desc)).setText(task.getDescription());
        ((EditText) itemView.findViewById(R.id.edt_info)).setText(task.getInformation());
        ((EditText) itemView.findViewById(R.id.edt_money)).setText(task.getMoney());
        ((EditText) itemView.findViewById(R.id.edt_datetime)).setText(task.getDatetime());
        ((EditText) itemView.findViewById(R.id.edt_cate)).setText(task.getCategory());
        itemView.findViewById(R.id.btn_exit).setOnClickListener(this);
        dialog.setContentView(itemView);
        int with = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        dialog.getWindow().setLayout(with, height);


        dialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_exit) {
            dialog.dismiss();
        }
    }
}
