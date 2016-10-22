package com.vikas.dtu.safetyfirst.mKnowItActivity.Ladder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mKnowItActivity.ExpandableTextView;

/**
 * Created by krishna on 20/10/16.
 */

public class NonSelfSupportingLadder extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonself_supporting_ladder);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        String extensionLadder = " This type is used to reach high places and most be leaned against some type of support; such as a house, wall, or tree before it can be used." +
                "Most extension ladders consist of two parts:"+
                "⦁the main piece is called the 'base' and should always being firmly placed on the ground."+
                "⦁The second part is called the 'fly' and it is a moveable piece that can extend above the bed to allow the extension ladder to reach higher. Usually this sliding is done by use of a rope or hooks. This means most extension ladders have the ability to reach higher than you may first think when you see it 'out of the box'.";
        String singleLadder = "•	The Single Ladder is a non-self-supporting portable ladder that is non-adjustable in length, consisting of one section. It is intended for use by one person."+
                "• Single Ladders rated for heavy-duty or extra heavy-duty service range in length up to 30 feet as measured a long the side rail."+
                "•	 Single Ladders rated for medium duty service are available in lengths up to 24 feet, and those rated for light-duty service do not exceed 16 feet in length."+
                "• The ladder must also be tied to the upper access level before climbing on to or off the ladder at the upper level. The user must take care when getting on or off the ladder at the upper level in order to avoid tipping the ladder over sideways or causing the ladder base to slide out.";
        ExpandableTextView expandableTextView = (ExpandableTextView) findViewById(R.id.extension_ladder);
        expandableTextView.setText(extensionLadder);
        ExpandableTextView twoWayLadderTextView = (ExpandableTextView)findViewById(R.id.single_ladder);
        twoWayLadderTextView.setText(singleLadder);
    }
}
