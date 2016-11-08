package com.vikas.dtu.safetyfirst.mKnowItActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mKnowItActivity.AerialLift.AerialLiftActivity;
import com.vikas.dtu.safetyfirst.mKnowItActivity.CraneLift.CraneLiftActivity;
import com.vikas.dtu.safetyfirst.mKnowItActivity.FireSprinkler.FireSprinklerActivity;
import com.vikas.dtu.safetyfirst.mKnowItActivity.ForkLift.ForkLiftActivity;
import com.vikas.dtu.safetyfirst.mKnowItActivity.HeatStress.HeatStressActivity;
import com.vikas.dtu.safetyfirst.mKnowItActivity.Ladder.LadderActivity;
import com.vikas.dtu.safetyfirst.mKnowItActivity.Scaffoldings.Scaffolding;
import com.vikas.dtu.safetyfirst.mSignUp.SignInActivity;

import java.util.ArrayList;

/**
 * Created by krishna on 16/10/16.
 */
public class KnowItActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_it_list);
        final ArrayList<KnowIt> count = new ArrayList<KnowIt>();
        count.add(new KnowIt("Ladder", R.drawable.ladders_s));
       // count.add(new KnowIt("Scaffold", R.drawable.scaffolds_s));
        count.add(new KnowIt("Fork Lift", R.drawable.forlift_s));
        count.add(new KnowIt("Airlift", R.drawable.aeriall_lift_s));
        count.add(new KnowIt("Heat Stress", R.drawable.heat_stroke_s));
       // count.add(new KnowIt("Crane Lift", R.drawable.crane_s));
       // count.add(new KnowIt("Respiratory Lift", R.drawable.respiratort_s));
        count.add(new KnowIt("Safety Net", R.drawable.safety_nets_s));
        count.add(new KnowIt("Fire Sprinkler", R.drawable.sprinkler_s));
        KnowItAdapter itemsAdapter = new KnowItAdapter(this, count);
        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(itemsAdapter);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);
        ImageView imageView = (ImageView)findViewById(R.id.know_it_imageView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KnowIt knowIt = count.get(position);
               switch (knowIt.getmKnowItImage()) {
                    case R.drawable.ladders_s:
                       startActivity(new Intent(KnowItActivity.this, LadderActivity.class));
                        break;
                    case R.drawable.scaffolds_s:
                        Intent i = new Intent(KnowItActivity.this, Scaffolding.class);
                        startActivity(i);
                        break;
                   case R.drawable.aeriall_lift_s:
                       startActivity(new Intent(KnowItActivity.this, AerialLiftActivity.class));
                       break;
                   case R.drawable.crane_s:
                       startActivity(new Intent(KnowItActivity.this, CraneLiftActivity.class));
                       break;
                   case R.drawable.sprinkler_s:
                       startActivity(new Intent(KnowItActivity.this, FireSprinklerActivity.class));
                       break;
                   case R.drawable.forlift_s:
                       startActivity(new Intent(KnowItActivity.this, ForkLiftActivity.class));
                       break;
                   case R.drawable.heat_stroke_s:
                       startActivity(new Intent(KnowItActivity.this, HeatStressActivity.class));
                       break;
                   case R.drawable.respiratort_s:
                       startActivity(new Intent(KnowItActivity.this, RespiratoryLiftActivity.class));
                       break;
                   case R.drawable.safety_nets_s:
                       startActivity(new Intent(KnowItActivity.this, SafetyNetActivity.class));
                       break;
                    default:
                        break;
                }

            }
        });
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