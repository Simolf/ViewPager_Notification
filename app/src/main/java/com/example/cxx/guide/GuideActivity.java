package com.example.cxx.guide;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView[]dots;
    private int[]ids={R.id.iv1,R.id.iv2,R.id.iv3};
    private Button start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initDots();
    }
    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one,null));
        views.add(inflater.inflate(R.layout.two,null));
        views.add(inflater.inflate(R.layout.three,null));
        vpAdapter = new ViewPagerAdapter(views,this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
        vp.addOnPageChangeListener(this);
    }
    private void initDots(){
        dots= new ImageView[views.size()];
        for (int i=0;i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    //当页面滑动时调用
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    //当前鑫的页面被选中时条用
    public void onPageSelected(int position) {
        for(int i=0;i<ids.length;i++){
            if(position==i){
                dots[i].setImageResource(R.drawable.bullet_grey);
            }else{
                dots[i].setImageResource(R.drawable.bullet_white);
            }
        }

    }

    @Override
    //当页面滑动状态改变时调用
    public void onPageScrollStateChanged(int i) {

    }
}
