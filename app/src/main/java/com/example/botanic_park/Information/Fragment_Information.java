package com.example.botanic_park.Information;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.botanic_park.MainActivity;
import com.example.botanic_park.NetworkConnectionCheck;
import com.example.botanic_park.R;
import com.example.botanic_park.SSLConnect;

import java.lang.reflect.Method;

public class Fragment_Information extends Fragment implements View.OnClickListener {
    private ImageButton guideOutside, guideInside;
    private LinearLayout information_information_use, information_way_to_come, information_news, information_community;

    public Fragment_Information() {
    }

    public static Fragment_Information newInstance() {
        Fragment_Information fragment = new Fragment_Information();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        // 안내도 버튼
        guideOutside = view.findViewById(R.id.guide_outside);
        guideInside = view.findViewById(R.id.guide_inside);
        Glide.with(view)
                .load(R.drawable.guide_outside)
                .fitCenter()
                .thumbnail(0.1f)
                .into(guideOutside);
        Glide.with(view)
                .load(R.drawable.guide_inside)
                .fitCenter()
                .thumbnail(0.1f)
                .into(guideInside);
        guideOutside.setOnClickListener(this);
        guideInside.setOnClickListener(this);

        //카드 정의
        information_information_use = view.findViewById(R.id.information_information_use);
        information_way_to_come = view.findViewById(R.id.information_way_to_come);
        information_news = view.findViewById(R.id.information_news);
        information_community = view.findViewById(R.id.information_community);

        //카드 click listener 추가
        information_information_use.setOnClickListener(this);
        information_way_to_come.setOnClickListener(this);
        information_news.setOnClickListener(this);
        information_community.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.guide_inside:
                excuteWebBrowser("http://botanicpark.seoul.go.kr/front/img/greenhouse_ripplet_02.pdf");
                break;
            case R.id.guide_outside:
                excuteWebBrowser("http://botanicpark.seoul.go.kr/front/img/%EC%95%88%EB%82%B4%EB%8F%84.pdf" );
                break;
            case R.id.information_information_use:
                intent = new Intent(getActivity(), InformationUseActivity.class);
                startActivity(intent);
                break;
            case R.id.information_way_to_come:
                intent = new Intent(getActivity(), WayToComeActivity.class);
                startActivity(intent);
                break;
            case R.id.information_news:
                intent = new Intent(getActivity(), NewsActivity.class);
                checkNewworkBeforeNextActivity(intent);
                break;
            case R.id.information_community:
                intent = new Intent(getActivity(), InconvenienceActivity.class);
                checkNewworkBeforeNextActivity(intent);
                break;
            default:
                break;

        }
    }

    public void excuteWebBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        startActivity(intent);
    }

    private void checkNewworkBeforeNextActivity(Intent intent) {
        if(NetworkConnectionCheck.isConnected(getActivity())) {
            startActivity(intent);
        }
        else {
            Toast.makeText(getActivity(), "네트워크가 연결되어야 이용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }

}
