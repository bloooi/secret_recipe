package com.cm.secret_recipe.listStar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cm.secret_recipe.BusProvider;
import com.cm.secret_recipe.PushEvent;
import com.cm.secret_recipe.R;
import com.cm.secret_recipe.menu_original;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class mainTabFragment2 extends Fragment  {
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    public static int position = 0;



    //다이얼로그에 필요한 Bundle과 Context
    Bundle bundle;
    Context context;

    public static mainTabFragmentAdapter2 adapter;
    public static List<mainTabFragmentMainData2> mainList = new ArrayList<mainTabFragmentMainData2>();


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

        adapter.notifyDataSetChanged();

        Log.d("starmenu", String.valueOf(menu_original.starMenu.length));
        for (int i = menu_original.starMenu.length; i>=0; --i){
            if (menu_original.starMenu.length == mainList.size()){
                break;
            }else {
                adapter.add(createMainList(menu_original.starMenu[i-1].menuName, menu_original.starMenu[i-1].drawableID),position);
                adapter.notifyDataSetChanged();
                position++;
            }


        }


        manager.scrollToPosition(position);



        return v;
    }


    public static mainTabFragmentMainData2 createMainList(String listName, int listImage){
        mainTabFragmentMainData2 main = new mainTabFragmentMainData2();
        main.setListName(listName);
        main.setListImage(listImage);

        return main;
    }









}
