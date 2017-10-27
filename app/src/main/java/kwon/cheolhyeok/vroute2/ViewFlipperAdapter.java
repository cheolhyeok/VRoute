package kwon.cheolhyeok.vroute2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gourav on 05-05-2016.
 */
public class ViewFlipperAdapter extends BaseAdapter {
    Context context;
    int[] fruitImages;
    String[] fruitNames;
    LayoutInflater inflter;

    public ViewFlipperAdapter(Context applicationContext, String[] fruitNames, int[] fruitImages) {
        this.context = applicationContext;
        this.fruitImages = fruitImages;
        this.fruitNames = fruitNames;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return fruitNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.flipper_list_item, null);
        TextView fruitName = (TextView) view.findViewById(R.id.fruitName);
        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruitImage);
        fruitName.setText(fruitNames[position]);
        fruitImage.setImageResource(fruitImages[position]);
        return view;
    }
}
