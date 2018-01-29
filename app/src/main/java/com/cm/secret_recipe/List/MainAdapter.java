package com.cm.secret_recipe.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cm.secret_recipe.R;


import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    List<MainData> mainData;
    int flag;
    public MainAdapter(List<MainData> mainData, int flag){
        this.mainData = mainData;
        this.flag = flag;
    }

    public void add(MainData main, int position){
        mainData.add(position, main);
        notifyItemInserted(position);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewMain = (ViewGroup)inflater.inflate(R.layout.list_view, parent, false);
        MainViewHolder mainViewHolder2 = new MainViewHolder(viewMain, parent.getContext(), flag);
        return mainViewHolder2;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        MainData main = mainData.get(position);
        MainViewHolder mainViewHolder = holder;
        mainViewHolder.listName.setText(main.getListName());
        mainViewHolder.listImage.setImageResource(main.getListImage());
    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }
}
