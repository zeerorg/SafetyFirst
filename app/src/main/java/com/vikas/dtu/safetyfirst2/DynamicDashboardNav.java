package com.vikas.dtu.safetyfirst2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.vikas.dtu.safetyfirst2.mDiscussion.DiscussionActivity;
import com.vikas.dtu.safetyfirst2.mKnowIt.KnowItMain;
import com.vikas.dtu.safetyfirst2.mLaws.ActivityLaws;
import com.vikas.dtu.safetyfirst2.mNewsActivity.NewsActivity;
import com.vikas.dtu.safetyfirst2.mSignUp.SignInActivity;
import com.vikas.dtu.safetyfirst2.mUser.UpdateProfile;
import com.vikas.dtu.safetyfirst2.mUser.UserProfileActivity;

import java.util.HashMap;

public class DynamicDashboardNav extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, GoogleApiClient.OnConnectionFailedListener {
    private SliderLayout mDemoSlider;

    private static final String IMAGE_1 = "image1";
    private static final String IMAGE_2 = "image2";
    private static final String IMAGE_3 = "image3";
    private static final String IMAGE_4 = "image4";
    private static final String IMAGE_1_URL = "image1_url";
    private static final String IMAGE_2_URL = "image2_url";
    private static final String IMAGE_3_URL = "image3_url";
    private static final String IMAGE_4_URL = "image4_url";

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    HashMap<String,String> url_maps = new HashMap<String, String>();

    private static final String TAG = DynamicDashboardNav.class.getSimpleName();
    private static final int REQUEST_INVITE = 0;
    TextView Username;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private SharedPreferences mSharedPreferences;
    private ImageView imgProfile;
    private TextView userProfile;
    private TextView emailProfile;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_dashboard_nav);

        mFirebaseUser = getCurrentUser();
        if (mFirebaseUser == null) {
            startActivity(new Intent(DynamicDashboardNav.this, SignInActivity.class));
            finish();
        }

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Set default username is anonymous.

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        boolean autoLaunchDeepLink = true;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(AppInviteInvitationResult result) {
                                Log.d(TAG, "getInvitation:onResult:" + result.getStatus());
                                if (result.getStatus().isSuccess()) {
                                    // Extract information from the intent
                                    Intent intent = result.getInvitationIntent();
                                    String deepLink = AppInviteReferral.getDeepLink(intent);
                                    String invitationId = AppInviteReferral.getInvitationId(intent);

                                    // Because autoLaunchDeepLink = true we don't have to do anything
                                    // here, but we could set that to false and manually choose
                                    // an Activity to launch to handle the deep link here.
                                    // ...
                                }
                            }
                        });

        Username = (TextView) findViewById(R.id.username);
//        Username.setText(getName());

        View header = navigationView.getHeaderView(0);
        imgProfile = (ImageView) header.findViewById(R.id.profile_image);
        userProfile = (TextView) header.findViewById(R.id.profile_user);
        emailProfile = (TextView) header.findViewById(R.id.profile_email);


        userProfile.setText(getName());
        emailProfile.setText(getEmail());
        if (getPhotoUrl() != null) {
            Glide.with(getBaseContext())
                    .load(getPhotoUrl())
                    .into(imgProfile);
        }

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        fetchDashboardSlides();
    }
    private void fetchDashboardSlides(){
        long cacheExpiration = 3600;

        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                   //         Toast.makeText(DynamicDashboardNav.this, "Fetch Succeeded", Toast.LENGTH_SHORT).show();

                            // Once the config is successfully fetched it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(DynamicDashboardNav.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        populateDashboard();
                    }
                });

    }
    private void populateDashboard(){
        url_maps.put(mFirebaseRemoteConfig.getString(IMAGE_1), mFirebaseRemoteConfig.getString(IMAGE_1_URL));
        url_maps.put(mFirebaseRemoteConfig.getString(IMAGE_2), mFirebaseRemoteConfig.getString(IMAGE_2_URL));
        url_maps.put(mFirebaseRemoteConfig.getString(IMAGE_3), mFirebaseRemoteConfig.getString(IMAGE_3_URL));
        url_maps.put(mFirebaseRemoteConfig.getString(IMAGE_4), mFirebaseRemoteConfig.getString(IMAGE_4_URL));

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

 @SuppressWarnings("StatementWithEmptyBody")
 @Override
 public boolean onNavigationItemSelected(MenuItem item) {
     // Handle navigation view item clicks here.
     int id = item.getItemId();


     if (id == R.id.nav_faq) {
         startActivity(new Intent(DynamicDashboardNav.this, FaqActivity.class));
     }
     else if (id == R.id.nav_feedback)
     {
         startActivity(new Intent(DynamicDashboardNav.this, FeedBackActivity.class));
     }
     else if (id == R.id.nav_help) {
         startActivity(new Intent(DynamicDashboardNav.this, Help.class));

     } else if (id == R.id.nav_invite) {

         onInviteClicked();

     } else if(id == R.id.nav_tnc){
         startActivity(new Intent(DynamicDashboardNav.this, TermsnCondition.class));
     }
     else if (id == R.id.log_out) {
         SignInActivity.signin = true;

         logout();
     } else if (id == R.id.nav_feedback) {
         startActivity(new Intent(DynamicDashboardNav.this, FeedBackActivity.class));
     }
     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
     drawer.closeDrawer(GravityCompat.START);
     return true;
 }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // [START_EXCLUDE]
                showMessage(getString(R.string.send_failed));
                // [END_EXCLUDE]
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("MainActivity", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();

    }

    public void startNews(View view) {

        if (isNetworkConnected()) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, NoNetworkConnection.class);
            startActivity(intent);
        }
    }

    public void startDiscussion(View view) {

        if (isNetworkConnected()) {
            Intent intent = new Intent(this, DiscussionActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, NoNetworkConnection.class);
            startActivity(intent);
        }

    }

    public void startKnowIt(View view) {
        Intent intent = new Intent(this, KnowItMain.class);
        startActivity(intent);
    }

    public void startLaw(View view) {
        Intent intent = new Intent(this, ActivityLaws.class);
        startActivity(intent);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                //.setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseUser = getCurrentUser();
        if (mFirebaseUser == null) {
            startActivity(new Intent(DynamicDashboardNav.this, SignInActivity.class));
            finish();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Dashboard Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient.connect();
        AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
        mGoogleApiClient.disconnect();
    }

}
