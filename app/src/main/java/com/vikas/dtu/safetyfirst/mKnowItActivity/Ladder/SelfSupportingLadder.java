package com.vikas.dtu.safetyfirst.mKnowItActivity.Ladder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mKnowItActivity.KnowItDetails1;

/**
 * Created by krishna on 17/10/16.
 */

public class SelfSupportingLadder extends BaseActivity {
    private boolean expandable = true;
    Button btnSeeMore;
    boolean expand = true;

    String yourText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Ut volutpat interdum interdum. Nulla laoreet lacus diam, vitae " +
            "sodales sapien commodo faucibus. Vestibulum et feugiat enim. Donec " +
            "semper mi et euismod tempor. Sed sodales eleifend mi id varius. Nam " +
            "et ornare enim, sit amet gravida sapien. Quisque gravida et enim vel " +
            "volutpat. Vivamus egestas ut felis a blandit. Vivamus fringilla " +
            "dignissim mollis. Maecenas imperdiet interdum hendrerit. Aliquam" +
            " dictum hendrerit ultrices. Ut vitae vestibulum dolor. Donec auctor ante" +
            " eget libero molestie porta. Nam tempor fringilla ultricies. Nam sem " +
            "lectus, feugiat eget ullamcorper vitae, ornare et sem. Fusce dapibus ipsum" +
            " sed laoreet suscipit. ";
    String twoWayLadder = "• Flat steps, hinged back\n" +
            "• Use on firm level footing\n"+
            "• Metal, wood, fiberglass\n"+
            "• Metal spreader or locking arms\n"+
            "• No work from top step\n"+
            "• 20 feet maximum length\n"+"• Flat steps, hinged back\n" +
            "• Use on firm level footing\n"+
            "• Metal, wood, fiberglass\n"+
            "• Metal spreader or locking arms\n"+
            "• No work from top step\n"+
            "• 20 feet maximum length\n";
    String orchardLadder = "A tripod orchard ladder is a portable, self-supporting ladder used in orchards and landscape maintenance, for tasks such as pruning and fruit harvesting.\n"+
            " They are designed with a flared base and a tripod pole that provides support on soft, uneven ground. In addition, these ladders have no spreader bar or locking mechanisms to hold its front in place or to stabilize the ladder.\n"+
            " For a tripod orchard ladder to function properly and remain stable, the ladder side rails and tripod pole must slightly penetrate the ground.";
    String trestleLadder = "•	The Trestle Ladder, also known as a Double Front Ladder, is a self-supporting portable ladder that is non-adjustable in length, and hinged at the top. It consists of two sections and is designed to be climbed by two persons at the same time, one on each side.\n"+
            "• Trestle Ladders range in lengths up to 20 ft. as measured along the side rail.  The highest standing level on a Trestle Ladder is slightly more than 2 ft. from the top of the ladder.\n"+
            "•	A Trestle Ladder requires level ground support for all four of its side rails.  If this work site condition does not exist, a Trestle Ladder should not be selected for the job.\n"+
            "• A Trestle Ladder must not be used unless its base is spread fully open and the Spreaders locked.";
    String platformLadder = "•	A platform ladder is a front step ladder with a platform as its top step.\n"+
            "• A top rail guard is usually around two feet higher than the platform to provide safety while you are working.\n"+
            "•	Platform ladders provide that higher level of comfort you look for while standing on a ladder for any amount of time.  You won’t be killing the bottom of your feet or your shins when you stand on the platform.\n"+
            "• This ladder allows you to work on any project that requires you to use two hands as safely as if you were on the ground itself.  Being able to rotate around and work in which ever direction you need helps you to work safer and faster.";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_supporting_ladder);
        // Get a support ActionBar corresponding to this toolbar

        Bundle b = getIntent().getExtras();

        ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);
      //  ExpandableTextView expandableTextView = (ExpandableTextView) findViewById(R.id.standard_ladder);
      // expandableTextView.setText(yourText);


       // ExpandableTextView twoWayLadderTextView = (ExpandableTextView)findViewById(R.id.two_way_ladder);
       // twoWayLadderTextView.setText(twoWayLadder);
       // ExpandableTextView orchardLadderTextView = (ExpandableTextView)findViewById(R.id.orchard_ladder);
       // orchardLadderTextView.setText(orchardLadder);
       // ExpandableTextView trestleLadderTextView = (ExpandableTextView)findViewById(R.id.trestle_ladder);
       // trestleLadderTextView.setText(trestleLadder);
       // ExpandableTextView platformLadderTextView = (ExpandableTextView)findViewById(R.id.platform_ladder);
       // platformLadderTextView.setText(platformLadder);

    }

    public void standardLadder(View view){

        Intent i = new Intent(SelfSupportingLadder.this, KnowItDetails1.class);
        i.putExtra("info", yourText );
        i.putExtra("image", R.drawable.standard_ladder);
        i.putExtra("check", "<a href=\"http://www.google.com\">http://www.google.com</a>");
        // i.putExtra(yourText, "howto" );
        // i.putExtra(yourText, "checklist" );
        // i.putExtra(yourText, "video" );
        startActivity(i);
    }

    public void twoWayLadder(View view){
        Intent i = new Intent(SelfSupportingLadder.this, KnowItDetails1.class);
         i.putExtra("info",twoWayLadder );
        i.putExtra("image", R.drawable.two_way_ladder);
        startActivity(i);
    }

    public void orchardLadder(View view){
        Intent i = new Intent(SelfSupportingLadder.this, KnowItDetails1.class);
         i.putExtra("info", orchardLadder );
        i.putExtra("image", R.drawable.orchard_ladder);
        startActivity(i);
    }

    public void trestleLadder(View view){
        Intent i = new Intent(SelfSupportingLadder.this, KnowItDetails1.class);
        i.putExtra("info", trestleLadder);
        i.putExtra("image", R.drawable.trestle_ladder);
        startActivity(i);
    }

    public void platformLadder(View view) {
        Intent i = new Intent(SelfSupportingLadder.this, KnowItDetails1.class);
        i.putExtra("info", platformLadder);
        i.putExtra("image", R.drawable.platform_ladder);
        startActivity(i);
    }


}
