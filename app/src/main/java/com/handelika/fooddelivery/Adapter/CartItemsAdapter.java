package com.handelika.fooddelivery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handelika.fooddelivery.Models.CartItems;
import com.handelika.fooddelivery.R;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemsAdapterViewHolder> {

    private Context context;
    private List<CartItems> cartItemsList;
    private CartItemsClick cartItemsClick;

    public CartItemsAdapter(Context context, List<CartItems> cartItemsList, CartItemsClick cartItemsClick) {
        this.context = context;
        this.cartItemsList = cartItemsList;
        this.cartItemsClick = cartItemsClick;
    }

    public interface CartItemsClick{
        void onCartItemClick(int index);
    }

    @NonNull
    @Override
    public CartItemsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);

        return new CartItemsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsAdapterViewHolder holder, int position) {

        CartItems cartItems = cartItemsList.get(position);

        holder.txtCartItem.setText(cartItems.getCartItem());
        holder.txtPrice.setText(cartItems.getPrice());

        for (String ingredients: cartItems.getIngredients()) {
            TextView textView = new TextView(context);
            textView.setTextColor(context.getResources().getColor(R.color.colorGrayLight));
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(ingredients);
            textView.setTextSize(10f);
            holder.linearIngredients.addView(textView);
        }


        String uri = "@drawable/" + cartItems.getCartImagUrl();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.imgCartItem.setBackground(res);

    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }

    public class CartItemsAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCartItem;
        private TextView txtPiece,txtCartItem,txtPrice,txtIngredients;
        private LinearLayout linearIngredients;

        public CartItemsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            txtCartItem = itemView.findViewById(R.id.txtCartItem);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            linearIngredients = itemView.findViewById(R.id.linearIngredients);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartItemsClick.onCartItemClick(getLayoutPosition());
                }
            });
        }
    }
}
