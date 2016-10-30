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

public class SuspensionScaffold extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspension_scaffold);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);
        String singlePointAdjustable = "A single-point adjustable scaffold consists of a platform suspended by one rope from an overhead support and equipped with means to permit the movement of the platform to desired work levels."+
                "The most common among these is the scaffold used by window washers to clean the outside of a skyscraper (also known as a boatswain's chair)."+
                "The supporting rope between the scaffold and the suspension device must be kept vertical unless designed by a qualified person, accessible to rescuers, protected from rubbing during direction changes.";
        String swingStage = "Two-point adjustable suspension scaffolds, also known as swing-stage scaffolds, are perhaps the most common type of suspended scaffold.  " +
                "Hung by ropes or cables connected to stirrups at each end of the platform, they are typically used by window washers on skyscrapers, but play a prominent role in high-rise construction as well." +
                "The safe use of a suspended scaffold begins with secure anchorage."+
                "The weight of the scaffold and its occupants must be supported by both the structure to which it is attached and by each of the scaffold components that make up the anchorage system."+
                "Adjustable suspension scaffolds are designed to be raised and lowered while occupied by workers and materials, and must be capable of bearing their load whether stationary or in motion. Because the platform is the work area of a suspended scaffold, an inspection requires safety checks of both the platform structure and how the platform is used by the workers. Even if a suspended scaffold has been assembled in compliance with every applicable standard, employers and workers must continue to exercise caution and use sound work practices to assure their safety. Extreme weather, excessive loads, or damage to structural components can all affect a scaffold's stability.";
        String multiPointAdjustable = "A multi-point adjustable scaffold consists of a platform (or platforms) suspended by more than two ropes from overhead supports and equipped with means to raise and lower the platform(s) to desired work levels."+
                "Multi-point adjustable scaffolds must be suspended from metal outriggers, brackets,  wire rope slings, hooks or means that meet equivalent criteria for strength, durability, etc."+
                "When two or more scaffolds are used they must not be bridged together unless design allows them to be connected,bridge connections are articulated and hoists are properly sized.";

        String multiLevel = "A multi-level scaffold is a two-point or multi-point adjustable suspension scaffold with a series of platforms at various levels resting on common stirrups."+
                "Multi-level suspended scaffolds must be equipped with additional independent support lines that are equal in number to the number of points supported, equal in strength to the suspension ropes and rigged to support the scaffold if the suspension ropes fail."+
                "Independent support lines and suspension ropes must not be anchored to the same points. Supports for platforms must be attached directly to support stirrups (not to other platforms).";
        String catenary = "A catenary scaffold is a scaffold consisting of a platform supported by two essentially horizontal and parallel ropes attached to structural members of a building or other structure."+
                "Platforms supported by wire rope must have hook-shaped stops on each of the platform to prevent them from slipping off the wire ropes. These hooks must be positioned so that they prevent the platform from falling if one of the horizontal wire ropes breaks."+
                "Wire ropes must not be over-tightened to the point that a scaffold load will overstress them. Wire ropes must be continuous and without splices between anchors. Each employee on a catenary scaffold must be protected by a personal fall-arrest system";
        String floatScaffold = "A float, or ship, scaffold is a suspension scaffold consisting of a braced platform resting on two parallel bearers and hung from overhead supports by ropes of fixed length."+
                "Platforms must be supported by and securely fastened to a minimum of two bearers extending at least 6 inches beyond the platform on both sides open connections must not allow the platform to shift or slip."+
                "When only two ropes are used with each float Ropes must be arranged to provide four ends that are securely fastened to overhead supports and Each employee on a float (ship) scaffold must be protected by a personal fall-arrest system.";
        String InteriorHug = "An interior hung suspension scaffold consists of a platform suspended from the ceiling or roof structure by fixed-length supports."+
                "Interior hung scaffolds must be suspended from roof structures (e.g., ceiling beams)."+
                "Roof structures must be inspected for strength before scaffolds are erected. Suspension ropes/cables must be connected to overhead supports by shackles, clips, thimbles, or equivalent means.";
        ExpandableTextView SinglePointAdjustable = (ExpandableTextView)findViewById(R.id.single_point_adjustable_scaffold);
        SinglePointAdjustable.setText(singlePointAdjustable);
        ExpandableTextView SwingStage = (ExpandableTextView)findViewById(R.id.swingStage);
        SwingStage.setText(swingStage);
        ExpandableTextView MultiPointAdjustable = (ExpandableTextView)findViewById(R.id.multiplepoint_scaffold);
        MultiPointAdjustable.setText(multiPointAdjustable);
        ExpandableTextView Multilevel = (ExpandableTextView)findViewById(R.id.multilevel_scaffold);
        Multilevel.setText(multiLevel);
        ExpandableTextView Catenary = (ExpandableTextView)findViewById(R.id.catenary_scaffold);
        Catenary.setText(catenary);
        ExpandableTextView FloatScaffolt = (ExpandableTextView)findViewById(R.id.float_scaffold);
        FloatScaffolt.setText(floatScaffold);
        ExpandableTextView InteriorHugScaffold = (ExpandableTextView)findViewById(R.id.interior_hung_scaffold);
        InteriorHugScaffold.setText(InteriorHug);

    }
}
