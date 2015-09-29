package com.cm.secret_recipe.listSecret;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cm.secret_recipe.R;
import com.cm.secret_recipe.itemOriginalActivity;
import com.cm.secret_recipe.menu_original;


public class mainTabFragmentMainViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView listImage;
    TextView listName;
    Context context;
    public mainTabFragmentMainViewHolder2(View itemView, Context context) {
        super(itemView);
        this.context = context;
        listName = (TextView)itemView.findViewById(R.id.list_name);
        listImage = (ImageView)itemView.findViewById(R.id.list_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, itemOriginalActivity.class);

        intent.putExtra("menuName", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].menuName);
        intent.putExtra("menuBrand", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].brandID);
        intent.putExtra("menuPrice1", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].menuPrice1);
        intent.putExtra("menuPrice2", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].menuPrice2);
        intent.putExtra("menuImageResorce", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].drawableID);
        intent.putExtra("menuIngredients1", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].ingredients[0]);
        intent.putExtra("menuIngredients2", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].ingredients[1]);
        intent.putExtra("menuIngredients3", menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].ingredients[2]);
        intent.putExtra("menuExtras1Name", String.valueOf(menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].extras[0].name));
        intent.putExtra("menuExtras2Name", String.valueOf(menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].extras[1].name));
        intent.putExtra("menuExtras3Name", String.valueOf(menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].extras[2].name));

        intent.putExtra("menuExtras1Price", Integer.parseInt(String.valueOf(menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].extras[0].price)));
        intent.putExtra("menuExtras2Price", Integer.parseInt(String.valueOf(menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].extras[1].price)));
        intent.putExtra("menuExtras3Price", Integer.parseInt(String.valueOf(menu_original.ARRAY_BEVERAGE_SECRET[getLayoutPosition()].extras[2].price)));


        context.startActivity(intent);
    }
}

