package kwon.cheolhyeok.vroute2.GoogleMapActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.Map;

import kwon.cheolhyeok.vroute2.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class Search_Route_2 extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    private static final int POPUP_POSITION_REFRESH_INTERVAL = 16;
    private static final int ANIMATION_DURATION = 500;

    private static final LatLng AkasakaStation = new LatLng(35.672238, 139.736412);

    Toolbar toolbar = null;

    private View infoWindowContainer;
    private TextView place;
    private TextView time;
    private ImageButton search_btn;

    private Map<Marker, Spot> spots;
    private LatLng trackedPosition;
    private GoogleMap googleMap;
    private Handler handler;
    private Runnable positionUpdaterRunnable;

    private int popupXOffset;
    private int popupYOffset;

    private AbsoluteLayout.LayoutParams overlayLayoutParams;
    private ViewTreeObserver.OnGlobalLayoutListener infoWindowLayoutListener;

    private static Spot[] SPOTS_ARRAY = new Spot[]{
            new Spot("나리타 국제 공항 3 터미널", "10:30", new LatLng(35.777270, 140.382920)),
            new Spot("아카사카 역", "12:30", new LatLng(35.672238, 139.736412)),
            new Spot("도쿄 타워", "17:00", new LatLng(35.660652, 139.729221)),
            new Spot("히비야 공원 윤무축제", "다음 목적지를 선택해주세요",new LatLng(35.674476, 139.756415)),
            new Spot("KITTE 스모 축제", "다음 목적지를 선택해주세요",new LatLng(35.679974, 139.764866)),
            new Spot("아사쿠사 삼바 카니발", "다음 목적지를 선택해주세요",new LatLng(35.710731, 139.797874)),
            new Spot("하라주쿠 슈퍼 오사코이", "다음 목적지를 선택해주세요",new LatLng(35.670499, 139.702794)),
            new Spot("도쿄 고엔지 아와오도리", "다음 목적지를 선택해주세요",new LatLng(35.705198, 139.649672)),
            new Spot("도쿄 원피스 타워", "다음 목적지를 선택해주세요",new LatLng(35.658784, 139.745370)),
            new Spot("오오에도 온천", "다음 목적지를 선택해주세요",new LatLng(35.615678, 139.777193)),
            new Spot("도쿄 디즈니 랜드", "다음 목적지를 선택해주세요",new LatLng(35.633149, 139.880448)),
            new Spot("진구가이엔 불꽃 놀이", "다음 목적지를 선택해주세요",new LatLng(35.678132, 139.714994)),
            new Spot("카오산 비어 마운트", "다음 목적지를 선택해주세요",new LatLng(35.630903, 139.265126)),
            new Spot("우에노 여름 축제", "다음 목적지를 선택해주세요",new LatLng(35.711060, 139.770036)),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_route_2);
//
        spots = new HashMap<>();
        handler = new Handler(Looper.getMainLooper());
        positionUpdaterRunnable = new Search_Route_2.PositionUpdaterRunnable();
        handler.post(positionUpdaterRunnable);

        infoWindowContainer = findViewById(R.id.container_popup);
        infoWindowLayoutListener = new Search_Route_2.InfoWindowLayoutListener();
        infoWindowContainer.getViewTreeObserver().addOnGlobalLayoutListener(infoWindowLayoutListener);

        overlayLayoutParams = (AbsoluteLayout.LayoutParams) infoWindowContainer.getLayoutParams();

        search_btn = (ImageButton)infoWindowContainer.findViewById(R.id.btn_image);
        search_btn.setOnClickListener(this);

        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 툴바 뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        getSupportActionBar().setDisplayShowHomeEnabled(true); //홈 아이콘을 숨김처리합니다.
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_36dp);

        // 맵 프래그먼트 불러오기
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "2일차 일정을 종료하시겠습니까?", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Search_Route_2.this, Search_Route_3.class);
                        startActivity(intent);
                    }
                }).show();
            }
        });

        place = (TextView) infoWindowContainer.findViewById(R.id.txt_place);
        time = (TextView) infoWindowContainer.findViewById(R.id.txt_time);
        search_btn = (ImageButton) infoWindowContainer.findViewById(R.id.btn_image);
    }

//     뒤로가기 버튼 기능
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };

    @Override
    public void onMapReady(final GoogleMap map) {
        googleMap = map;
        int height = 144;
        int width = 100;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.marker_icon);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        for (Spot spot : SPOTS_ARRAY) {
            Marker marker = map.addMarker(new MarkerOptions().position(spot.getPosition()));
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            spots.put(marker, spot);
        }

        //지도상에 다른 부분 클릭시 마커 사라지게
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                infoWindowContainer.setVisibility(INVISIBLE);
            }
        });

        //마커 클릭시 보여주는 창(회색원)
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Projection projection = map.getProjection();

                trackedPosition = marker.getPosition();
                Point trackedPoint = projection.toScreenLocation(trackedPosition);

                trackedPoint.y -= popupYOffset / 2;
                LatLng newCameraLocation = projection.fromScreenLocation(trackedPoint);

                map.animateCamera(CameraUpdateFactory.newLatLng(newCameraLocation), ANIMATION_DURATION, null);

                Spot spot = spots.get(marker);

                place.setText(spot.getPlace());
                place.setTag(spot.getPlace());
                time.setText(spot.getTime());

                infoWindowContainer.setVisibility(VISIBLE);

                return false;
            }
        });

        // 마커 간 선 연결
        map.addPolyline((new PolylineOptions())
                .clickable(true)
                .add(new LatLng(35.672238, 139.736412),
                        new LatLng(35.674476, 139.756415),
                        new LatLng(35.679974, 139.764866),
                        new LatLng(35.710731, 139.797874)
                ));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(AkasakaStation, 16);
        map.animateCamera(cameraUpdate);
    }

    // 인터넷으로 이동
    @Override
    public void onClick(View v) {
        String name = (String) place.getTag();
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.google.com/search?q="+ name)));
    }

    //마커 위치
    private class InfoWindowLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            //회색 동그라미 가로축 위치 /2가 중앙
            popupXOffset = infoWindowContainer.getWidth() / 2;

            //회색 동그라미 세로축 위치 조정
            popupYOffset = infoWindowContainer.getHeight() - 560;
        }
    }

    private class PositionUpdaterRunnable implements Runnable {
        private int lastXPosition = Integer.MIN_VALUE;
        private int lastYPosition = Integer.MIN_VALUE  ;

        // 쓰레드
        @Override
        public void run() {
            handler.postDelayed(this, POPUP_POSITION_REFRESH_INTERVAL);

            if (trackedPosition != null && infoWindowContainer.getVisibility() == VISIBLE) {
                Point targetPosition = googleMap.getProjection().toScreenLocation(trackedPosition);
                if (lastXPosition != targetPosition.x || lastYPosition != targetPosition.y) {
                    overlayLayoutParams.x = targetPosition.x - popupXOffset;
                    overlayLayoutParams.y = targetPosition.y - popupYOffset;
                    infoWindowContainer.setLayoutParams(overlayLayoutParams);

                    lastXPosition = targetPosition.x;
                    lastYPosition = targetPosition.y;
                }
            }
        }
    }

    // 액티비티 이동에 기능을 위해서 데이터를 버려주는 역할
    public void onDestroy(){
        super.onDestroy();

        infoWindowContainer.getViewTreeObserver().removeGlobalOnLayoutListener(infoWindowLayoutListener);
        handler.removeCallbacks(positionUpdaterRunnable);
        handler = null;
    }

}