package com.handelika.fooddelivery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.handelika.fooddelivery.Models.UserAddress;
import com.handelika.fooddelivery.R;

import java.util.List;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

public class UserAddressAdapter extends RecyclerView.Adapter<UserAddressAdapter.UserAddressAdapterViewHolder> {

    private Context context;
    private List<UserAddress> userAddressList;
    private AddressClick addressClick;

    public UserAddressAdapter(Context context, List<UserAddress> userAddressList, AddressClick addressClick) {
        this.context = context;
        this.userAddressList = userAddressList;
        this.addressClick = addressClick;
    }

    public interface AddressClick{
        void onAdressClick(int index);
    }

    @NonNull
    @Override
    public UserAddressAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);

        return new UserAddressAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAddressAdapterViewHolder holder, int position) {

        UserAddress userAddress = userAddressList.get(position);

        int color = Color.parseColor( getShareDefaults("themeColor", context));

        holder.txtAddressHeader.setText(userAddress.getAddressHeader());
        holder.txtAddressDetail.setText(userAddress.getAddressDetail());

        holder.txtAddressHeader.setTextColor(color);

        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow = context.getResources().getDrawable( R.drawable.ic_baseline_keyboard_arrow_right_24 );
        arrow.setTint(color);
        holder.imgArrow.setImageDrawable(arrow);

    }

    @Override
    public int getItemCount() {
        return userAddressList.size();
    }

    public class UserAddressAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView txtAddressHeader,txtAddressDetail;
        private ImageView imgArrow;

        public UserAddressAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAddressHeader = itemView.findViewById(R.id.txtAddressHeader);
            txtAddressDetail = itemView.findViewById(R.id.txtAddressDetail);
            imgArrow = itemView.findViewById(R.id.imgArrow);

            imgArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addressClick.onAdressClick(getLayoutPosition());
                }
            });

        }
    }

    //region gradientColor
    private GradientDrawable gradientBackground(int color) {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {gradientColor(color,1f), gradientColor(color,0.8f),gradientColor(color,0.6f)});
        gd.setCornerRadius(0f);

        return gd;
    }

    private int gradientColor(int color, float factor) {

        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);


        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }
    //endregion

}
