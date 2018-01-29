package com.cm.secret_recipe.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cm.secret_recipe.R;
import com.cm.secret_recipe.ItemOriginalActivity;
import com.cm.secret_recipe.MenuOriginal;


public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView listImage;
    TextView listName;
    Context context;
    int flag;
    public MainViewHolder(View itemView, Context context, int flag) {
        super(itemView);
        this.context = context;
        this.flag = flag;
        listName = (TextView)itemView.findViewById(R.id.list_name);
        listImage = (ImageView)itemView.findViewById(R.id.list_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent(context, ItemOriginalActivity.class);
        if(flag == 1){
            intent.putExtra("menu", MenuOriginal.ARRAY_BEVERAGE[getLayoutPosition()]);
        }else if (flag == 2){
            intent.putExtra("menu", MenuOriginal.ARRAY_BEVERAGE_SECRET[getLayoutPosition()]);
        }else if (flag == 3){
            intent.putExtra("menu", MenuOriginal.starMenu[getLayoutPosition()]);
        }
        context.startActivity(intent);

    }
}

