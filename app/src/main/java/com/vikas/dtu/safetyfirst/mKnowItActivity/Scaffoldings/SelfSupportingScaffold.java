package com.vikas.dtu.safetyfirst.mKnowItActivity.Scaffoldings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mKnowItActivity.ExpandableTextView;

/**
 * Created by krishna on 20/10/16.
 */

public class SelfSupportingScaffold extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_supporting_scaffold);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        String frameScaffold = "• The most common type of scaffold because they are versatile, economical, and easy to use."+
                "• They are frequently used in one or two tiers by residential contractors, painters, etc., but their modular frames can also be stacked several stories high for use on large-scale construction jobs.";
        String mobileScaffold = "•  Mobile scaffolds are a type of supported scaffold set on wheels or casters"+
                "• They are designed to be easily moved and are commonly used for things like painting and plastering, where workers must frequently change position."+
                "• Scaffolds must be plumb, level, and squared.All brace connections must be secured."+
                "• To prevent movement of the scaffold while it is being used in a stationary position, scaffold casters and wheels must be locked with positive wheel locks "+
                "• Platforms must not extend beyond the base supports of the scaffold, unless stability is ensured by outrigger frames Leveling of the scaffold, where necessary, must be achieved by the use of screw jacks.";
        String pumpJack = "• Pump jacks are a uniquely designed scaffold consisting of a platform supported by move able brackets on vertical poles."+
                "• The brackets are designed to be raised and lowered in a manner similar to an automobile jack."+
                "• Pump jack brackets, braces, and accessories must be fabricated from metal plates and angles."+
                "• Each pump jack bracket must have two positive gripping mechanisms to prevent any failure or slippage. When bracing already installed has to be removed so the pump jack can pass, an additional brace must be installed approximately 4 feet above the original brace before it is removed."+
                "• The additional brace must be left in place until the pump jack has been moved and the original brace reinstalled.";
        String tubeCoupler= "• Tube and coupler scaffolds are so-named because they are built from tubing connected by coupling devices."+
                "• Due to their strength, they are frequently used where heavy loads need to be carried, or where multiple platforms must reach several stories high."+
                "• Their versatility, which enables them to be assembled in multiple directions in a variety of settings, also makes them hard to build correctly."+
                "• Each pump jack bracket must have two positive gripping mechanisms to prevent any failure or slippage. When bracing already installed has to be removed so the pump jack can pass, an additional brace must be installed approximately 4 feet above the original brace before it is removed."+
                "• The additional brace must be left in place until the pump jack has been moved and the original brace reinstalled.";
        String ladderJack = "•  A ladder jack scaffold is a simple device consisting of a platform resting on brackets attached to a ladder."+
                "• Ladder jacks are primarily used in light applications because of their portability and cost effectiveness."+
                "• All ladders used to support ladder jack scaffolds must comply with-Stairways and Ladders."+
                "• Ladder jacks must be designed and constructed to bear on the side rails and ladder rungs. Ladders used to support ladder jack scaffolds must be placed to prevent slipping fastened to prevent slipping and or equipped with devices to prevent slipping. ";
        String poleScaffold = "• Pole scaffolds are a type of supported scaffold in which every structural component, from uprights to braces to platforms, is made of wood."+
                "• OSHA has standards for two kinds: single-pole, which are supported on their interior side by a structure or wall, and double-pole, which are supported by double uprights independent of any structure."+
                "• When platforms are moved to the next level, the existing platform must be left undisturbed until the new bearers have been set in place and braced. Pole scaffolds over 60 feet in height must be designed by a registered professional engineer.";
        String specialityScaffold ="Special use scaffolds and assemblies are capable of supporting their own weight and at least 4 times the maximum intended load. The types of special use scaffolds include:"+
                "\nForm and Carpenter Bracket\n"+
                "Roof Bracket\n"+
                "Outrigger\n"+
                "Pump Jack\n"+
                "Ladder Jack\n"+
                "Window Jack\n"+
                "Horse\n"+
                "Crawling Boards\n"+
                "Step, Platforms, and Trestle Ladder";
        ExpandableTextView FrameScaffold = (ExpandableTextView)findViewById(R.id.frame_scoffold);
        FrameScaffold.setText(frameScaffold);
        ExpandableTextView MobileScaffold = (ExpandableTextView)findViewById(R.id.mobileScaffold);
        MobileScaffold.setText(mobileScaffold);
        ExpandableTextView PumpJack = (ExpandableTextView)findViewById(R.id.pump_jack);
        PumpJack.setText(pumpJack);
        ExpandableTextView TubeCoupler = (ExpandableTextView)findViewById(R.id.tube_coupler);
        TubeCoupler.setText(tubeCoupler);
        ExpandableTextView LadderJack = (ExpandableTextView)findViewById(R.id.ladder_jack_scaffold);
        LadderJack.setText(ladderJack);
        ExpandableTextView PoleScaffold = (ExpandableTextView)findViewById(R.id.pole_scaffold);
        PoleScaffold.setText(poleScaffold);
        ExpandableTextView SpecialityScaffold = (ExpandableTextView)findViewById(R.id.speciality_scaffold);
        SpecialityScaffold.setText(specialityScaffold);
    }
}
