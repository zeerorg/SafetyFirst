package com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding;

/**
 * Created by Vikas on 26-07-2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vikas.dtu.safetyfirst.R;

public class ScaffoldingsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scaffolding_fragment, container, false);

        return view;
    }


}