package com.handelika.fooddelivery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handelika.fooddelivery.Models.Orders;
import com.handelika.fooddelivery.R;

import java.util.List;

import static com.handelika.fooddelivery.callClass.SharePrefCall.getShareDefaults;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersAdapterViewHolder> {

    private Context context;
    private List<Orders> ordersList;
    private OrdersClickItem ordersClickItem;

    public OrdersAdapter(Context context, List<Orders> ordersList, OrdersClickItem ordersClickItem) {
        this.context = context;
        this.ordersList = ordersList;
        this.ordersClickItem = ordersClickItem;
    }

    public interface OrdersClickItem{
        void ordersOnClick (int index);
    }

    @NonNull
    @Override
    public OrdersAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);

        return new OrdersAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapterViewHolder holder, int position) {

        Orders orders = ordersList.get(position);

        int color = Color.parseColor( getShareDefaults("themeColor", context));


        holder.txtDateTime.setText(new StringBuilder().append(orders.getDate()).append("-").append(orders.getTime()).toString());
        holder.txtOrderDetail.setText(orders.getOrderItem());
        holder.txtOrderDetail.setMovementMethod(new ScrollingMovementMethod());


        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow = context.getResources().getDrawable( R.drawable.ic_baseline_keyboard_arrow_right_24 );
        arrow.setTint(color);
        holder.imgArrow.setImageDrawable(arrow);

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class OrdersAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView txtOrderDetail, txtDateTime;
        private ImageView imgArrow;

        public OrdersAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDateTime = itemView.findViewById(R.id.txtDateTime);
            txtOrderDetail = itemView.findViewById(R.id.txtOrderDetail);
            imgArrow = itemView.findViewById(R.id.imgArrow);
        }
    }
}
