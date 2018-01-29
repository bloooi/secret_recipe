package com.cm.secret_recipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemOriginalActivity extends AppCompatActivity {
    private Context context;

    Toolbar toolbar;
    private MenuOriginal menu;
    DrawerArrowDrawable arrowDrawable;
    String menuBrandS;
    TextView menuBrand, menuName, menuPrice1, menuPrice2;
    TextView arrowText1, arrowText2, arrowText3, menu1, menu2, menu3;
    ImageView arrow1, arrow2, arrow3;

    ImageView menuImage;

    ImageButton star;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = this;
        Intent intent = getIntent();
        menu = intent.getParcelableExtra("menu");

        super.onCreate(savedInstanceState);
        if (menu.typeID == 0) {
            setContentView(R.layout.item_view_original);
        }else if (menu.typeID == 1){
            setContentView(R.layout.item_view_secret);
        }


        toolbar = (Toolbar)findViewById(R.id.tool_bar_item);
        arrowDrawable = new DrawerArrowDrawable(this);
        arrowDrawable.setProgress(1f);


        menuBrand = (TextView)findViewById(R.id.coffee_brand);
        menuName = (TextView)findViewById(R.id.coffee_name);
        menuPrice1 = (TextView)findViewById(R.id.coffee_price1);
        menuPrice2 = (TextView)findViewById(R.id.coffee_price2);
        menuImage = (ImageView)findViewById(R.id.coffee_image);
        star = (ImageButton)findViewById(R.id.star);

        if (menu.typeID == 1){
            arrow1 = (ImageView)findViewById(R.id.arrow1);
            arrow2 = (ImageView)findViewById(R.id.arrow2);
            arrow3 = (ImageView)findViewById(R.id.arrow3);

            arrowText1 = (TextView)findViewById(R.id.arrow1text);
            arrowText2 = (TextView)findViewById(R.id.arrow2text);
            arrowText3 = (TextView)findViewById(R.id.arrow3text);
            menu1 = (TextView)findViewById(R.id.coffee_menu1);
            menu2 = (TextView)findViewById(R.id.coffee_menu2);
            menu3 = (TextView)findViewById(R.id.coffee_menu3);

            arrowText1.setText(menu.ingredients[0]);
            arrowText2.setText(menu.ingredients[1]);
            arrowText3.setText(menu.ingredients[2]);

            menu1.setText(menu.extras[0].name + " " + menu.extras[0].price + "원");
            menu2.setText(menu.extras[1].name + " " + menu.extras[1].price + "원");
            menu3.setText(menu.extras[2].name + " " + menu.extras[2].price + "원");

            if (menu.ingredients[0] == null){
                arrow1.setVisibility(View.INVISIBLE);
                arrowText1.setVisibility(View.INVISIBLE);
                menu1.setVisibility(View.GONE);
            }

            if (menu.ingredients[1] == null){
                arrow2.setVisibility(View.INVISIBLE);
                arrowText2.setVisibility(View.INVISIBLE);
                menu2.setVisibility(View.GONE);
            }

            if (menu.ingredients[2] == null){
                arrow3.setVisibility(View.INVISIBLE);
                arrowText3.setVisibility(View.INVISIBLE);
                menu3.setVisibility(View.GONE);
            }

        }

        menuImage.setImageResource(menu.drawableID);

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MenuOriginal.SearchStar(menu.menuName, MenuOriginal.STARMENU)){
                    //여기서 STARMENU List가 추가됩니다
                    MenuOriginal.STARMENU.add(menu);
                    final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ItemOriginalActivity.this);
                    MenuOriginal.starMenu = MenuOriginal.STARMENU.toArray(new MenuOriginal[MenuOriginal.STARMENU.size()]);
                    MenuOriginal.save(context);
                    builder.setTitle("즐겨찾기 등록")
                            .setMessage("즐겨찾기에 등록되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }else{
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ItemOriginalActivity.this);
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

        if (menu.brandID == 0){
            menuBrandS = "공차";
        }else if(menu.brandID == 1){
            menuBrandS = "Starbucks";
        }else if(menu.brandID == 2){
            menuBrandS = "탐 앤 탐스";
        }else if (menu.brandID == 3){
            menuBrandS = "할리스 커피";
        }
        toolbar.setTitle(menuBrandS + " - " + menu.menuName);
        toolbar.setNavigationIcon(arrowDrawable);

        if (menu.menuPrice1 == 0){
            menuPrice1.setVisibility(View.GONE);
        } else if (menu.menuPrice2 == 0){
            menuPrice2.setVisibility(View.GONE);
        }
        menuBrand.setText(menuBrandS);
        menuName.setText(menu.menuName);
        menuPrice1.setText("Tall 사이즈 : " + menu.menuPrice1 + "원");
        menuPrice2.setText("Grande 사이즈 : " + menu.menuPrice2 + "원");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemOriginalActivity.this.finish();
            }
        }); // d
    }

}

