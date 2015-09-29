package com.cm.secret_recipe.listOriginal;

import android.content.Context;
import android.content.Intent;
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
        intent.putExtra("menuName", menu_original.ARRAY_BEVERAGE[getLayoutPosition()].menuName);
        intent.putExtra("menuBrand", menu_original.ARRAY_BEVERAGE[getLayoutPosition()].brandID);
        intent.putExtra("menuPrice1", menu_original.ARRAY_BEVERAGE[getLayoutPosition()].menuPrice1);
        intent.putExtra("menuPrice2", menu_original.ARRAY_BEVERAGE[getLayoutPosition()].menuPrice2);
        intent.putExtra("menuImageResorce", menu_original.ARRAY_BEVERAGE[getLayoutPosition()].drawableID);
        context.startActivity(intent);

    }
}

