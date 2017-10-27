package kwon.cheolhyeok.vroute2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kwon.cheolhyeok.vroute2.GoogleMapActivity.Search_Route_1;
import kwon.cheolhyeok.vroute2.MainChoiceActivity.c_AirlinechoiceActivity;
import kwon.cheolhyeok.vroute2.MainChoiceActivity.b_CalendarchoiceActivity;
import kwon.cheolhyeok.vroute2.MainChoiceActivity.a_CountrychoiceActivity;
import kwon.cheolhyeok.vroute2.nav_Vroute_360.day_1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar = null;
    DrawerLayout drawer = null;
    ActionBarDrawerToggle toggle = null;

    private AdapterViewFlipper simpleAdapterViewFlipper = null;
    int[] fruitImages = {R.drawable.flipper1, R.drawable.flipper2, R.drawable.flipper3, R.drawable.flipper4, R.drawable.flipper5, R.drawable.flipper6, R.drawable.flipper7};     // array of images
    String fruitNames[] = {"","","","","","",""};

    RelativeLayout relativelayout1 = null;
    RelativeLayout relativelayout2 = null;
    RelativeLayout relativelayout3 = null;

    Button search_route = null;

    TextView tutorial = null;

    // 백버튼 2번눌러야 앱 종료
    private CloseActivityHandler closeActivityHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 드로워 열고 닫기
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //네비게이션 드로워 아이콘
        toggle.setDrawerIndicatorEnabled(false);
        final Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_menu_black_36dp,getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        //네비게이션(implements)
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //AdapterViewFlipper (사진 자동넘김)
        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        ViewFlipperAdapter customAdapter = new ViewFlipperAdapter(getApplicationContext(), fruitNames, fruitImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000); // 인터벌 속도
        simpleAdapterViewFlipper.setAutoStart(true);

        //메인화면에서 각 선택 레이아웃 클릭시 해당 액티비티로 이동
        relativelayout1 =(RelativeLayout)findViewById(R.id.menu_choice_1);
        relativelayout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(MainActivity.this,a_CountrychoiceActivity.class), 0);
            }
        });

        relativelayout2 =(RelativeLayout)findViewById(R.id.menu_choice_2);
        relativelayout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(MainActivity.this,b_CalendarchoiceActivity.class), 0);
            }
        });
        relativelayout3 =(RelativeLayout)findViewById(R.id.menu_choice_3);
        relativelayout3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivityForResult(new Intent(MainActivity.this,c_AirlinechoiceActivity.class), 0);
            }
        });

        search_route = (Button)findViewById(R.id.search_route);
        search_route.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, WelcomeActivity2.class);
                startActivity(intent);
            }
        });

        closeActivityHandler = new CloseActivityHandler(this);

        findViewById(R.id.tutorial).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PrefManager prefManager = new PrefManager(getApplicationContext());

                // make first time launch TRUE
                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                finish();
            }
        });

    }

    //백버튼
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            closeActivityHandler.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {

        }
        else if (id == R.id.nav_vroute) {
        Intent i = new Intent(MainActivity.this, day_1.class);
            startActivity(i);
        }
        else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_airplain) {


        } else if (id == R.id.nav_setting){


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
