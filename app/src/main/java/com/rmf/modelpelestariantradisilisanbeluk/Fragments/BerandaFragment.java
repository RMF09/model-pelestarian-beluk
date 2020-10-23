package com.rmf.modelpelestariantradisilisanbeluk.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.rmf.modelpelestariantradisilisanbeluk.Adapters.AdapterRVGambar;
import com.rmf.modelpelestariantradisilisanbeluk.Adapters.AdapterRVGambarStaggered;
import com.rmf.modelpelestariantradisilisanbeluk.Adapters.DataGambar;
import com.rmf.modelpelestariantradisilisanbeluk.MyScrollView;
import com.rmf.modelpelestariantradisilisanbeluk.R;
import com.rmf.modelpelestariantradisilisanbeluk.WatchVideo;

import java.util.ArrayList;
import java.util.List;

public class BerandaFragment extends Fragment {
    Context context;
    Button btnTonton;
    MyScrollView scrollView;
    View view;
    TextView nilai;
    boolean animasiRv2=false,animasiRv1=false;

    public BerandaFragment() {
    }

    RecyclerView rv1,rv2;
    AdapterRVGambar adapterRVGambar1;
    List<DataGambar> listDataGambar1;
    RecyclerView.LayoutManager rlm, rlm2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = container.getContext();
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);
        scrollView = v.findViewById(R.id.beranda_scrollview);
        btnTonton = v.findViewById(R.id.btn_tonton);
        rv1 = v.findViewById(R.id.rv_image1);
        rv2 = v.findViewById(R.id.rv_image2);
        view = v.findViewById(R.id.view);
        nilai =  v.findViewById(R.id.nilai);

        assert container != null;
        rlm = new LinearLayoutManager(container.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rlm2 = new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL);
        rv1.setLayoutManager(rlm);
        rv2.setLayoutManager(rlm2);
        rv1.setVisibility(View.INVISIBLE);
        rv2.setVisibility(View.INVISIBLE);
        addDataImage1();

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    scrollView.startScrollerTask();
                }
                return false;
            }
        });
        scrollView.setOnScrollStoppedListener(new MyScrollView.OnScrollStoppedListener() {
            @Override
            public void onScrollStopped() {
                Rect scrollBounds = new Rect();
                scrollView.getHitRect(scrollBounds);
                if(view.getLocalVisibleRect(scrollBounds) && animasiRv2==false){
                    rv2.setVisibility(View.VISIBLE);
                    runLayoutAnimation(rv2,R.anim.layout_animation_from_right);
                    animasiRv2 =true;
                }
                if(nilai.getLocalVisibleRect(scrollBounds) && animasiRv1==false){
                    rv1.setVisibility(View.VISIBLE);
                    runLayoutAnimation(rv1,R.anim.layout_animation_from_right);
                    animasiRv1= true;
                }
            }
        });

        btnTonton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), WatchVideo.class));
            }
        });




        return v;

    }

    private void addDataImage1(){
        listDataGambar1 = new ArrayList<>();
        listDataGambar1.add(new DataGambar(R.drawable.wp2,0,0));
        listDataGambar1.add(new DataGambar(R.drawable.wp3,0,0));
        listDataGambar1.add(new DataGambar(R.drawable.wp4,0,0));
        listDataGambar1.add(new DataGambar(R.drawable.wp5,0,0));
        adapterRVGambar1 = new AdapterRVGambar(listDataGambar1,context);
        rv1.setAdapter(adapterRVGambar1);

        List<DataGambar> listDataGambar2 = new ArrayList<>();
        listDataGambar2.add(new DataGambar(R.drawable.wp2,350,440));
        listDataGambar2.add(new DataGambar(R.drawable.wp3,360,370));
        listDataGambar2.add(new DataGambar(R.drawable.wp5,360,300));
        listDataGambar2.add(new DataGambar(R.drawable.wp6,350,440));
        listDataGambar2.add(new DataGambar(R.drawable.wp3,360,370));
        listDataGambar2.add(new DataGambar(R.drawable.wp1,360,300));
        AdapterRVGambarStaggered adapterRVGambar2 = new AdapterRVGambarStaggered(listDataGambar2,context);
        rv2.setAdapter(adapterRVGambar2);
    }
    private void runLayoutAnimation(final RecyclerView recyclerView,int anim) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context,anim);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();
        rv1.setVisibility(View.INVISIBLE);
        rv2.setVisibility(View.INVISIBLE);
        animasiRv1=false;
        animasiRv2=false;

//        runLayoutAnimation(rv2,R.anim.layout_animation_from_right);
//        runLayoutAnimation(rv2, R.anim.layout_animation_from_right);
//        runLayoutAnimation(rv2, R.anim.layout_animation_from_right);
    }
}
