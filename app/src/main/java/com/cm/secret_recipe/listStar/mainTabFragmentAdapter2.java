package com.cm.secret_recipe.listStar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cm.secret_recipe.BusProvider;
import com.cm.secret_recipe.PushEvent;
import com.cm.secret_recipe.R;
import com.squareup.otto.Subscribe;

import java.util.List;

public class mainTabFragmentAdapter2 extends RecyclerView.Adapter<mainTabFragmentMainViewHolder2> {

    List<mainTabFragmentMainData2> mainData;


    public mainTabFragmentAdapter2(List<mainTabFragmentMainData2> mainData){
        this.mainData = mainData;

    }

    public void add(mainTabFragmentMainData2 main, int position){
        mainData.add(position, main);
        notifyItemInserted(position);

    }

    @Override
    public mainTabFragmentMainViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewMain = (ViewGroup)inflater.inflate(R.layout.list_view, parent, false);
        mainTabFragmentMainViewHolder2 mainViewHolder2 = new mainTabFragmentMainViewHolder2(viewMain, parent.getContext());
        return mainViewHolder2;


    }

    @Override
    public void onBindViewHolder(mainTabFragmentMainViewHolder2 holder, int position) {
        mainTabFragmentMainData2 main = mainData.get(position);
        mainTabFragmentMainViewHolder2 mainViewHolder1 = (mainTabFragmentMainViewHolder2)holder;
        mainViewHolder1.listName.setText(main.getListName());
        mainViewHolder1.listImage.setImageResource(main.getListImage());

    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }


}

