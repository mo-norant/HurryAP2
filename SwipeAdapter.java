package be.norant.mo.hurryap2;

/**
 * Created by mo on 14/12/2016.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by mo on 14/12/2016.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {
    LayoutInflater layoutInflater;
    int aantalTabs;

    public SwipeAdapter(Context context , int aantalTabs, FragmentManager fm){

        super(fm);
        this.aantalTabs = aantalTabs;

    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        switch(position){
            case 0 :
                Fragment kaartFragment = new MapViewFragment();
                return kaartFragment;
            case 1 :

            Fragment travelFragment = new TravelFragment();
            return travelFragment;
            case 2 :
                Fragment kalenderFragment = new CalendarFragment();
                return kalenderFragment;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 3;
    }
}

