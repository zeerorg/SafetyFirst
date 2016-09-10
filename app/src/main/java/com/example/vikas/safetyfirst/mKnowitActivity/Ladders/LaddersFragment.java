package com.example.vikas.safetyfirst.mKnowitActivity.Ladders;

/**
 * Created by Vikas on 26-07-2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikas.safetyfirst.R;

public class LaddersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ladders, container, false);
        return view;
    }
}