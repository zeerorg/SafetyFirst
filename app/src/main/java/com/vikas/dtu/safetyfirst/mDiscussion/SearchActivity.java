package com.vikas.dtu.safetyfirst.mDiscussion;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mDiscussion.fragment.SearchPostFragment;

public class SearchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle bundle = getIntent().getExtras();
        String query = bundle.getString("search_query");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.search_frame_layout, SearchPostFragment.newInstance(query));
        ft.commit();
    }

}
