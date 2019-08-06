package com.iskandar.meapps.Presenter;
/*
 * tgl pengerjaan : 3 agustus 2019
 * nim : 10116121
 * nama : mohammad iskandar
 * kelas : IF-3
 * */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iskandar.meapps.Models.ViewPagerItem;
import com.iskandar.meapps.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<ViewPagerItem> mListVP;

    public ViewPagerAdapter(Context mContext, List<ViewPagerItem> mListVP) {
        this.mContext = mContext;
        this.mListVP = mListVP;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_viewpager, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.img_viewpager);
        TextView title = layoutScreen.findViewById(R.id.judul_viewpager);
        TextView desc = layoutScreen.findViewById(R.id.desc_viewpager);

        title.setText(mListVP.get(position).getJudul_vp());
        desc.setText(mListVP.get(position).getDesc_vp());
        imgSlide.setImageResource(mListVP.get(position).getImg_vp());

        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return mListVP.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
