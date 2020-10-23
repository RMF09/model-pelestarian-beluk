package com.rmf.modelpelestariantradisilisanbeluk;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.rmf.modelpelestariantradisilisanbeluk.Fragments.BerandaFragment;
import com.rmf.modelpelestariantradisilisanbeluk.Fragments.VideoFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public boolean fullscreenVideo =false;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments
    BerandaFragment berandaFragment;
    VideoFragment videoFragment;
    MenuItem prevMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);


        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);

                                break;
                            case R.id.navigation_video:
                                viewPager.setCurrentItem(1);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

                if(position==0){
                    videoFragment.youTubePlayerView.exitFullScreen();
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        berandaFragment=new BerandaFragment();
        videoFragment=new VideoFragment(bottomNavigationView);
        adapter.addFragment(berandaFragment);
        adapter.addFragment(videoFragment);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int newOrientation = newConfig.orientation;

        if(newOrientation == Configuration.ORIENTATION_LANDSCAPE){
            if(videoFragment.youTubePlayerView.isFullScreen()==false){
                bottomNavigationView.setVisibility(View.GONE);
                if(viewPager.getCurrentItem()==1){

                    videoFragment.youTubePlayerView.enterFullScreen();
                }
            }
        }
        if(newOrientation == Configuration.ORIENTATION_PORTRAIT){
            if(videoFragment.youTubePlayerView.isFullScreen()==false){

                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(videoFragment.youTubePlayerView.isFullScreen()==true){
            videoFragment.youTubePlayerView.exitFullScreen();

        }
        else{
            super.onBackPressed();
        }
    }
}
