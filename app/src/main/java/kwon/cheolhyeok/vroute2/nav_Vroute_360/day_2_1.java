package kwon.cheolhyeok.vroute2.nav_Vroute_360;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import kwon.cheolhyeok.vroute2.R;

/**
 * Created by Kwon on 2017-09-12.
 */

public class day_2_1 extends AppCompatActivity {

    Toolbar toolbar = null;
    TextView nextday = null;

    ImageButton play1 = null;
    ImageButton play2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_2_1);

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
                Intent intent = new Intent(day_2_1.this, day_2_2.class);
                startActivity(intent);
            }
        });
        play1 = (ImageButton) findViewById(R.id.play1);
        play1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/6BLlUvcysv4")));
            }
        });

        play2 = (ImageButton) findViewById(R.id.play2);
        play2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/D2HaawwhSXI")));
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
