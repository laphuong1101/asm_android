package com.example.assignment;

import java.util.List;

public interface OnMainActCallback extends OnActionCallback {

    void showListDataSearch(List<Task> taskList);
}
