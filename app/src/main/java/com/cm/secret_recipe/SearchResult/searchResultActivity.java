package com.cm.secret_recipe.SearchResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.cm.secret_recipe.R;
import com.cm.secret_recipe.MenuOriginal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ijaebeom on 2015. 9. 15..
 */
public class searchResultActivity extends AppCompatActivity {

    LinearLayoutManager manager;

    List<searchResultData> searchResultData = new ArrayList<searchResultData>();

    searchResultAdapter adapter;
    RecyclerView recyclerView;

    DrawerArrowDrawable drawerArrowDrawable;
    static String searchName;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);

        //검색 결과 화면으로 넘어 온 뒤의 정보값을 받아온다.
        Intent intent = getIntent();
        //메인 엑티비티에서 extra로 쏜 정보를 가져온다.
        searchName = intent.getStringExtra("searchName");
        //레이아웃 구성에 필요한 사항들
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar_research);

        MenuOriginal[] menu = MenuOriginal.SearchWithName(searchName);

        //리스트 뷰 초기화
        recyclerView = (RecyclerView)findViewById(R.id.search_recycler);
        // 리스트 뷰 어뎁터 인스턴스 생성
        adapter = new searchResultAdapter(searchResultData);
        //recyclerView는 리니어 매니저를 반드시 사용한다.
        //자세한 사용법은 http://www.kmshack.kr/2014/10/android-recyclerview 참고.
        manager  = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.scrollToPosition(position);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        toolbar.setTitle(searchName);
        //화살표 아이콘
        drawerArrowDrawable = new DrawerArrowDrawable(this);
        drawerArrowDrawable.setProgress(1.0f);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(drawerArrowDrawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResultActivity.this.finish();
            }
        });


        for (int i = menu.length-1; i>= 0; i--){
            adapter.add(createResultList(menu[i].menuName, menu[i].drawableID),position);
        }
        manager.scrollToPosition(position);
    }

    public searchResultData createResultList(String listName, int listImage ){
        //실질적인 데이터가 저장되는 곳이다.
        searchResultData resultData = new searchResultData();
        resultData.setListName(listName);
        resultData.setListImage(listImage);
        //Array에서 0번에 저장되어 있는 searchResultInputData 객체를 꺼내 객체속 데이터를 입력해 넣어준다

        return resultData;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
