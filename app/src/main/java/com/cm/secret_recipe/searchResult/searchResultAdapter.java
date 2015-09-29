package com.cm.secret_recipe.searchResult;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cm.secret_recipe.R;

import java.util.List;

public class searchResultAdapter extends RecyclerView.Adapter<searchResultViewHolder> {

    List<searchResultData> mainData;

    public searchResultAdapter(List<searchResultData> mainData){
        this.mainData = mainData;

    }

    public void add(searchResultData main, int position){
        mainData.add(position, main);
        notifyItemInserted(position);

    }

    @Override
    public searchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewMain = (ViewGroup)inflater.inflate(R.layout.list_view, parent, false);
        searchResultViewHolder resultViewHolder = new searchResultViewHolder(viewMain, parent.getContext());
        return resultViewHolder;


    }

    @Override
    public void onBindViewHolder(searchResultViewHolder holder, int position) {
        searchResultData main = mainData.get(position);
        searchResultViewHolder resultViewHolder = (searchResultViewHolder)holder;
        resultViewHolder.listName.setText(main.getListName());
        resultViewHolder.listImage.setImageResource(main.getListImage());

    }

    @Override
    public int getItemCount() {
        return mainData.size();
    }
}
