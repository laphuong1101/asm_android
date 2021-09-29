package com.example.assignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<C extends OnActionCallback> extends Fragment implements View.OnClickListener{
    protected Context mContext;
    protected View rootView;
    protected C callback;

    public final void setCallback(C callback) {
        this.callback = callback;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initViews();
        return rootView;
    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

    public final  <T extends View> T findViewById(int id) {
        return rootView.findViewById(id);
    }

    public final <T extends View> T findViewById(int id, View.OnClickListener onClickListener) {
        T view = rootView.findViewById(id);
        if (view != null && onClickListener != null) {
            view.setOnClickListener(onClickListener);
        }
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        rootView = null;
        mContext = null;
        super.onDestroy();
    }
}
