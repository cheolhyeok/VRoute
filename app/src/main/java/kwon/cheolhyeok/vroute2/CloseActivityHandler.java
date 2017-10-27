package kwon.cheolhyeok.vroute2;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by Juyeol on 2017-06-27
 * 엑티비티 닫기 컨트롤
 */


public class CloseActivityHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;
    private final int time = 2000;  // 기다리는 시간

    public CloseActivityHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {

        if (System.currentTimeMillis() <= backKeyPressedTime + time) {
            toast.cancel();
//            activity.finish();
//            activity.finishAffinity();
            ActivityCompat.finishAffinity(activity);
        }else{
            showGuide();
            backKeyPressedTime = System.currentTimeMillis();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "한번 더 누르면 브이루트가 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
