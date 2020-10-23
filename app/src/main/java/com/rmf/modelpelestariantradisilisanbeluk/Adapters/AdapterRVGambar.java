package com.rmf.modelpelestariantradisilisanbeluk.Adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.rmf.modelpelestariantradisilisanbeluk.R;
import com.rmf.modelpelestariantradisilisanbeluk.ViewPagerAdapter;
import com.rmf.modelpelestariantradisilisanbeluk.ViewPagerAdapterPhotos;

import java.util.List;

public class AdapterRVGambar extends RecyclerView.Adapter<AdapterRVGambar.Gambar> {

    private final List<DataGambar> mList;
    private final Context context;
    AlertDialog alertDialog;
    private ViewPagerAdapterPhotos viewPagerAdapterPhotos;
    private ViewPager viewPager;

    public AdapterRVGambar(List<DataGambar> mList, Context context) {
        this.mList = mList;
        this.context = context;
        setUpViewPagerPhotos();
        setUpDialogPhotos();
    }

    @NonNull
    @Override
    public Gambar onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image,viewGroup,false);

        return new Gambar(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Gambar gambar, int i) {
        Glide.with(gambar.viewContext.getContext()).load(mList.get(i).getImage()).into(gambar.imageView);
        if(mList.get(i).getWidthCard()!=0){
            gambar.imageView.setLayoutParams(
                    new LinearLayout.LayoutParams(mList.get(i).getWidthCard(),mList.get(i).getHeightCard()));
        }

        final int position = i;
        gambar.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhoto(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class Gambar extends RecyclerView.ViewHolder{
        final ImageView imageView;
        final View viewContext;


        public Gambar(@NonNull View itemView) {
            super(itemView);
            viewContext = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.item_image);

        }
    }

    private void setUpViewPagerPhotos(){
        viewPagerAdapterPhotos = new ViewPagerAdapterPhotos(context,mList);
    }
    private void setUpDialogPhotos(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_photos,null);

        viewPager = view.findViewById(R.id.viewpager_photo);

        viewPager.setAdapter(viewPagerAdapterPhotos);
        viewPager.setOffscreenPageLimit(mList.size());
        builder.setView(view);
        alertDialog = builder.create();
    }

    private void showPhoto(int position){
//        position =2;

        viewPager.setCurrentItem(position,true);
        alertDialog.show();
    }
}
