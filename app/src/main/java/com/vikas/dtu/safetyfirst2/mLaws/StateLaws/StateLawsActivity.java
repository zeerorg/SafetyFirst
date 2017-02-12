package com.vikas.dtu.safetyfirst2.mLaws.StateLaws;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mLaws.DownloadPdf;
import com.vikas.dtu.safetyfirst2.mLaws.LawsRecyclerViewAdapter;
import com.vikas.dtu.safetyfirst2.mLaws.StateLawsRowInfo;

import java.util.ArrayList;

public class StateLawsActivity extends AppCompatActivity {
    RecyclerView lawsrecycler,formsrecycler;
    ArrayList<StateLawsRowInfo> lawsArrayList,formsArrayList;
    RelativeLayout relativeLayout1,relativeLayout2;
    Resources res;
    int pos,q=0;
    TypedArray data;
    TypedArray data1;
    String[] LTextdata;
    Drawable[] LImagedata;
    String[] FTextdata;
    String[][] lurl;
    String[][] furl;
    DownloadPdf downloadPdf;
    String[] states;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_laws);
        res=this.getResources();
        pos=getIntent().getExtras().getInt("position",0);
        states=res.getStringArray(R.array.States);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(states[pos]);
        data=res.obtainTypedArray(R.array.StatesLf);
        int id=data.getResourceId(pos,0);
        data1=res.obtainTypedArray(id);
        int id1=data1.getResourceId(q,0);
        q++;
        LTextdata=res.getStringArray(id1);
        int id2=data1.getResourceId(q,1);
        q++;
        TypedArray data2=res.obtainTypedArray(id2);
        LImagedata=new Drawable[data2.length()];
        for(int i=0;i<LImagedata.length;i++){
            LImagedata[i]=data2.getDrawable(i);
        }
        int id3=data1.getResourceId(q,0);
        FTextdata=res.getStringArray(id3);
        downloadPdf=new DownloadPdf();

        relativeLayout1=(RelativeLayout) findViewById(R.id.lawscom);
        relativeLayout2=(RelativeLayout) findViewById(R.id.formscom);
        lawsrecycler=(RecyclerView) findViewById(R.id.LawsRecycler);
        formsrecycler=(RecyclerView) findViewById(R.id.FormsRecycler);
        lawsArrayList=fillLawsData();
        formsArrayList=fillFormsData();
        if(lawsArrayList==null){
            relativeLayout1.setVisibility(View.GONE);
        }else{
            LawsRecyclerViewAdapter lawsRecyclerViewAdapter=new LawsRecyclerViewAdapter(getApplicationContext(),lawsArrayList, new LawsRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    if (Checkforpermission.CheckforPermissions(StateLawsActivity.this)) {
                        downloadPdf.downloadandShow(lurl[pos][position], StateLawsActivity.this,states[pos]+" "+LTextdata[position]+".pdf");
                    } else {
                        Checkforpermission.requestpermission(StateLawsActivity.this, position);
                    }

                    Log.d("TAG2",String.valueOf(pos));
                }
            });
            lawsrecycler.setAdapter(lawsRecyclerViewAdapter);
            lawsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));}
        if(formsArrayList==null){
            relativeLayout2.setVisibility(View.GONE);
        }else{
            LawsRecyclerViewAdapter lawsRecyclerViewAdapter1=new LawsRecyclerViewAdapter(getApplicationContext(), formsArrayList, new LawsRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    if (Checkforpermission.CheckforPermissions(StateLawsActivity.this)) {
                        downloadPdf.downloadandShow(furl[pos][position], StateLawsActivity.this,states[pos]+" "+FTextdata[position]+".pdf");
                    } else {
                        Checkforpermission.requestpermission(StateLawsActivity.this, position+100);
                    }
                }
            });
            formsrecycler.setAdapter(lawsRecyclerViewAdapter1);
            formsrecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));}
        if(pos==4){
            final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            int pixels = (int) (240 * scale + 0.5f);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, pixels);
            relativeLayout1.setLayoutParams(params);

        }
        lurl=new String[states.length][6];
        furl=new String[states.length][16];
        Addurl();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode>=100){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadPdf.downloadandShow(furl[pos][requestCode-100], StateLawsActivity.this, states+" "+FTextdata+".pdf");
                // permission was granted, yay! Do the
                // contacts-related task you need to do.

            }}
        else{
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadPdf.downloadandShow(lurl[pos][requestCode], StateLawsActivity.this,states+" "+LTextdata+".pdf");
                // permission was granted, yay! Do the
                // contacts-related task you need to do.

            }
        }
        return;
    }


    private void Addurl() {
//        Andhra
        lurl[1][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTHE%20FACTORIES%20ACT-1948.pdf?alt=media&token=293a89e2-f4b9-4cc8-b2bc-58f400e14829";
//      delhi
        lurl[1][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffire%20acts%2FDelhi_fire_service_act_2009.pdf?alt=media&token=3db46ee5-27bc-4a6e-9ce8-938b3f2f0e5d";
        furl[1][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_form%20-%207%20-%20Record%20of%20Lime%20washing%2Cpainting%2Cetc.pdf?alt=media&token=9fa3d07b-b09d-4c4e-8c59-e27cf31d1194";
        furl[1][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2010%20-%20Overtime%20Muster%20Roll%20for%20Exempted%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=a5fbf92a-9aee-4022-be83-195a4a0dd844";
        furl[1][2]= "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2011%20-%20Notice%20of%20period%20of%20work%20for%20Adult%20Workers%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=70314442-90b6-4cb7-8356-bb5b73dc27aa";
        furl[1][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2012%20-%20Register%20of%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=18cf8de4-b0b3-40a2-9154-12e1ae0d37e3";
        furl[1][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2013%20-%20Notice%20of%20periods%20of%20work%20for%20child%20worker%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=93b9b467-307c-4430-9e80-d82c596a1e13";
        furl[1][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2014%20-%20Register%20of%20Child%20Workers%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=d91a0593-5d3d-48ec-996c-abdba53e7382";
        furl[1][6]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2015%20-%20Register%20of%20Leave%20with%20Wages%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=b6ffd8c7-aa7a-4c1d-b3af-bea9d51080b4";
        furl[1][7]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2017%20-%20Health%20Register%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=49455f73-1b9e-418f-9797-6755f969918d";
        furl[1][8]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2021%20-%20Annual%20Return%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=5c370dba-1514-497c-ba74-120a5deb3eb9";
        furl[1][9]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2022%20-%20Half%20Yearly%20Return.pdf?alt=media&token=acc56a7a-6177-4bf8-bb46-228f14335d3d";
        furl[1][10]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2026%20-%20Muster%20Roll%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=407ed9da-c303-4df8-be9f-e12b54aae0d3";
        furl[1][11]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2027%20-%20Register%20of%20Accident%20and%20Dangerous%20Occurences%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=6cf81b5c-ac7d-49ac-b542-f2f914de1aff";
        furl[1][12]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%2029%20-%20Register%20of%20Trained%20Adult%20Workers%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=a2c9a7d8-4dbf-4ccb-bb99-666a918fd68e";
        furl[1][13]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%203%20-%20Notice%20of%20change%20of%20Manager%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=e25d5baa-72b4-48f5-a49e-672174b3f066";
        furl[1][14]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FDelhiForm%2FNone_Form%209%20-%20Register%20of%20Compensatory%20Holidays%20-%20Delhi%20Factories%20Act.pdf?alt=media&token=ced366a5-8dcc-44f2-896b-498aae375a8b";
//    Gujarat
        lurl[2][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGuj-Fact-Rules.pdf?alt=media&token=1212b060-7ed1-43f0-a93e-5d121c486d34";
        furl[2][0]=" https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2013%20-%20Overtime%20Register%20for%20Exempted%20workers%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=a850ebb9-5d25-4bc3-837e-bcefc295d1fb";
        furl[2][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2015%20-%20Register%20of%20Adult%20worker%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=1aff8ac3-8699-44d2-8708-b5b652940563";
        furl[2][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2016%20-%20Notice%20of%20period%20of%20work%20for%20Child%20Worker%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=e3d042e0-4a4d-4612-a753-12a262a69c49";
        furl[2][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2017%20-%20Register%20of%20Child%20workers%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=8614c51c-ef5b-4a31-9735-726462d0bdf8";
        furl[2][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%2018%20-%20Register%20of%20Leave%20with%20Wages%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=c13eba75-880e-40b4-a77d-cc0473c1a5af";
        furl[2][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%203-A%20-%20Notice%20of%20change%20of%20Manager%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=e6239ead-13ea-497c-b6cf-3d3bf04b7d44";
        furl[2][6]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FGujratForm%2FNone_Form%207%20-%20Record%20of%20Lime%20Washing%2C%20Painting%2Cetc%20-%20Gujarat%20Factories%20Act.pdf?alt=media&token=b4a7f12e-0864-44ac-8b44-3441137f56b7";
//   Haryana
        lurl[3][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FCopy%20of%20the_punjab_factories_rules_1952.pdf?alt=media&token=fc2f7c90-df66-4754-9fa5-6989f27fb7d4";
        furl[3][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_form%20-%207%20-%20Record%20of%20Lime%20washing%2Cpainting%2Cetc.pdf?alt=media&token=24627748-4422-4833-b374-136996cc4aa8";
        furl[3][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2010%20-%20Overtime%20Muster%20Roll%20for%20Exempted%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=70f58cfb-5981-420c-884b-dec55bddb064";
        furl[3][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2011%20-%20Notice%20period%20of%20work%20for%20Adult%20Workers%20-%20Haryana%20Factories%20Act.pdf?alt=media&token=af64399b-5220-4755-b1e9-c9a11a5d3a73";
        furl[3][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2012%20-%20Register%20of%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=2881cf55-31c4-473e-bdfc-1aa6bdf380d5";
        furl[3][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2013%20-%20Notice%20of%20periods%20of%20work%20for%20child%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=5b1afd20-d75a-4f45-b413-e8036debf854";
        furl[3][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2014%20-%20Register%20of%20Child%20worker%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=25145329-fdba-4d5d-a5a5-a10f498e731d";
        furl[3][6]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2015%20-%20Register%20of%20Leave%20with%20Wages%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=843d1a34-ac60-4ab7-b208-6bd5fb5cdf28";
        furl[3][7]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2017%20-%20Health%20Register%20-%20Haryana%20Factories%20Act.pdf?alt=media&token=f7fb2a82-a35b-4fbf-8151-7b854501d13c";
        furl[3][8]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2021%20-%20Annual%20Return%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=042c908b-32ba-4b59-9984-9039701e9795";
        furl[3][9]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2022%20-%20Half%20Yearly%20Returns%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=c16d3b58-7dfd-42f6-a23d-fe0bbd6de476";
        furl[3][10]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%2025%20-%20Muster%20Roll%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=dce96e5a-ef4c-4bd1-b3ee-3aeb784b6b57";
        furl[3][11]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FHaryanaForm%2FNone_Form%209%20-%20Register%20of%20Compensatory%20Holidays%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=0bc96ba7-98ea-47e0-844e-073f44be5008";
//  Karnataka
        lurl[4][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2Fcitizen_charter_dept_of_factoriesboilers_industrial_safety__health_karnataka(1).pdf?alt=media&token=72507bbf-53b5-4b01-b3fa-395c42d8e6b8";
        lurl[4][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2Ffactories_act_1948.pdf?alt=media&token=9a78f989-c310-47af-bebe-a06faabe0aa3";
        lurl[4][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%2Fno_fees_for_trade_license_or_general_license_from_small_scale_industries_in_karnataka.pdf?alt=media&token=08118094-29a5-406c-8727-36d3b6c5492f";
        lurl[4][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FKarnataka%20Factories%20Rules%2C%201969.pdf?alt=media&token=e2f3a4e6-81ba-43f7-a64d-68ded3e3982f";
        lurl[4][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffire%20acts%2FKarnataka_Fire_Force_Act.pdf?alt=media&token=4792a850-441d-4f26-a10f-3b952ada03d2";
        furl[4][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%20%2023%20-%20REGISTER%20OF%20ACCIDENTS%20AND%20DANGEROUS%20OCCURRENCES.pdf?alt=media&token=9db0d92c-0e13-4b06-942e-73c76d94313e";
        furl[4][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%2011%20-%20REGISTER%20OF%20ADULT%20WORKER.pdf?alt=media&token=eb2823c4-1285-4200-ad58-a65f9f6801da";
        furl[4][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%2014%20-%20REGISTER%20OF%20LEAVE%20WITH%20WAGES%20FOR%20THE%20YEAR.pdf?alt=media&token=e6efac1d-d217-4b1c-882e-39e42c7c54d4";
        furl[4][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%2015%20-%20Leave%20Book-%20REGISTER%20OF%20LEAVE%20WITH%20WAGES.pdf?alt=media&token=d3ef7e28-45d2-426e-84a7-b66d980a8f7c";
        furl[4][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%2016%20-%20HEALTH%20%20REGISTER.pdf?alt=media&token=17c7e97f-976e-4d49-a985-76cdb695d20a";
        furl[4][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%202%20-%20FACTORY%20LICENCE%20RENEWAL-2.pdf?alt=media&token=d6bda6e2-7758-423a-af23-205683879d37";
        furl[4][6]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%2020%20-%20COMBINED%20ANNUAL%20RETURN.pdf?alt=media&token=d472891a-64cb-44c6-9c61-d413bed858ef";
        furl[4][7]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%2022%20-%20MUSTER%20ROLL%20CUM%20REGISTER%20OF%20WAGES.pdf?alt=media&token=3b231861-97a4-4a8e-9465-8174a4fbb44a";
        furl[4][8]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_FORM%209%20-%20REGISTER%20OF%20OVERTIME%20AND%20PAYMENT.pdf?alt=media&token=c6d1d6ff-c686-408f-94ba-57146e7ddd9d";
//  Kerala
        lurl[5][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fthe_kerala_factories_rules_1957.pdf?alt=media&token=f741aa7e-dcc0-4239-9bbd-21f3ae57781f";
//  Maharashtra
        lurl[6][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffactories-act-1948.pdf?alt=media&token=4b247841-6f5c-49c8-a8e0-5041228a0f36";
        lurl[6][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FMaharashtra%2Fmaharashtra_labor_welfare_fund_act.pdf?alt=media&token=d24180b3-f4cb-4682-b411-e8e3ca67ba62";
        lurl[6][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffire%20acts%2FMaharashatra_Fire_Act_English.pdf?alt=media&token=acd9bbc0-ab78-4f90-9163-5670e10eef2d";
        furl[6][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2FNone_Form%20A-1%20CUM%20Return.pdf?alt=media&token=8a79ffe9-4191-403e-b9c5-ad911b71ea26";
//  Orrisa
        lurl[7][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FOrissa%2F1415.pdf?alt=media&token=91d15ee5-ff8e-4670-b468-2cb7c194ce54";
        lurl[7][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fthe_orissaindustrial_establishments_national_and_festival_holidays_act_1969.pdf?alt=media&token=86a33e4e-e4cb-46dc-a080-d9c526a1c62a";
        lurl[7][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffire%20acts%2Forissa_fire_service_act_1993.pdf?alt=media&token=b6899629-f6c9-44a5-b008-15e84ab968aa";
//  Punjab
        lurl[8][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FCopy%20of%20the_punjab_factories_rules_1952.pdf?alt=media&token=fc2f7c90-df66-4754-9fa5-6989f27fb7d4";
        furl[8][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2010%20-%20Overtime%20Muster%20Roll%20for%20Exempted%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=b9518d5d-819d-4bf4-b82a-8fcca2b805ae";
        furl[8][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2011%20-%20Notice%20period%20of%20work%20for%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=61ff4cc1-ebca-43e7-b36d-8c319e31d536";
        furl[8][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2012%20-%20Register%20of%20Adult%20Workers%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=7b0ce24b-72d5-4fd7-8c86-e1ef734d680d";
        furl[8][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2013%20-%20Notice%20of%20periods%20of%20work%20for%20child%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=749baf8b-4565-4dce-aa62-8642b24bad47";
        furl[8][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2014%20-%20Register%20of%20Child%20worker%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=41f0bcaf-b602-4e26-88f8-a8bcce654d31";
        furl[8][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2015%20-%20Register%20of%20Leave%20with%20Wages%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=412e61e3-fa62-4556-8a7f-bc5ff2415ff9";
        furl[8][6]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2017%20-%20Health%20Register%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=5c471af6-c308-4750-be5d-dd5bf3102f04";
        furl[8][7]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2021%20-%20Annual%20Return%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=f0b6cf6f-1422-4e47-8c83-6ae5b66923a4";
        furl[8][8]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2022%20-%20Half%20Yearly%20Returns%20-%20Kerala%20Factories%20Act.pdf?alt=media&token=f2d2b982-565d-4fef-81b7-50c183c171d0";
        furl[8][9]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%2025%20-%20Muster%20Roll%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=2039a2ce-d60d-4249-86f5-89381de4263f";
        furl[8][10]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%207%20-%20Record%20of%20Lime%20washing%2Cpainting%2Cetc%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=4470834e-439d-4e55-b0ee-391868c68b09";
        furl[8][11]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FPunjab%2FNone_Form%209%20-%20Register%20of%20Compensatory%20Holidays%20-%20Punjab%20Factories%20Act.pdf?alt=media&token=7d731775-06c2-4d2e-926e-4a9f949ba442";
//  TamilNadu
        lurl[9][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTamil-Nadu-Factories-Rules-1950-With-FORMS.pdf?alt=media&token=b6712332-b33c-420c-a305-a880a703a86b";
        lurl[9][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTamilNadu%2FFactoriesAct1948andTamilNaduFactoriesRules.pdf?alt=media&token=592e135b-d415-45d6-9f6a-b3129af47fc3";
        lurl[9][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffire%20acts%2Ftamilnadu_fire_service_act_1985.pdf?alt=media&token=f74a7db6-ab57-4fc8-abc8-4a0b28bf0ec7";
//  Telangana
        lurl[10][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fthe_telangana_factorie_rules.pdf?alt=media&token=2f72ad5c-0ab0-4688-b9f4-44831e11acb2";
        furl[10][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%2014%20-%20Register%20of%20Child%20worker%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=1c506423-9bec-4c8b-9bf6-85791c97796c";
        furl[10][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%2017%20-%20B%20-%20Telangana%20Factories%20Act.pdf?alt=media&token=2b0945e1-74a1-47aa-88b8-9d0cc133e576";
        furl[10][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%202-A%20-%20Notice%20of%20Change%20of%20Manager%20or%20Occupier.pdf?alt=media&token=f05b4d95-14c3-49a0-8658-03fd8a5dec8b";
        furl[10][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%205%20-%20Certificate%20of%20Fitness.pdf?alt=media&token=e7bf0856-8091-45e5-8450-76cef93c1789";
        furl[10][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2FNone_Form%20AR%20-%20Annual%20Return%20for%20the%20Year%20Ending%2031st%20December.pdf?alt=media&token=22ddc9ef-216a-487a-882b-69058d838da2";
        furl[10][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2FTelanganaForm%2Fthe_telangana_factorie_rules.pdf?alt=media&token=6b04868d-930a-4bf9-8c8d-a8fc0e3ddc98";
//  WestBengal
        lurl[11][0]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2F1_bocw_act_1996.pdf?alt=media&token=d69d2ea3-22b8-4efa-868d-54291b914bab";
        lurl[11][1]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2F1_manufacture_storage_and_import_of_hazardous_chemical_rules_1989_as_amended_upto_date.pdf?alt=media&token=3261a995-c649-4947-bb76-90d3b8e9bf9a";
        lurl[11][2]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2F1_safety_officer_notification_after_1980.pdf?alt=media&token=b9bf17e0-4a22-4c4d-97d0-4b87f587a232";
        lurl[11][3]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2F2_factories_rules_1958.pdf?alt=media&token=520ee35a-3461-4a48-804b-e1cacc69a1dd";
        lurl[11][4]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%202%2F2_the_chemical_accidents_emergency_planning_preparedness_and_response_rules_1996_as_amended_upto_date.pdf?alt=media&token=ae27f2ef-d913-4e61-a50e-52d4d5a26667";
        lurl[11][5]="https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffire%20acts%2FWest_bengal_fire_service_act.pdf?alt=media&token=1a6ee010-6e74-46cb-a358-8f49d7b41b38";
    }

    private ArrayList<StateLawsRowInfo> fillFormsData() {
        if(FTextdata.length==0){
            return null;
        }else{
            ArrayList<StateLawsRowInfo> temp=new ArrayList<>();
            for(int i=0;i<FTextdata.length;i++){
                temp.add(new StateLawsRowInfo(FTextdata[i],getResources().getDrawable(R.drawable.form_backgrond)));
            }
            return temp;
        }

    }

    private ArrayList<StateLawsRowInfo> fillLawsData() {
        if(LTextdata.length==0){
            return null;
        }else{
            ArrayList<StateLawsRowInfo> temp=new ArrayList<>();
            for(int i=0;i<LTextdata.length;i++){
                temp.add(new StateLawsRowInfo(LTextdata[i],LImagedata[i]));
            }
            return temp;}
    }
}
