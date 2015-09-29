package com.cm.secret_recipe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cm.secret_recipe.listStar.mainTabFragment2;
import com.cm.secret_recipe.listStar.mainTabFragmentAdapter2;
import com.cm.secret_recipe.listStar.mainTabFragmentMainData2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ijaebeom on 2015. 9. 15..
 */
public class itemOriginalActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerArrowDrawable arrowDrawable;
    String menuBrandS, menuNameS;
    int menuBrandI, menuPrice1S, menuPrice2S, typeID;
    TextView menuBrand, menuName, menuPrice1, menuPrice2;

    String menuIngredients[] = new String[3], extrasName[] = new String[3];
    int extrasPrice[] = new int[3];
    menu_original.Extra []extras = new menu_original.Extra[3];
    TextView arrowText1, arrowText2, arrowText3, menu1, menu2, menu3;
    ImageView arrow1, arrow2, arrow3;

    int menuImageI;
    ImageView menuImage;

    ImageButton star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        typeID = intent.getIntExtra("typeID", 1);

        super.onCreate(savedInstanceState);
        if (typeID ==0) {
            setContentView(R.layout.item_view_original);
        }else if (typeID == 1){
            setContentView(R.layout.item_view_secret);
        }


        toolbar = (Toolbar)findViewById(R.id.tool_bar_item);
        arrowDrawable = new DrawerArrowDrawable(this);
        arrowDrawable.setProgress(1f);


        menuBrandI = intent.getIntExtra("menuBrand", 0);
        menuPrice1S = intent.getIntExtra("menuPrice1", 0);
        menuPrice2S = intent.getIntExtra("menuPrice2", 0);
        menuNameS = intent.getStringExtra("menuName");
        menuImageI = intent.getIntExtra("menuImageResorce", R.drawable.americano3);
        menuBrand = (TextView)findViewById(R.id.coffee_brand);
        menuName = (TextView)findViewById(R.id.coffee_name);
        menuPrice1 = (TextView)findViewById(R.id.coffee_price1);
        menuPrice2 = (TextView)findViewById(R.id.coffee_price2);
        menuImage = (ImageView)findViewById(R.id.coffee_image);
        star = (ImageButton)findViewById(R.id.star);


        menuImage.setImageResource(menuImageI);
        if (typeID == 1){
            menuIngredients[0] = intent.getStringExtra("menuIngredients1");
            menuIngredients[1] = intent.getStringExtra("menuIngredients2");
            menuIngredients[2] = intent.getStringExtra("menuIngredients3");


            extrasName[0] = intent.getStringExtra("menuExtras1Name");
            extrasName[1] = intent.getStringExtra("menuExtras2Name");
            extrasName[2] = intent.getStringExtra("menuExtras3Name");

            extrasPrice[0] = intent.getIntExtra("menuExtras1Price", 0);
            extrasPrice[1] = intent.getIntExtra("menuExtras2Price", 0);
            extrasPrice[2] = intent.getIntExtra("menuExtras3Price", 0);

            extras[0] = new menu_original.Extra(extrasName[0], extrasPrice[0]);
            extras[1] = new menu_original.Extra(extrasName[1], extrasPrice[1]);
            extras[2] = new menu_original.Extra(extrasName[2], extrasPrice[2]);

            arrow1 = (ImageView)findViewById(R.id.arrow1);
            arrow2 = (ImageView)findViewById(R.id.arrow2);
            arrow3 = (ImageView)findViewById(R.id.arrow3);

            arrowText1 = (TextView)findViewById(R.id.arrow1text);
            arrowText2 = (TextView)findViewById(R.id.arrow2text);
            arrowText3 = (TextView)findViewById(R.id.arrow3text);
            menu1 = (TextView)findViewById(R.id.coffee_menu1);
            menu2 = (TextView)findViewById(R.id.coffee_menu2);
            menu3 = (TextView)findViewById(R.id.coffee_menu3);

            arrowText1.setText(menuIngredients[0]);
            arrowText2.setText(menuIngredients[1]);
            arrowText3.setText(menuIngredients[2]);

            menu1.setText(extrasName[0] + " " + extrasPrice[0] + "원");
            menu2.setText(extrasName[1] + " " + extrasPrice[1] + "원");
            menu3.setText(extrasName[2] + " " + extrasPrice[2] + "원");

            if (menuIngredients[0] == null){
                arrow1.setVisibility(View.INVISIBLE);
                arrowText1.setVisibility(View.INVISIBLE);
                menu1.setVisibility(View.GONE);
            }

            if (menuIngredients[1] == null){
                arrow2.setVisibility(View.INVISIBLE);
                arrowText2.setVisibility(View.INVISIBLE);
                menu2.setVisibility(View.GONE);
            }

            if (menuIngredients[2] == null){
                arrow3.setVisibility(View.INVISIBLE);
                arrowText3.setVisibility(View.INVISIBLE);
                menu3.setVisibility(View.GONE);
            }

        }



        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!menu_original.SearchStar(menuNameS, menu_original.STARMENU)){
                    //typeID가 0이면 원래 메뉴를 뜻합니다.
                    if (typeID ==0){
                        //여기서 STARMENU List가 추가됩니다
                        menu_original.STARMENU.add(new menu_original(menuBrandI,menuNameS,menuPrice1S,menuPrice2S,menuImageI,"",typeID));
                        //동시에 List를 배열로 바꿔줍니다.
                        menu_original.starMenu = menu_original.STARMENU.toArray(new menu_original[menu_original.STARMENU.size()]);

                        //typeID가 1이면 원래 메뉴를 뜻합니다.
                    }else if (typeID == 1){
                        //여기서 STARMENU List가 추가됩니다
                        menu_original.STARMENU.add(new menu_original(menuBrandI,menuNameS,menuPrice1S,menuPrice2S,menuImageI,"",typeID,menuIngredients,extras));
                        //동시에 List를 배열로 바꿔줍니다.
                        menu_original.starMenu = menu_original.STARMENU.toArray(new menu_original[menu_original.STARMENU.size()]);
                    }
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(itemOriginalActivity.this);
                    builder.setTitle("즐겨찾기 등록")
                            .setMessage("즐겨찾기에 등록되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }else{
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(itemOriginalActivity.this);
                    builder.setMessage("이미 즐겨찾기에 등록되어 있습니다.")
                           .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           })
                            .show();
                }
            }
        });

        if (menuBrandI == 0){
            menuBrandS = "공차";
        }else if(menuBrandI == 1){
            menuBrandS = "Starbucks";
        }else if(menuBrandI == 2){
            menuBrandS = "탐 앤 탐스";
        }else if (menuBrandI == 3){
            menuBrandS = "할리스 커피";
        }
        toolbar.setTitle(menuBrandS + " - " + menuNameS);
        toolbar.setNavigationIcon(arrowDrawable);

        if (menuPrice1S == 0){
            menuPrice1.setVisibility(View.GONE);
        } else if (menuPrice2S == 0){
            menuPrice2.setVisibility(View.GONE);
        }
        menuBrand.setText(menuBrandS);
        menuName.setText(menuNameS);
        menuPrice1.setText("Tall 사이즈 : " + menuPrice1S + "원");
        menuPrice2.setText("Grande 사이즈 : " + menuPrice2S + "원");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOriginalActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }
}

