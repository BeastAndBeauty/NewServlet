package com.paopao.newservlet.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paopao.newservlet.R;

/**
 * A simple {@link Fragment} subclass.
 * 视频的Fragment
 */
public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    public static VideoFragment newInstance(){
        VideoFragment videoFragment=new VideoFragment();
        return videoFragment;
    }

}
