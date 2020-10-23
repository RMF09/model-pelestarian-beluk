package com.rmf.modelpelestariantradisilisanbeluk.Fragments;

import android.animation.LayoutTransition;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.rmf.modelpelestariantradisilisanbeluk.Adapters.AdapterRVVideo;
import com.rmf.modelpelestariantradisilisanbeluk.ModelData.DataVideo;
import com.rmf.modelpelestariantradisilisanbeluk.R;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private ProgressBar progressBar;
    private LinearLayout layoutDeskripsi;
    public TextView textDeskripsi,textJudul;
    public YouTubePlayerView youTubePlayerView;
    public YouTubePlayer youTubePlayer;
    private RecyclerView rv;
    RecyclerView.LayoutManager rlm;
    public String videoId;
    public loadDataVideoChoronous loadData;

    private BottomNavigationView bottomNavigationView;
    private boolean first =false;
    public VideoFragment(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_video, container, false);

        this.initialUI(v);
        this.actionUI();

        loadData= new loadDataVideoChoronous();
        loadData.execute("Load Data");


        return v;
    }


    private void initialUI(View v){
        progressBar = v.findViewById(R.id.progress_bar);
        textJudul = v.findViewById(R.id.judul_video);
        layoutDeskripsi = v.findViewById(R.id.layout_deskripsi);
        textDeskripsi = v.findViewById(R.id.text_deskripsi);
        youTubePlayerView = v.findViewById(R.id.youtube_player);

        youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                bottomNavigationView.setVisibility(View.GONE);

                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                bottomNavigationView.setVisibility(View.VISIBLE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            }
        });

//        Do not Allow to Google Playstore
//        getLifecycle().addObserver(youTubePlayerView);

        rv = v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rlm = new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(rlm);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubeP) {
                youTubePlayer = youTubeP;
//                    youTubePlayer.loadVideo(videoId,0);
            }
        });

    }

    private void actionUI(){
        layoutDeskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textDeskripsi.getVisibility()==View.GONE){
                    textDeskripsi.setVisibility(View.VISIBLE);
                }else{
                    textDeskripsi.setVisibility(View.GONE);
                }
            }
        });
    }

    public void addDataVideo(){
        List<DataVideo> dataVideoList = new ArrayList<>();
        dataVideoList.add(new DataVideo("VpU5VkFQ2rE","Video Latihan 1","Nothing else sa asdjpas wjpa asdopajs asdkpoas",R.drawable.sawah));
        dataVideoList.add(new DataVideo("HgxUk3BCJ8k","Video Latihan 2","Nothing else sadjapmca apson asdjaiosd iasjoid avn sdaoisd",R.drawable.i1));
        dataVideoList.add(new DataVideo("_UluAcBG5gs","Video Latihan 3","Nothing else sdjao",R.drawable.i2));
        dataVideoList.add(new DataVideo("GFEcOvs6YWk","Video Latihan 4","Nothing else",R.drawable.i3));

        if(first==false){
            videoId  = dataVideoList.get(0).getVideoId();
            first=true;
        }
        AdapterRVVideo adapterRVVideo = new AdapterRVVideo(dataVideoList,this);
//        adapterRVVideo.notifyDataSetChanged();
        adapterRVVideo.setHasStableIds(true);
        rv.setAdapter(adapterRVVideo);
        rv.setHasFixedSize(true);


    }

    public class loadDataVideoChoronous extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            addDataVideo();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
//            loadVideoChoronous loadVideo = new loadVideoChoronous();
//            loadVideo.execute("Load VIdeo");
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();


    }
}
