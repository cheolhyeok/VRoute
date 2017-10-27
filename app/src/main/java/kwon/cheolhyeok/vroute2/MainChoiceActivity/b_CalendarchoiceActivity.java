package kwon.cheolhyeok.vroute2.MainChoiceActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tubb.calendarselector.library.CalendarSelector;
import com.tubb.calendarselector.library.FullDay;
import com.tubb.calendarselector.library.IntervalSelectListener;
import com.tubb.calendarselector.library.MonthView;
import com.tubb.calendarselector.library.SCDateUtils;
import com.tubb.calendarselector.library.SCMonth;
import com.tubb.calendarselector.library.SegmentSelectListener;
import com.tubb.calendarselector.library.SingleMonthSelector;

import java.util.List;

import kwon.cheolhyeok.vroute2.R;

/**
 * Created by Kwon on 2017-08-18.
 */
public class b_CalendarchoiceActivity extends AppCompatActivity {


    Toolbar toolbar = null;

    private static final String TAG = "mv";
    SCMonth scMonth;
    private SingleMonthSelector selector;
    private MonthView monthView;

    TextView save = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_choice_activity);


        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        getSupportActionBar().setDisplayShowHomeEnabled(true); //홈 아이콘을 숨김처리합니다.
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_36dp);

        // 캘린더뷰
        monthView = (MonthView) findViewById(R.id.ssMv);
        TextView tvMonthTitle = (TextView) findViewById(R.id.tvMonthTitle);
        if(savedInstanceState != null){
            scMonth = savedInstanceState.getParcelable("month");
        }

        // 어느 시점의 달력으로 설정할 것인지
        if(scMonth == null)
            scMonth = new SCMonth(2018, 8, SCMonth.SUNDAY_OF_WEEK);

        //타이틀 날짜 텍스트 변경
        tvMonthTitle.setText("2018 / 08");

        // 캘린더뷰 모드
        segmentMode();

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


    private void segmentMode(){
        scMonth = new SCMonth(2018, 8, SCMonth.SUNDAY_OF_WEEK);

        selector = new SingleMonthSelector(scMonth, SingleMonthSelector.SEGMENT);

        //24일부터 26일까지 기간을 미리 체크해줌
        selector.addSelectedSegment(new FullDay(2018, 8, 24), new FullDay(2018, 8, 26));
        monthView.setSCMonth(scMonth);
        selector.setSegmentSelectListener(new SegmentSelectListener() {
            @Override
            public void onSegmentSelect(FullDay startDay, FullDay endDay) {
                Log.d(TAG, "segment select " + startDay.toString() + " : " + endDay.toString());
            }

            @Override
            public boolean onInterceptSelect(FullDay selectingDay) {
                if(SCDateUtils.isToday(selectingDay.getYear(), selectingDay.getMonth(), selectingDay.getDay())){
                    Toast.makeText(b_CalendarchoiceActivity.this, "Today can't be selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return super.onInterceptSelect(selectingDay);
            }

            @Override
            public boolean onInterceptSelect(FullDay startDay, FullDay endDay) {
                int differDays = SCDateUtils.countDays(startDay.getYear(), startDay.getMonth(), startDay.getDay(),
                        endDay.getYear(), endDay.getMonth(), endDay.getDay());
                Log.d(TAG, "differDays " + differDays);
                if(differDays > 31) {
                    Toast.makeText(b_CalendarchoiceActivity.this, "Selected days can't more than 31", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return super.onInterceptSelect(startDay, endDay);
            }

        });
        selector.bind(monthView);
    }

    private void intervalMode(){
        scMonth = new SCMonth(2018, 8, SCMonth.SUNDAY_OF_WEEK);
        selector = new SingleMonthSelector(scMonth, SingleMonthSelector.INTERVAL);
        selector.addSelectedInterval(new FullDay(2018, 8, 24));
        selector.addSelectedInterval(new FullDay(2018, 8, 25));
        selector.addSelectedInterval(new FullDay(2018, 8, 26));
        monthView.setSCMonth(scMonth);
        selector.setIntervalSelectListener(new IntervalSelectListener() {
            @Override
            public void onIntervalSelect(List<FullDay> selectedDays) {
                Log.d(TAG, "interval selected days " + selectedDays.toString());
            }

            //31일 이상 체크 할 시 에러 토스트
            @Override
            public boolean onInterceptSelect(List<FullDay> selectedDays, FullDay selectingDay) {
                if(selectedDays.size() >= 31) {
                    Toast.makeText(b_CalendarchoiceActivity.this, "Selected days can't more than 31", Toast.LENGTH_LONG).show();
                    return true;
                }
                return super.onInterceptSelect(selectedDays, selectingDay);
            }
        });
        selector.bind(monthView);
    }




}
