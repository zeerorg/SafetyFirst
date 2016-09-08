package com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding;

/**
 * Created by Vikas on 26-07-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.PumpJack;

public class ScaffoldingsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scaffolding_fragment, container, false);

        TextView supported_sca = (TextView) view.findViewById(R.id.types_of_scaffolding1);
        supported_sca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SupportedScaffold.class);
                startActivity(intent);
            }
        });
        TextView suspended_sca = (TextView) view.findViewById(R.id.types_of_scaffolding2);
        suspended_sca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SuspendedScaffold.class);
                startActivity(intent);
            }
        });
        TextView specialuse_sca = (TextView) view.findViewById(R.id.types_of_scaffolding3);
        specialuse_sca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SpecialUseScaffold.class);
                startActivity(intent);
            }
        });


        return view;
    }


}
