package kwon.cheolhyeok.vroute2.FestivalActivity_10;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kwon.cheolhyeok.vroute2.R;


public class festival_7_viewpager_adapter extends PagerAdapter {


    LayoutInflater inflater;

    public festival_7_viewpager_adapter(LayoutInflater inflater) {
        this.inflater=inflater;
    }

    @Override
    public int getCount() {
        return 5; //이미지 개수 리턴(그림이 10개라서 10을 리턴)
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view=null;
        view= inflater.inflate(R.layout.fes_viewpager_item, null);
        ImageView img= (ImageView)view.findViewById(R.id.img_viewpager_childimage);
        img.setImageResource(R.drawable.fes7_1+position);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v==obj;

    }



}
