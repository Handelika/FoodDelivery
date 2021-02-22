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

import com.handelika.fooddelivery.Models.Menu;
import com.handelika.fooddelivery.R;
import com.handelika.fooddelivery.callClass.ThemeColors;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuAdapterViewHolder> {

    private Context context;
    private List<Menu> menuList;
    private MenuItemClick menuItemClick;

    public MenuAdapter(Context context, List<Menu> menuList, MenuItemClick menuItemClick) {
        this.context = context;
        this.menuList = menuList;
        this.menuItemClick = menuItemClick;
    }

    public interface MenuItemClick{
        void menuOnClick(int index);
    }

    @NonNull
    @Override
    public MenuAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);

        return new MenuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapterViewHolder holder, int position) {

        Menu menu = menuList.get(position);

        holder.txtMenuHeader.setText(menu.getMenu());
        holder.txtMenuDetail.setText(menu.getMenuDetail());
        holder.txtMenuPrice.setText(menu.getMenuPrice());

        int color = ThemeColors.getThemeColor(context);

        @SuppressLint("UseCompatLoadingForDrawables") Drawable add = context.getResources().getDrawable( R.drawable.ic_baseline_add_24 );
        add.setTint(color);
        holder.imgAdd.setImageDrawable(add);

        String uri = "@drawable/" + menu.getMenuImgUrl();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.imgMenu.setImageDrawable(res);

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgMenu, imgAdd;
        private TextView txtMenuHeader, txtMenuDetail, txtMenuPrice;

        public MenuAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMenu = itemView.findViewById(R.id.imgMenu);
            imgAdd = itemView.findViewById(R.id.imgAdd);
            txtMenuHeader = itemView.findViewById(R.id.txtMenuHeader);
            txtMenuDetail = itemView.findViewById(R.id.txtMenuDetail);
            txtMenuPrice = itemView.findViewById(R.id.txtMenuPrice);

            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuItemClick.menuOnClick(getLayoutPosition());
                }
            });
        }
    }
}
