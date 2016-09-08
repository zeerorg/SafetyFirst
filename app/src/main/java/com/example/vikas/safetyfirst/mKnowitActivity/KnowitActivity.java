package com.example.vikas.safetyfirst.mKnowitActivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.vikas.safetyfirst.BaseActivity;
import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.SignInActivity;
import com.example.vikas.safetyfirst.mKnowitActivity.Ladders.LaddersFragment;
import com.example.vikas.safetyfirst.mKnowitActivity.Ladders.mNonSelfSupportingLadder.NonSelfSupporting;
import com.example.vikas.safetyfirst.mKnowitActivity.Ladders.mSelfSupportingLadder.SelfSupporting;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.ScaffoldingsFragment;

import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.FrameScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.LadderJackScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.MobileScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.PoleScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.PumpJackScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.SpecialtyScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.SupportedScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.TubeCouplerScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.CatenaryScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.FloatScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.InteriorHungScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.MultiLevelScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.MutiPointAdjustable;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.NeedleBeamScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.SinglePointAdjustable;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.SuspendedScaffold;
import com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.SwingStageScaffold;
import com.google.firebase.auth.FirebaseAuth;

public class KnowitActivity extends BaseActivity {

    private static final String TAG = "KnowitActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowit);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new LaddersFragment(),
                    new ScaffoldingsFragment(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.ladders_heading),
                    getString(R.string.scaffolding_heading)
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



    public void startPumpJack(View view) {
        Intent intent = new Intent(this, PumpJackScaffold.class);
        startActivity(intent);
    }

    public void startSelfSupportingLadder(View view) {
        Intent intent = new Intent(this, SelfSupporting.class);
        startActivity(intent);
    }

    public void startNonSelfSupportingLadder(View view) {
        Intent intent = new Intent(this, NonSelfSupporting.class);
        startActivity(intent);
    }

    public void frame_text_view(View view) {
        Intent intent = new Intent(this, FrameScaffold.class);
        startActivity(intent);
    }

    public void mobile_text_view(View view) {
        Intent intent = new Intent(this, MobileScaffold.class);
        startActivity(intent);
    }

    public void tube_coupler_textView(View view) {
        Intent intent = new Intent(this, TubeCouplerScaffold.class);
        startActivity(intent);
    }

    public void ladder_jack_textView(View view) {
        Intent intent = new Intent(this, LadderJackScaffold.class);
        startActivity(intent);
    }

    public void pole_textView(View view) {
        Intent intent = new Intent(this, PoleScaffold.class);
        startActivity(intent);
    }

    public void speciality_textView(View view) {
        Intent intent = new Intent(this, SpecialtyScaffold.class);
        startActivity(intent);
    }

    public void self_supporting_scaffold_textView(View view) {
        Intent intent = new Intent(this, SupportedScaffold.class);
        startActivity(intent);
    }

    public void suspension_scaffolds_textView(View view) {
        Intent intent = new Intent(this, SuspendedScaffold.class);
        startActivity(intent);
    }

    public void single_point_adjustable_textView(View view) {
        Intent intent = new Intent(this, SinglePointAdjustable.class);
        startActivity(intent);
    }

    public void swing_scaffold_textView(View view) {
        Intent intent = new Intent(this, SwingStageScaffold.class);
        startActivity(intent);
    }

    public void multiple_point_adjustable_textView(View view) {
        Intent intent = new Intent(this, MutiPointAdjustable.class);
        startActivity(intent);
    }

    public void multi_level_scaffold_textView(View view) {
        Intent intent = new Intent(this, MultiLevelScaffold.class);
        startActivity(intent);
    }

    public void catenary_textView(View view) {
        Intent intent = new Intent(this, CatenaryScaffold.class);
        startActivity(intent);
    }

    public void float_scaffold_textView(View view) {
        Intent intent = new Intent(this, FloatScaffold.class);
        startActivity(intent);
    }

    public void interior_hung_scaffold_textView(View view) {
        Intent intent = new Intent(this, InteriorHungScaffold.class);
        startActivity(intent);
    }

    public void needle_beam_scaffold_textView(View view) {
        Intent intent = new Intent(this, NeedleBeamScaffold.class);
        startActivity(intent);
    }
}
