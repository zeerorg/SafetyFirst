package com.vikas.dtu.safetyfirst2.mKnowItActivity.Ladder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mKnowItActivity.KnowItComponent;
import com.vikas.dtu.safetyfirst2.mKnowItActivity.KnowItListAdapter;

import java.util.ArrayList;
import java.util.List;

public class LadderList extends AppCompatActivity {
    private List<KnowItComponent> knowItComponents;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.know_it_list);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        knowItComponents = new ArrayList<>();
        knowItComponents.add(new KnowItComponent("Emma Wilson", R.drawable.ic_launcher));
        knowItComponents.add(new KnowItComponent("Lavery Maiss", R.drawable.ic_launcher));
        knowItComponents.add(new KnowItComponent("Lillie Watts", R.drawable.ic_launcher));

    }

    private void initializeAdapter(){
        KnowItListAdapter adapter = new KnowItListAdapter(knowItComponents);
        rv.setAdapter(adapter);
    }
}
