package com.example.assignment;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskFrg extends BaseFragment<OnMainActCallback>{
    private EditText edtName, edtDesc, edtInfo, edtMoney, edtDatetime, edtCate;

    @Override
    protected void initViews() {
        edtName = findViewById(R.id.edt_taskName);
        edtDesc = findViewById(R.id.edt_desc);
        edtInfo = findViewById(R.id.edt_info);
        edtMoney = findViewById(R.id.edt_money);
        edtDatetime = findViewById(R.id.edt_datetime);
        edtCate = findViewById(R.id.edt_cate);

        findViewById(R.id.btn_add, this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            String taskName = edtName.getText().toString();
            String desc = edtDesc.getText().toString();
            String info = edtInfo.getText().toString();
            String money = edtMoney.getText().toString();
            String dt = edtDatetime.getText().toString();
            String cate = edtCate.getText().toString();
            App.getInstance().getDatabase().queryData("INSERT INTO Task " +
                    "VALUES" +
                    "(null, " +
                    "'" + taskName + "'," +
                    "'" + desc + "'," +
                    "'" + info + "'," +
                    "'" + money + "'," +
                    "'" + dt + "'," +
                    "'" + cate + "'" +
                    ")");

            Toast.makeText(mContext, "Add new task success !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_add;
    }
}
