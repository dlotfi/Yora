package com.example.yora.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.example.yora.infrastructure.YoraApplication;

public abstract class BaseFragment extends Fragment {
    protected YoraApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (YoraApplication) getActivity().getApplication();
    }
}
