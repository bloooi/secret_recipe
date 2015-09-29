package com.cm.secret_recipe;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.cm.secret_recipe.searchResult.searchResultActivity;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener{

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    DrawerArrowDrawable drawerArrowDrawable;
    ViewPager tabPager;
    TabLayout tabLayout;
    SearchView searchView;
    MenuItem searchMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, splash.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //splash Activity
        initialize();

        menu_original.load(getApplicationContext());
        //액션바
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //네비게이션 메뉴 슬라이드 레이아웃
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //네비게이션 메뉴 버튼
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.name1, R.string.name2);
        //네이게이션 메뉴 레이아웃
        navigationView = (NavigationView)findViewById(R.id.main_drawer_view);
        //시크릿, 기본메뉴, 추천메뉴 화면을 슬라이드해서 보여주는 뷰페이저
        tabPager = (ViewPager)findViewById(R.id.tab_pager);
        // 시크릿, 기본메뉴, 추천메뉴가 적혀있는 탭
        tabLayout = (TabLayout)findViewById(R.id.tab_view);
        // 네비게이션 메뉴 버튼 아이콘
        drawerArrowDrawable = new DrawerArrowDrawable(this);
        //toolbar와 actionbar를 연결하여 액션바 출력
        setSupportActionBar(toolbar);

        //슬라이드 레이아웃의 네비게이션 버튼 연결
        drawerLayout.setDrawerListener(toggle);
        //액션바 위에 네비게이션 메뉴 아이콘을 drawerArrowDrawable의 아이콘을 설정
        toolbar.setNavigationIcon(drawerArrowDrawable);
        //뷰페이저를 전체 제어 하는 adapter를 만들어 tabPager의 adapter로 사용
        setupViewPager(tabPager);
        //뷰페이저와 탭을 연결
        tabLayout.setupWithViewPager(tabPager);

        //네비게이션에서 메뉴을 클릭했을때 나타나는 현상 작성
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                //네비게이션 메뉴 중에 id값을 받아와 어떤 action을 취할지 보여주는 코드
                switch (menuItem.getItemId()) {
                    case R.id.setting: //설정하기 메뉴를 눌렀을 경우
                        Intent intent = new Intent(MainActivity.this, settingActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    //스플레시 엑티비티 관련 메서드
    private void initialize()
    {
        InitializationRunnable init = new InitializationRunnable();
        new Thread(init).start();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new com.cm.secret_recipe.listSecret.mainTabFragment2(), "시크릿");
        adapter.addFrag(new com.cm.secret_recipe.listOriginal.mainTabFragment2(), "기본메뉴");
        adapter.addFrag(new com.cm.secret_recipe.listStar.mainTabFragment2(), "즐겨찾기");
        viewPager.setAdapter(adapter);
    }

    //액션 바 위에 메뉴 생성 메서드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //검색 설정
        searchMenu = menu.findItem(R.id.action_search);
        final SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //검색할 문자열을 입력하고 확인을 눌렀을때 어떻게 action을 취할지 보여주는 메서드
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent =new Intent(MainActivity.this, searchResultActivity.class);
                intent.putExtra("searchName", query);
                startActivity(intent);
                searchView.clearFocus();
                searchView.onActionViewCollapsed();
                return false;
            }

            //검색뷰에 검색할 문자열이 바뀔 때 사용 되는 메서드 (이 메서드의 용도는 미리 검색 문자열을 찾아주는 용도인듯)
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    //액션바 메뉴를 눌렀을때 각각 메뉴마다 다르게 action을 보여주도록 설정 하는 메서드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(toggle.onOptionsItemSelected(item)){
            return true;
        }else if(id == R.id.action_search){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //네비게이션이 슬라이드 되는 동안 나타나는 action 설정 메서드
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        //네비게이션 아이콘이 메뉴에서 화살표로 바뀌도록 설정
        drawerArrowDrawable.setProgress(slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}

//스플레쉬 엑티비티 보여주는 실질적인 스레드 클래스
class InitializationRunnable implements Runnable
{
    public void run()
    {
        for (int i=0; i<5;i++)
        {
            try
            {

                Thread.sleep(1000);
                // LogCat으로 로그를 보면 스플래시 화면 표시 중에 계속 이미 메시지가 표시됨을 확인할 수 있습니다.
                Log.d("SPLASH", "------------- initialize..........");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
