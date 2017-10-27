package kwon.cheolhyeok.vroute2.GoogleMapActivity;

import android.widget.ImageButton;

import com.google.android.gms.maps.model.LatLng;

public class Spot {
    private String place;
    private String time;
    private LatLng position;
    private ImageButton btn_search;

    public Spot(String name, String time, LatLng position) {
        this.place = name;
        this.time = time;
        this.position = position;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {return time;}

    public LatLng getPosition() {
        return position;
    }

    public ImageButton getBtn_search() {
        return btn_search;
    }

    public void setBtn_search(ImageButton btn_search) {
        this.btn_search = btn_search;
    }
}
