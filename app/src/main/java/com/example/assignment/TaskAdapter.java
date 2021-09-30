package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> taskList;
    private Context mContext;
    private OnItemClickListener listener;

    public TaskAdapter(List<Task> taskList, Context mContext, OnItemClickListener listener) {
        this.taskList = taskList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.act_item_task, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = taskList.get(position);
        holder.tvTaskName.setText(task.getName());
        holder.tvTaskName.setTag(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        private TextView tvTaskName;
        private ImageView ivDelete, ivEdit, ivDetail;
        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            tvTaskName = itemView.findViewById(R.id.tv_taskName);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDetail = itemView.findViewById(R.id.iv_detail);

            View.OnClickListener click = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.iv_delete) {
                        listener.deleteItem((Task) tvTaskName.getTag());
                    } else if (v.getId() == R.id.iv_edit) {
                        listener.editItem((Task) tvTaskName.getTag());
                    } else if (v.getId() == R.id.iv_detail) {
                        listener.detail((Task) tvTaskName.getTag());
                    }
                }
            };

            ivDelete.setOnClickListener(click);
            ivEdit.setOnClickListener(click);
            ivDetail.setOnClickListener(click);

        }
    }

    public interface OnItemClickListener {
        void deleteItem(Task task);
        void editItem(Task task);
        void detail(Task task);
    }
}
