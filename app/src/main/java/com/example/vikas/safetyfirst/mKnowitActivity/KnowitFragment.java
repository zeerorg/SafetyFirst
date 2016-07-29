package com.example.vikas.safetyfirst.mKnowitActivity;

/**
 * Created by Vikas on 26-07-2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mNewsActivity.FavNewsFragment;
import com.example.vikas.safetyfirst.mNewsActivity.NewNewsFragment;

/**
 * Created by Ratan on 7/29/2015.
 */
public class KnowitFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 8 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.knowit_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0: return new TabFragment1();
                case 1: return new TabFragment2();
                case 2 : return new TabFragment1();
                case 3 : return new TabFragment2();
                case 4: return new TabFragment1();
                case 5: return new TabFragment2();
                case 6 : return new TabFragment1();
                case 7 : return new TabFragment2();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Ladders";
                case 1 :
                    return "Scaffoldings";
                case 2 :
                    return "Tab 3";
                case 3 :
                    return "Tab 4";
                case 4 :
                    return "Tab 5";
                case 5 :
                    return "Tab 6";
                case 6 :
                    return "Tab 7";
                case 7 :
                    return "Tab 8";
            }
            return null;
        }
    }

}