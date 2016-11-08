package com.vikas.dtu.safetyfirst2.mKnowItActivity.Ladder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mKnowItActivity.KnowItDetails.KnowItDetail;

/**
 * Created by krishna on 17/10/16.
 */

public class LadderTypes extends BaseActivity {
    String yourText;
    String twoWayLadder;
    String orchardLadder;
    String trestleLadder;
    String platformLadder;


    String TRESTLELADDER_info = "●	Self-supporting portable ladder, Non adjustable in length, \n Consists of two sections hinged at the top to form equal angles with the base. \n The size is designated by the length of the side rails measured along the front edge.";

    String TRESTLELADDER_howto = "Proper Use\n An Extension Trestle Ladder requires level ground support for all four of its side rails. If this work site condition does not exist, an Extension Trestle Ladder should not be selected for the job.\n An Extension Trestle Ladder must not be used unless its base is spread fully open and the Spreaders locked. Extension Trestle Ladders are not to be used as Single Ladders or in the partially open position."+
    "In order to prevent tipping the ladder over sideways due to over-reaching, the user(s) must climb or work with the body near the middle of the steps or rungs. The ladder should be set-up close to the work. Never attempt to move the ladder without first descending, relocating the ladder, and then re-climbing. Do not attempt to mount the ladder from the side or step from one ladder to another unless the ladder is secured against sideways motion."+

    "In an effort to avoid losing your balance and falling off the Extension Trestle Ladder, the user must not step or stand higher than the step indicated on the label marking the highest standing level. When ascending or descending the ladder, always face the ladder and maintain a firm hand hold. Do not attempt to carry other objects in your hand(s) while climbing."+
    "The anti-slip feet at the bottom of the base section side rails must be present and in good condition prior to using the ladder. The ladder must not be used on ice, snow or slippery surfaces unless suitable means to prevent slipping is employed."+
    "An Extension Trestle Ladder must never be placed upon other objects such as boxes, barrels, scaffolds, or other unstable bases in an effort to obtain additional height."+
    "Proper Care"+
    "A thorough inspection must be made when the ladder is initially purchased and each time it is placed into service. Clean the climbing and gripping surfaces if they have been subjected to oil, grease or slippery materials. Working parts, bolts, rivets, step-to-side rail connections, and the condition of the anti-slip feet (safety shoes) shall be checked."+
    "Ladders exposed to excessive heat, as in the case of fire, may have reduced strength. Similarly, ladders exposed to corrosive substances such as acids or alkali materials may experience chemical corrosion and a resulting reduction in strength. Remove these ladders from service."+
    "Broken or bent ladders, and ladders with missing or worn out parts must be taken out of service and marked, for example, Dangerous – Do Not Use” until repaired by a competent mechanic or destroyed. No attempt shall be made to repair a ladder with a defective side rail. Ladders with bent or broken side rails must be destroyed."+
    "In the event a ladder is discarded, it must be destroyed in such a manner as to render it useless. Another person must not be given the opportunity to use a ladder that has been deemed unsafe."+
    "When transporting ladders on vehicles equipped with ladder racks, the ladders must be properly supported. Overhang of the ladders beyond the support points of the rack should be minimized. The support points should be constructed of material such as wood or rubber-covered pipe to minimize the effects of vibration, chafing and road shock. Securing the ladder to each support point will greatly reduce the damaging effects of road shock."+
    "Storage racks for ladders not in use should have sufficient supporting points to avoid sagging which can result in warping the ladder. Other materials must not be placed on the ladder while it is in storage."+
    "Extension Trestle Ladder Safety Standards"+
    "Safety requirements for Construction, Performance, Use and Care of Extension Trestle Ladders can be found in the following standards:"+
            "   ●	ANSI A14.1 (Portable Wood Ladders)"+
            "   ●	ANSI A14.2 (Portable Metal Ladders)"+
            "   ●	ANSI A14.5 (Portable Reinforced Plastic Ladders)";

    String TRESTLELADDER_checklist = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=9&ved=0ahUKEwi42_7MvfbPAhUMPY8KHWXBAvsQFgg5MAg&url=http%3A%2F%2Fwww.kelsi.org.uk%2F__data%2Fassets%2Fword_doc%2F0011%2F27884%2FLadder-safety-Ladders-checklist-3-monthly-inspection.doc&usg=AFQjCNFQ6VaVt0WmMQeJNlp1rP2DGjCbZg&bvm=bv.136593572,d.c2I&cad=rja";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_supporting_ladder);
        // Get a support ActionBar corresponding to this toolbar
       ActionBar ab = getSupportActionBar();

        // Enable the Up round_blue_dark
        ab.setDisplayHomeAsUpEnabled(true);
      yourText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
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
        twoWayLadder = "• Flat steps, hinged back\n" +
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
        orchardLadder = "A tripod orchard ladder is a portable, self-supporting ladder used in orchards and landscape maintenance, for tasks such as pruning and fruit harvesting.\n"+
                " They are designed with a flared base and a tripod pole that provides support on soft, uneven ground. In addition, these ladders have no spreader bar or locking mechanisms to hold its front in place or to stabilize the ladder.\n"+
                " For a tripod orchard ladder to function properly and remain stable, the ladder side rails and tripod pole must slightly penetrate the ground.";
      platformLadder = "•	A platform ladder is a front step ladder with a platform as its top step.\n"+
                "• A top rail guard is usually around two feet higher than the platform to provide safety while you are working.\n"+
                "•	Platform ladders provide that higher level of comfort you look for while standing on a ladder for any amount of time.  You won’t be killing the bottom of your feet or your shins when you stand on the platform.\n"+
                "• This ladder allows you to work on any project that requires you to use two hands as safely as if you were on the ground itself.  Being able to rotate around and work in which ever direction you need helps you to work safer and faster.";
    }

    public void standardLadder(View view){

        Intent i = new Intent(LadderTypes.this, KnowItDetail.class);
        i.putExtra("info", yourText );
        i.putExtra("image", R.drawable.standard_ladder);
        // i.putExtra(yourText, "howto" );
        // i.putExtra(yourText, "checklist" );
        // i.putExtra(yourText, "video" );
        startActivity(i);
    }

    public void twoWayLadder(View view){
        Intent i = new Intent(LadderTypes.this, KnowItDetail.class);
         i.putExtra("info",twoWayLadder );
        i.putExtra("image", R.drawable.two_way_ladder);
        startActivity(i);
    }

    public void orchardLadder(View view){
        Intent i = new Intent(LadderTypes.this, KnowItDetail.class);
         i.putExtra("info", orchardLadder );
        i.putExtra("image", R.drawable.orchard_ladder);
        startActivity(i);
    }

    public void trestleLadder(View view){
        Intent i = new Intent(LadderTypes.this, KnowItDetail.class);
        i.putExtra("info", TRESTLELADDER_info);
        i.putExtra("image", R.drawable.trestle_ladder);
        i.putExtra("howto", TRESTLELADDER_howto);
        i.putExtra("checklist", TRESTLELADDER_checklist);
        startActivity(i);
    }

    public void platformLadder(View view) {
        Intent i = new Intent(LadderTypes.this, KnowItDetail.class);
        i.putExtra("info", platformLadder);
        i.putExtra("image", R.drawable.platform_ladder);
        startActivity(i);
    }


}
