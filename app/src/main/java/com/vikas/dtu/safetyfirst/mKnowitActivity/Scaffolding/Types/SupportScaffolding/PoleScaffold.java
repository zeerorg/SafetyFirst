package com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vikas.dtu.safetyfirst.R;

/**
 * Created by Golu on 9/8/2016.
 */
public class PoleScaffold extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pole_scaffold);
    }
}
