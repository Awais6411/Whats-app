package com.example.whatsapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsapp.fragments.CallsFragment;
import com.example.whatsapp.fragments.chatsfragment;
import com.example.whatsapp.fragments.statusFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:return new chatsfragment();
            case 1:return new statusFragment();
            case 2:return new CallsFragment();
            default:return new chatsfragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title=null;
        if(position==0)
        {
            title="chats";
        }
if(position==1)
        {
            title="Status";
        }
if(position==2)
        {
            title="Calls";
        }

        return title;
    }
}
