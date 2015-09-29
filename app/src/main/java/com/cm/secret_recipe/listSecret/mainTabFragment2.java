package com.cm.secret_recipe.listSecret;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cm.secret_recipe.R;
import com.cm.secret_recipe.menu_original;

import java.util.ArrayList;
import java.util.List;

public class mainTabFragment2 extends Fragment  {
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    int position = 0;


    //다이얼로그에 필요한 Bundle과 Context
    Bundle bundle;
    Context context;

    mainTabFragmentAdapter2 adapter;
    List<mainTabFragmentMainData2> mainList = new ArrayList<mainTabFragmentMainData2>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = savedInstanceState;
        context = container.getContext();

        //리스트 보여주는 fragment입니다
        View v = inflater.inflate(R.layout.main_list,container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.main_list_recycler);
        manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.scrollToPosition(position);
        recyclerView.setLayoutManager(manager);
        adapter = new mainTabFragmentAdapter2(mainList);
        recyclerView.setAdapter(adapter);

        for (int i = menu_original.ARRAY_BEVERAGE_SECRET.length - 1; i >= 0; --i) {
            adapter.add(createMainList(menu_original.ARRAY_BEVERAGE_SECRET[i].menuName, menu_original.ARRAY_BEVERAGE_SECRET[i].drawableID),position);


        }


        manager.scrollToPosition(position);



        return v;
    }

    private mainTabFragmentMainData2 createMainList(String listName, int listImage){
        mainTabFragmentMainData2 main = new mainTabFragmentMainData2();
        main.setListName(listName);
        main.setListImage(listImage);

        return main;
    }








}
