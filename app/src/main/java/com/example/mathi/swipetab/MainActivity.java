package com.example.mathi.swipetab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    Toolbar toolbar ;
    TabLayout tabLayout ;
    ViewPager viewPager ;
    String token;
    FragmentAdapterClass fragmentAdapter ;
    DatabaseReference myRef;
    private static int SIGN_IN_REQUEST_CODE=1;

    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data){
        super.onActivityResult(requestcode,resultcode,data);
        if(requestcode==SIGN_IN_REQUEST_CODE){
            if(resultcode==RESULT_OK){
                Toast.makeText(this, "Successfully signed in Welcome", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "We couldn't sign you in.please try again later", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("mathi");
        token = Utilities.getFirebaseToken(MainActivity.this);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Message");
        spec.setContent(R.id.message_view);
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_message));
        host.addTab(spec);
        //tab 2
        spec = host.newTabSpec("Search");
        spec.setContent(R.id.search_view);
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_search));
        host.addTab(spec);
        //Tab 3
        spec = host.newTabSpec("Profile");
        spec.setContent(R.id.profile_view);
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_account));
        host.addTab(spec);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        viewPager = (ViewPager) findViewById(R.id.pager1);

        setSupportActionBar(toolbar);

        tabLayout.addTab(tabLayout.newTab().setText("Send"));
        tabLayout.addTab(tabLayout.newTab().setText("Fav"));
        tabLayout.addTab(tabLayout.newTab().setText("Received"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentAdapter = new FragmentAdapterClass(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                viewPager.setCurrentItem(LayoutTab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });

    }
}