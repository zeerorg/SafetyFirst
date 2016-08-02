package com.example.vikas.safetyfirst.mKnowitActivity.Ladders;

/**
 * Created by Vikas on 26-07-2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mKnowitActivity.Ladders.mNonSelfSupportingLadder.NonSelfSupporting;
import com.example.vikas.safetyfirst.mKnowitActivity.Ladders.mSelfSupportingLadder.SelfSupporting;

public class Ladders extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ladders, container, false);

        ImageButton non_self_supporting_ladder = (ImageButton) view.findViewById(R.id.non_self_supporting_ladder);
        non_self_supporting_ladder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NonSelfSupporting.class);
                startActivity(intent);
              //  Toast.makeText(getActivity(), "non_self_supporting_ladder", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton self_supporting_ladder = (ImageButton) view.findViewById(R.id.self_supporting_ladder);
        self_supporting_ladder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelfSupporting.class);
                startActivity(intent);
               // Toast.makeText(getActivity(), "self_supporting_ladder", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}