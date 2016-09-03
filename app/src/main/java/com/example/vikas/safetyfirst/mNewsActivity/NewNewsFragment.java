package com.example.vikas.safetyfirst.mNewsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mFirebase.FireBaseClient;

/**
 * Created by Vikas on 26-07-2016.
 */
public class NewNewsFragment extends Fragment {
    private static final String TAG = "NewNewsFragment";
    final static String DB_URL="https://youngman-783f3.firebaseio.com/";
    RecyclerView rv;
    FireBaseClient fireBaseClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        rv = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        fireBaseClient=new FireBaseClient(getActivity(),DB_URL,rv);

        fireBaseClient.refreshData();

        return rv;
    }
}
