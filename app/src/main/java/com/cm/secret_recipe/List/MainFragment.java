package com.cm.secret_recipe.List;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cm.secret_recipe.R;
import com.cm.secret_recipe.MenuOriginal;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    int position = 0;


    //다이얼로그에 필요한 Bundle과 Context
    Bundle bundle;
    Context context;

    MainAdapter adapter;
    List<MainData> mainList = new ArrayList<MainData>();

    public static MainFragment newInstance(int flag) {

        Bundle args = new Bundle();
        args.putInt("flag", flag);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = savedInstanceState;
        context = container.getContext();
        int flag = 0;
        if (getArguments() != null){
            flag = getArguments().getInt("flag");
        }
        //리스트 보여주는 fragment입니다
        View v = inflater.inflate(R.layout.main_list,container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.main_list_recycler);
        manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.scrollToPosition(position);
        recyclerView.setLayoutManager(manager);
        adapter = new MainAdapter(mainList, flag);
        recyclerView.setAdapter(adapter);
        MenuOriginal[] menus;
        if (flag == 1){
            menus = MenuOriginal.ARRAY_BEVERAGE;
        }else if (flag == 2){
            menus = MenuOriginal.ARRAY_BEVERAGE_SECRET;
        }else {
            menus = MenuOriginal.starMenu;
        }
        for (int i = menus.length-1; i>=0; --i){
            adapter.add(createMainList(menus[i].menuName, menus[i].drawableID),position);
        }

        manager.scrollToPosition(position);
        return v;
    }

    private MainData createMainList(String listName, int listImage){
        MainData main = new MainData();
        main.setListName(listName);
        main.setListImage(listImage);

        return main;
    }











}
