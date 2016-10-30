package com.vikas.dtu.safetyfirst.mKnowItActivity.Ladder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mKnowItActivity.ExpandableTextView;

/**
 * Created by krishna on 17/10/16.
 */

public class LadderActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);
        String Ladder = "Ladders are used when employees need to move up or down between two different levels. Slips, trips, and falls are significant contributors to accidents. Slips, trips, and falls can occur when wrong ladder selection is made and when improper climbing techniques and/or defective ladders are used."+
                "\nAppropriate ladders must be used for the corresponding job and defective ladders will not be used. When hazards exist that cannot be eliminated, then engineering practices, administrative practices, safe work practices, Personal Protective Equipment (PPE), and proper training regarding ladders must be implemented. "+
                "\nBasic Ladder Safety Rules:"+
                "\n• All wood parts must be free from sharp edges and splinters; sound and free from accepted visual inspection from shake, wane, compression failures, decay, or other irregularities."+
                "\n• Ladders must be maintained in good condition at all times, the joint between the steps and side rails must be tight, all hardware and fittings securely attached, and the movable parts shall operate freely without binding or undue play."+
                "\n• Metal bearings of locks, wheels, pulleys, etc., shall be frequently lubricated."+
                "\n• Ladders must be inspected frequently and those which have developed defects must be withdrawn from service for repair or destruction and tagged or marked as \"Dangerous, Do Not Use.\" "+
                "\n• Ladders with broken or missing steps, rungs, or cleats, broken side rails, or other faulty equipment shall not be used; improvised repairs are not allowed.";
        ExpandableTextView LadderTextView = (ExpandableTextView)findViewById(R.id.laddertextview);
        LadderTextView.setText(Ladder);
    }

    public void selfSupportingLadder(View view) {
        Intent intent = new Intent(this,SelfSupportingLadder.class);
        startActivity(intent);
    }

    public void nonSelfSupportingLadder(View view) {
        Intent intent = new Intent(this,NonSelfSupportingLadder.class);
        startActivity(intent);

    }
}
