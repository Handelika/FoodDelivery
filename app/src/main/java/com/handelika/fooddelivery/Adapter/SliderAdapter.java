package com.handelika.fooddelivery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handelika.fooddelivery.Models.Slider;
import com.handelika.fooddelivery.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderAdapterViewHolder> {

    private Context context;
    private List<Slider> sliderList;
    private SliderOnClick sliderOnClick;

    public SliderAdapter(Context context, List<Slider> sliderList, SliderOnClick sliderOnClick) {
        this.context = context;
        this.sliderList = sliderList;
        this.sliderOnClick = sliderOnClick;
    }

    public interface SliderOnClick{
        void sliderBtnOnClick(int index);
    }

    @NonNull
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false);

        return new SliderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapterViewHolder holder, int position) {

        Slider slider = sliderList.get(position);

        //holder.txtHeader.setText(slider.getHeader());

        String uri = "@drawable/" + slider.getImgUrl();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable res = context.getResources().getDrawable(imageResource);
        holder.imgUrl.setImageDrawable(res);

    }

    @Override
    public int getItemCount() {
        return sliderList.size();
    }

    public class SliderAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUrl;
        private TextView txtHeader;

        public SliderAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUrl = itemView.findViewById(R.id.imgUrl);
            txtHeader = itemView.findViewById(R.id.txtHeader);

        }
    }
}
