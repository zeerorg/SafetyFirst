package com.example.vikas.safetyfirst.mNewsActivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vikas.safetyfirst.BaseActivity;
import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.SignInActivity;
import com.example.vikas.safetyfirst.mDiscussion.fragment.RecentPostsFragment;
import com.example.vikas.safetyfirst.mKnowitActivity.Ladders.LaddersFragment;
import com.example.vikas.safetyfirst.mKnowitActivity.TabFragment2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class NewsActivity extends BaseActivity {
    //FragmentManager mFragmentManager;
   // FragmentTransaction mFragmentTransaction;

    private static final String TAG = "NewsActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

     //   mFragmentManager = getSupportFragmentManager();
     //   mFragmentTransaction = mFragmentManager.beginTransaction();
     //   mFragmentTransaction.replace(R.id.fragment_container,new NewsFragment()).commit();


        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new NewNewsFragment(),
                    new FavNewsFragment(),
                    new RecentNewsFragment(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.news_heading),
                    getString(R.string.fav_heading),
                    "Testing"
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
