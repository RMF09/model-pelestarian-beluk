package com.rmf.modelpelestariantradisilisanbeluk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;
import com.rmf.modelpelestariantradisilisanbeluk.Adapters.DataGambar;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterPhotos extends PagerAdapter {

    private Context context;
    private List<DataGambar> photos = new ArrayList<>();
    private PhotoView photoView;
    public ViewPagerAdapterPhotos(Context context, List<DataGambar> photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.photo_slide,null);
        photoView = view.findViewById(R.id.photos);
        photoView.setImageResource(photos.get(position).getImage());
        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
