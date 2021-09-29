package com.example.assignment;

import androidx.fragment.app.FragmentManager;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class FragmentFactory {
    private static final HashMap listFragment = new HashMap();
    private static FragmentFactory instance;

    private FragmentFactory() {

    }

    public static FragmentFactory getInstance() {
        if (instance == null) {
            instance = new FragmentFactory();
        }
        return instance;
    }

    public BaseFragment getFragment(String fragmentName, OnActionCallback callback) {
        try {
            BaseFragment fragment = (BaseFragment) listFragment.get(fragmentName);
            if (fragment == null) {
                Class<?> aClass = Class.forName(fragmentName);
                Constructor<?> constructor = aClass.getConstructor();
                fragment = (BaseFragment) constructor.newInstance();
                listFragment.put(fragmentName, fragment);
                fragment.setCallback(callback);
            }
            return fragment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showFragment(String tag, OnActionCallback callback, FragmentManager fragmentManager, int containerId) {
        BaseFragment fragment = FragmentFactory.getInstance().getFragment(tag, callback);
        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .commit();
    }

}
