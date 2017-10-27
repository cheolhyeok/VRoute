package kwon.cheolhyeok.vroute2.MainChoiceActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_1;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_10;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_2;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_3;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_4;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_5;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_6;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_7;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_8;
import kwon.cheolhyeok.vroute2.FestivalActivity_10.festival_9;
import kwon.cheolhyeok.vroute2.R;

public class a_CountrychoiceActivity extends AppCompatActivity {

    Toolbar toolbar = null;
    private ListView recommend_festival = null;

    TextView save = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_choice_activity);

        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        getSupportActionBar().setDisplayShowHomeEnabled(true); //홈 아이콘을 숨김처리합니다.
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_36dp);


        recommend_festival = (ListView)this.findViewById(R.id.a_listview);

        ArrayList<String> items = new ArrayList<>();
        items.add("fes1");
        items.add("fes2");
        items.add("fes3");
        items.add("fes4");
        items.add("fes5");
        items.add("fes6");
        items.add("fes7");
        items.add("fes8");
        items.add("fes9");
        items.add("fes10");


        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        recommend_festival.setAdapter(adapter);


        View header = getLayoutInflater().inflate(R.layout.country_choice_header, null, false);
        recommend_festival.addHeaderView(header);

        save = (TextView)this.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Intent intent = new Intent(MainActivity.this, WelcomeActivity2.class);
//                startActivity(intent);
                finish();
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

    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.country_choice_listitem, null);
            }

            // ImageView TextView 인스턴스
            final ImageView imageView = (ImageView)v.findViewById(R.id.a_imageview);

            TextView textView1 = (TextView)v.findViewById(R.id.fest_place);
            textView1.setText(items.get(position));

            TextView textView2 = (TextView)v.findViewById(R.id.fest_name);
            textView2.setText(items.get(position));

            TextView textView3 = (TextView)v.findViewById(R.id.fest_date);
            textView3.setText(items.get(position));

            // 리스트뷰의 아이템에 이미지와 텍스트를 변경한다.
            if("fes1".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes1_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i = new Intent(a_CountrychoiceActivity.this, festival_1.class);
                        startActivity(i);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("소부다이시타 해바라기 축제");
                textView3.setText("07.10~09.20");
            }
            else if("fes2".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes2_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i2 = new Intent(a_CountrychoiceActivity.this, festival_2.class);
                        startActivity(i2);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("KITTE 스모 축제");
                textView3.setText("08.20~08.29");
            }
            else if("fes3".equals(items.get(position))){
                imageView.setImageResource(R.drawable.fes3_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i3 = new Intent(a_CountrychoiceActivity.this, festival_3.class);
                        startActivity(i3);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("히비야 공원 윤무 축제");
                textView3.setText("08.25~08.26");
            }

            else if("fes4".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes4_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i4 = new Intent(a_CountrychoiceActivity.this, festival_4.class);
                        startActivity(i4);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("도쿄 고엔지 아와오도리 축제");
                textView3.setText("08.25~08.26");
            }

            else if("fes5".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes5_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i5 = new Intent(a_CountrychoiceActivity.this, festival_5.class);
                        startActivity(i5);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("히라주쿠 슈퍼 요사코이 축제");
                textView3.setText("08.20~08.26");
            }

            else if("fes6".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes6_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i6 = new Intent(a_CountrychoiceActivity.this, festival_6.class);
                        startActivity(i6);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("도쿄 도청 전망대 만화 축제");
                textView3.setText("07.20~09.20");
            }

            else if("fes7".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes7_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i7 = new Intent(a_CountrychoiceActivity.this, festival_7.class);
                        startActivity(i7);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("오오에도 온천 축제");
                textView3.setText("01.01~12.31");
            }

            else if("fes8".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes8_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i8 = new Intent(a_CountrychoiceActivity.this, festival_8.class);
                        startActivity(i8);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("도쿄 디즈니 랜드");
                textView3.setText("01.01~12.31");
            }

            else if("fes9".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes9_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i9 = new Intent(a_CountrychoiceActivity.this, festival_9.class);
                        startActivity(i9);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("진구가 이엔 불꽃 축제");
                textView3.setText("08.24~08.24");
            }

            else if("fes10".equals(items.get(position))) {
                imageView.setImageResource(R.drawable.fes10_1);
                imageView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i10 = new Intent(a_CountrychoiceActivity.this, festival_10.class);
                        startActivity(i10);
                    }
                });
                textView1.setText("도쿄");
                textView2.setText("카오산 비어 마운트");
                textView3.setText("06.01~09.31");
            }

            return v;
        }
    }

}
