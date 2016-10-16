package com.vikas.dtu.safetyfirst.mKnowitActivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.SignInActivity2;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.LaddersFragment;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.ExtensionLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.OrchardLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.PlatformLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.SingleLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.StandardLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.TrestleLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Ladders.Types.TwoWayLadder;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.ScaffoldingsFragment;

import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.FrameScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.LadderJackScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.MobileScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.PoleScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.PumpJackScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.SpecialtyScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.SupportedScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding.TubeCouplerScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.CatenaryScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.FloatScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.InteriorHungScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.MultiLevelScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.MutiPointAdjustable;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.NeedleBeamScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.SinglePointAdjustable;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.SuspendedScaffold;
import com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SuspendedScaffolding.SwingStageScaffold;
import com.google.firebase.auth.FirebaseAuth;

public class KnowitActivity extends BaseActivity {

    private static final String TAG = "KnowitActivity";

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowit);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


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
            startActivity(new Intent(this, SignInActivity2.class));
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

    public void startStandardLadder(View view) {
        Intent intent = new Intent(this, StandardLadder.class);
        startActivity(intent);
    }

    public void startOrchardLadder(View view) {
        Intent intent = new Intent(this, OrchardLadder.class);
        startActivity(intent);
    }

    public void startPlatformLadder(View view) {
        Intent intent = new Intent(this, PlatformLadder.class);
        startActivity(intent);
    }

    public void startSingleLadder(View view) {
        Intent intent = new Intent(this, SingleLadder.class);
        startActivity(intent);
    }

    public void startExtensionLadder(View view) {
        Intent intent = new Intent(this, ExtensionLadder.class);
        startActivity(intent);
    }

    public void startTrestleLadder(View view) {
        Intent intent = new Intent(this, TrestleLadder.class);
        startActivity(intent);
    }

    public void startTwoWayLadder(View view) {
        Intent intent = new Intent(this, TwoWayLadder.class);
        startActivity(intent);
    }
}
