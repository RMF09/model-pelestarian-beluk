package com.rmf.modelpelestariantradisilisanbeluk.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rmf.modelpelestariantradisilisanbeluk.Fragments.VideoFragment;
import com.rmf.modelpelestariantradisilisanbeluk.ModelData.DataVideo;
import com.rmf.modelpelestariantradisilisanbeluk.R;

import java.util.List;

public class AdapterRVVideo extends RecyclerView.Adapter<AdapterRVVideo.Video> {

    private List<DataVideo> mList;
    private VideoFragment vf;
    public AdapterRVVideo(List<DataVideo> mList, VideoFragment vf) {
        this.mList = mList;
        this.vf = vf;
    }

    @NonNull
    @Override
    public Video onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video,viewGroup,false);

        return new Video(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Video video, int i) {
        Glide.with(video.viewContext.getContext()).load(mList.get(i).getImage()).into(video.imageView);
        final String videoID = mList.get(i).getVideoId();
        final String deskripsi = mList.get(i).getDeskripsi();
        final String judul = mList.get(i).getJudul();

        video.judulVideo.setText(judul);

        ViewGroup.LayoutParams params = video.viewContext.getLayoutParams();
        if(vf.videoId.equals(videoID)){
            params.height =0;
            video.viewContext.setLayoutParams(params);
        }

        video.clItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.videoId = videoID;
                vf.textDeskripsi.setText(deskripsi);
                vf.textJudul.setText(judul);
                vf.youTubePlayer.loadVideo(vf.videoId,0);
                vf.addDataVideo();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Video extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView judulVideo;
        View viewContext;
        ConstraintLayout clItem;

        public Video(@NonNull View itemView) {
            super(itemView);
            viewContext = itemView;
            clItem = itemView.findViewById(R.id.item_video_constraintl);
            judulVideo = itemView.findViewById(R.id.judul_video);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
