package kwon.cheolhyeok.vroute2.nav_Vroute_360;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import kwon.cheolhyeok.vroute2.R;

public class day_1 extends AppCompatActivity {

    Toolbar toolbar = null;
    TextView nextday = null;

    ImageButton play1 = null;
    ImageButton play2 = null;
    ImageButton play3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_1);

        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        getSupportActionBar().setDisplayShowHomeEnabled(true); //홈 아이콘을 숨김처리합니다.
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_36dp);

        nextday = (TextView) findViewById(R.id.nextday);
        nextday.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(day_1.this, day_1_1.class);
                startActivity(intent);
            }
        });

        play1 = (ImageButton) findViewById(R.id.play1);
        play1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/QHK0zOJSd_M")));
            }
        });

        play2 = (ImageButton) findViewById(R.id.play2);
        play2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/flwkWk5GKH0")));
            }
        });

        play3 = (ImageButton) findViewById(R.id.play3);
        play3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/VwQTZFuPmr8")));
            }
        });



    }



    // 뒤로가기 버튼 기능
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };


}
