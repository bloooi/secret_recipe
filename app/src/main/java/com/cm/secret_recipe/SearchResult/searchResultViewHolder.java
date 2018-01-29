package com.cm.secret_recipe.SearchResult;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cm.secret_recipe.R;
import com.cm.secret_recipe.ItemOriginalActivity;
import com.cm.secret_recipe.MenuOriginal;


public class searchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView listImage;
    TextView listName;
    Context context;
    public searchResultViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        listName = (TextView)itemView.findViewById(R.id.list_name);
        listImage = (ImageView)itemView.findViewById(R.id.list_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ItemOriginalActivity.class);
        intent.putExtra("typeID", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].typeID);
        intent.putExtra("menuName", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].menuName);
        intent.putExtra("menuBrand", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].brandID);
        intent.putExtra("menuPrice1", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].menuPrice1);
        intent.putExtra("menuPrice2", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].menuPrice2);
        intent.putExtra("menuImageResorce", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].drawableID);
        if (MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].typeID == 1) {
            intent.putExtra("menuIngredients1", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].ingredients[0]);
            intent.putExtra("menuIngredients2", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].ingredients[1]);
            intent.putExtra("menuIngredients3", MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].ingredients[2]);
            intent.putExtra("menuExtras1Name", String.valueOf(MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].extras[0].name));
            intent.putExtra("menuExtras2Name", String.valueOf(MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].extras[1].name));
            intent.putExtra("menuExtras3Name", String.valueOf(MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].extras[2].name));

            intent.putExtra("menuExtras1Price", Integer.parseInt(String.valueOf(MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].extras[0].price)));
            intent.putExtra("menuExtras2Price", Integer.parseInt(String.valueOf(MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].extras[1].price)));
            intent.putExtra("menuExtras3Price", Integer.parseInt(String.valueOf(MenuOriginal.SearchWithName(searchResultActivity.searchName)[getLayoutPosition()].extras[2].price)));
        }
        context.startActivity(intent);
    }
}

