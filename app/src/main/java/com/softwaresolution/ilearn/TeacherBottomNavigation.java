package com.softwaresolution.ilearn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class TeacherBottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main);

        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            bottomnav.setSelectedItemId(R.id.nav_addquiz);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_addquiz:
                getSupportFragmentManager().beginTransaction().replace(R.id.bottom_fragment,
                        new TeacherActivity()).commit();
                break;
            case R.id.nav_leader:
                getSupportFragmentManager().beginTransaction().replace(R.id.bottom_fragment,
                        new TeacherLeaderboard()).commit();
                break;
        }
        return true;
    }
//    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                    Fragment fragment = null;
//                    switch (menuItem.getItemId()){
//                        case R.id.nav_addquiz:
//                            fragment = new TeacherActivity();
//                            break;
//                        case R.id.nav_leader:
//                            fragment = new TeacherLeaderboard();
//                            break;
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.bottom_fragment,
//                            fragment).commit();
//                    return  true;
//                }
//            };
}
