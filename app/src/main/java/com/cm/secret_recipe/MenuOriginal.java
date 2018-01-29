package com.cm.secret_recipe;
 
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public final class MenuOriginal implements Parcelable{
    // 즐겨찾기 메뉴를 저장하는 ArrayList입니다.
    public static ArrayList<MenuOriginal> STARMENU =  new ArrayList<MenuOriginal>();
    //STARMENU가 adapter에서 바로 등록이 안되 객체 배열로 따로 만들어 저장 했습니다.
    public static MenuOriginal starMenu[] = new MenuOriginal[MenuOriginal.STARMENU.size()];

    public static final String fileName = "star.dat";


    protected MenuOriginal(Parcel in) {
        typeID = in.readInt();
        drawableID = in.readInt();
        brandID = in.readInt();
        menuPrice1 = in.readInt();
        menuPrice2 = in.readInt();
        menuName = in.readString();
        menuCategory = in.readString();
        ingredients = in.createStringArray();
        extras = in.createTypedArray(Extra.CREATOR);
    }

    public static final Creator<MenuOriginal> CREATOR = new Creator<MenuOriginal>() {
        @Override
        public MenuOriginal createFromParcel(Parcel in) {
            return new MenuOriginal(in);
        }

        @Override
        public MenuOriginal[] newArray(int size) {
            return new MenuOriginal[size];
        }
    };

    public static synchronized void save(Context context){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(STARMENU);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static synchronized void load(Context context){
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            STARMENU = (ArrayList<MenuOriginal>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            MenuOriginal.starMenu = MenuOriginal.STARMENU.toArray(new MenuOriginal[MenuOriginal.STARMENU.size()]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public int typeID;            //이 메뉴의 메뉴 타입 ID 입니다
    public int drawableID;        //이 메뉴의 이미지 Drawable ID 입니다
    public int brandID;           //이 메뉴가 속한 브랜드의 번호입니다. 아래 상수들을 참고하세요
    public int menuPrice1;        //이 메뉴의 가격입니다.
    public int menuPrice2;
    public String menuName;       //이 메뉴의 이름입니다. 한글로 통일해주세요
    public String menuCategory;   //이 메뉴의 카테고리 입니다 한글로 통일해주세요
    public String[] ingredients;  //이 메뉴의 재료들을 나열한 것입니다.
    public Extra[] extras;






    public MenuOriginal(int brandID, String menuName, int menuPrice1, int menuPrice2, int drawableID, String menuCategory, int typeID){
        //Constructor는 Private이여서 외부에서 접근 불가능합니다.
        //일단 이 클래스 내에서 직접 매뉴르등록을 해주세요
        this.brandID = brandID;
        this.drawableID = drawableID;
        this.menuName = menuName;
        this.menuPrice1 = menuPrice1;
        this.menuPrice2 = menuPrice2;
        this.typeID = typeID;
        this.menuCategory = menuCategory;

    }

    public MenuOriginal(int brandID, String menuName, int menuPrice1, int menuPrice2, int drawableID, String menuCategory, int typeID, String[] ingredients, Extra... extras){
        //Constructor는 Private이여서 외부에서 접근 불가능합니다.
        //일단 이 클래스 내에서 직접 매뉴르등록을 해주세요
        this.brandID = brandID;
        this.drawableID = drawableID;
        this.menuName = menuName;
        this.menuPrice1 = menuPrice1;
        this.menuPrice2 = menuPrice2;
        this.ingredients = ingredients;
        this.typeID = typeID;
        this.menuCategory = menuCategory;
        this.extras = extras;
    }
    //각각의 브랜드를 구분할 수 있는 ID 상수입니다.
    public static final int BRAND_GONGCHA = 0;
    public static final int BRAND_STARBUCKS = 1;
    public static final int BRAND_TOMNTOMS = 2;
    public static final int BRAND_HOLLYS = 3;



    //여기에서 메뉴를 직접 하나씩 등록해주세요
    //외부에서 Menu.ARRAY_BEVERAGE[] 로 접근해서 사용하면 됩니다.
    //예제는 아래를 참고하세요
    public static final MenuOriginal[] ARRAY_BEVERAGE = {
            new MenuOriginal(BRAND_GONGCHA, "아메리카노", 3500, 0 ,R.drawable.gongcha_coffee_americano,"커피", 0),
            new MenuOriginal(BRAND_GONGCHA, "허니레몬주스", 3900, 0,R.drawable.gongcha_coffee_earlgreyamericano, "커피", 0),
            new MenuOriginal(BRAND_GONGCHA, "카라멜커피", 4200, 0, R.drawable.gongcha_coffee_caramelcoffee, "커피", 0),

            new MenuOriginal(BRAND_GONGCHA, "그린티요구르트", 4300, 5500, R.drawable.gongcha_juice_greenteayoghurt, "주스", 0),
            new MenuOriginal(BRAND_GONGCHA, "레몬요구르트", 4500, 5700, R.drawable.gongcha_juice_lemonyoghurt, "주스", 0),
            new MenuOriginal(BRAND_GONGCHA, "망고요구르트", 5000, 6200, R.drawable.gongcha_juice_mongoyoghurt, "주스", 0),
            new MenuOriginal(BRAND_GONGCHA, "망고주스", 4600, 5800, R.drawable.gongcha_juice_mongojuice, "주스", 0),
            new MenuOriginal(BRAND_GONGCHA, "허니레몬주스", 3900, 4900, R.drawable.gongcha_juice_honeylemonjuice, "주스", 0),
            new MenuOriginal(BRAND_GONGCHA, "허니자몽주스", 4200, 5200, R.drawable.gongcha_juice_honeygrapefruitjuice, "주스", 0),

            new MenuOriginal(BRAND_GONGCHA, "블랙티", 3700, 4800, R.drawable.gongcha_originaltea_blacktea, "오리지날티", 0),
            new MenuOriginal(BRAND_GONGCHA, "얼그레이티", 3700, 4800, R.drawable.gongcha_originaltea_earlgreytea, "오리지날티", 0),
            new MenuOriginal(BRAND_GONGCHA, "우롱티", 3700, 4800, R.drawable.gongcha_originaltea_oolongtea, "오리지날티", 0),
            new MenuOriginal(BRAND_GONGCHA, "자스민그티린", 3700, 4800, R.drawable.gongcha_originaltea_jasminegreentea, "오리지날티", 0),

            new MenuOriginal(BRAND_GONGCHA, "망고스무디", 5300, 0, R.drawable.gongcha_smoothie_mongosmoothie, "스무디", 0),
            new MenuOriginal(BRAND_GONGCHA, "밀크쿠앤크스무디", 4000, 0, R.drawable.gongcha_smoothie_milkcookiecreamsmoothie, "스무디", 0),
            new MenuOriginal(BRAND_GONGCHA, "요구르트스무디-레몬", 4800, 0, R.drawable.gongcha_smoothie_yoghurtsmoothielemon, "스무디", 0),
            new MenuOriginal(BRAND_GONGCHA, "요구르트스무디-피치", 4800, 0, R.drawable.gongcha_smoothie_yoghurtsmoothiepeach, "스무디", 0),
            new MenuOriginal(BRAND_GONGCHA, "초코쿠앤크스무디", 4500, 0, R.drawable.gongcha_smoothie_chococookiecreamsmoothie, "스무디", 0),
            new MenuOriginal(BRAND_GONGCHA, "타로스무디", 4300, 0, R.drawable.gongcha_smoothie_tarosmoothie, "스무디", 0),

            new MenuOriginal(BRAND_GONGCHA, "그린밀크티", 3800, 4900, R.drawable.gongcha_milktea_greenmilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "블랙밀크티", 3800, 4900, R.drawable.gongcha_milktea_blackmilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "블랙밀크티+펄", 3900, 5200, R.drawable.gongcha_milktea_blackmilkteapearl, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "생타로밀크티", 4500, 5700, R.drawable.gongcha_milktea_sangtaromilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "얼그레이밀크티", 3800, 4900, R.drawable.gongcha_milktea_earlgreymilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "우롱밀크티", 3800, 4900, R.drawable.gongcha_milktea_oolongmilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "윈터멜론밀크티", 3800, 4900, R.drawable.gongcha_milktea_wintermelonmilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "초콜릿밀크티", 3900, 5200, R.drawable.gongcha_milktea_chocolatemilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "타로밀크티", 4300, 0, R.drawable.gongcha_milktea_taromilktea, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "타로밀크티+펄", 3900, 5200, R.drawable.gongcha_milktea_taromilkteapearl, "밀크티", 0),
            new MenuOriginal(BRAND_GONGCHA, "허니밀크티", 3900, 5200, R.drawable.gongcha_milktea_honeymilktea, "밀크티", 0),

            new MenuOriginal(BRAND_GONGCHA, "레몬모히또그린티에이드", 4300, 5300, R.drawable.gongcha_greenteaade_lemonmojitogreenteaade, "그린티에이드", 0),
            new MenuOriginal(BRAND_GONGCHA, "베리베리그린티에이드", 4300, 5300, R.drawable.gongcha_greenteaade_berryberrygreenteaade, "그린티에이드", 0),
            new MenuOriginal(BRAND_GONGCHA, "애플그린티에이드", 4300, 5300, R.drawable.gongcha_greenteaade_applegreenteaade, "그린티에이드", 0),
            new MenuOriginal(BRAND_GONGCHA, "자몽그린티에이드", 4300, 5300, R.drawable.gongcha_greenteaade_grapefruitgreenteaade, "그린티에이드", 0),
            new MenuOriginal(BRAND_GONGCHA, "피치그린티에이드", 4300, 5300, R.drawable.gongcha_greenteaade_peachgreenteaade, "그린티에이드", 0),

            new MenuOriginal(BRAND_GONGCHA, "밀크폼그린티", 3900, 4900,R.drawable.gongcha_special_milkfoamgreentea,"공차 스페셜", 0),
            new MenuOriginal(BRAND_GONGCHA, "밀크폼블랙티", 3900, 4900,R.drawable.gongcha_special_milkfoamblacktea,"공차 스페셜", 0),
            new MenuOriginal(BRAND_GONGCHA, "밀크폼얼그레이티", 3900, 4900,R.drawable.gongcha_special_milkfoamearlgreytea,"공차 스페셜", 0),
            new MenuOriginal(BRAND_GONGCHA, "밀크폼우롱티", 3900, 4900,R.drawable.gongcha_special_milkfoamoolongtea,"공차 스페셜", 0),
            new MenuOriginal(BRAND_GONGCHA, "밀크폼윈터멜론티", 3900, 4900,R.drawable.gongcha_special_milkfoamwintermelontea,"공차 스페셜", 0),

            new MenuOriginal(BRAND_HOLLYS, "고구마 라떼", 5500, 6000,R.drawable.hollys_lattetea_sweetpotatolatte,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "그린티 라떼", 5800, 6300,R.drawable.hollys_lattetea_greentealatte,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "루이보스페시카", 4300, 0,R.drawable.hollys_lattetea_rooibospersica,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "리얼 벤지안 초코", 5900, 6400,R.drawable.hollys_lattetea_realbelgianchocolate,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "민트 카페 모카", 5900, 6000,R.drawable.hollys_lattetea_mintchocolate,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "밀크티 라떼", 5800, 6300,R.drawable.hollys_lattetea_milktealatte,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "스크로베리크림", 4300, 0,R.drawable.hollys_lattetea_strawberrycream,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "얼그레이", 4300, 0,R.drawable.hollys_lattetea_earlgrey,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "유기농 녹차", 4300, 0,R.drawable.hollys_lattetea_organicgreentea,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "유자차", 4300, 0,R.drawable.hollys_lattetea_hollyscitrontea,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "제주한라봉꿀차", 4800, 0, R.drawable.hollys_lattetea_jejuhanrabonghoneytea,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "핫초코", 4900, 5400, R.drawable.hollys_lattetea_hotchocolate,"라떼티", 0),
            new MenuOriginal(BRAND_HOLLYS, "화이트 초코", 5200, 5700, R.drawable.hollys_lattetea_whitechocolate,"라떼티", 0),

            new MenuOriginal(BRAND_HOLLYS, "19곡눈꽃팥빙수", 11500, 0, R.drawable.hollys_hollyccinobingsu_redbeanbingsoo,"빙수", 0),
            new MenuOriginal(BRAND_HOLLYS, "눈꽃망고빙수", 11500, 0, R.drawable.hollys_hollyccinobingsu_mangobingsoo,"빙수", 0),
            new MenuOriginal(BRAND_HOLLYS, "눈꽃베리빙수", 11500, 0, R.drawable.hollys_hollyccinobingsu_berrybingsoo,"빙수", 0),
            new MenuOriginal(BRAND_HOLLYS, "올아바웃초코빙수", 11500, 0, R.drawable.hollys_hollyccinobingsu_allaboutchocobingsoo,"빙수", 0),
            new MenuOriginal(BRAND_HOLLYS, "파핑레몬빙수", 11500, 0, R.drawable.hollys_hollyccinobingsu_poppinglemonbingsoo,"빙수", 0),
            new MenuOriginal(BRAND_HOLLYS, "허니치즈빙수", 11500, 0, R.drawable.hollys_hollyccinobingsu_honeycheesebingsoo,"빙수", 0),

            new MenuOriginal(BRAND_HOLLYS, "딸기 스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_strawberrysparkling,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "레드베리아이스티", 4800, 0, R.drawable.hollys_sparklingicetea_veryberryblendtea,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "망고코코스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_mangococosparkling,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "복숭아 자두 스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_peachplumsparkling,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "스퀴즈레몬 스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_squeezelemonsparkling,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "스퀴즈블루레몬 스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_squeezebluelemonsparkling,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "유자블러썸아이스", 5500, 0, R.drawable.hollys_sparklingicetea_citronblossomicetea,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "자몽파인 스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_grapefruitpineapplesparkling,"스파클링티", 0),
            new MenuOriginal(BRAND_HOLLYS, "청포도 스파클링", 5500, 0, R.drawable.hollys_sparklingicetea_greengrapesparkling,"스파클링티", 0),

            new MenuOriginal(BRAND_HOLLYS, "리얼 벨지안 모카", 5900, 6400, R.drawable.hollys_espresso_realbelgianmocha,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "민트 카페 모카", 5900, 6000, R.drawable.hollys_espresso_mintcaffemocca,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "바닐라 딜라이트", 5400, 5600, R.drawable.hollys_espresso_vanilladelight,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "아메리카노", 4100, 4600, R.drawable.hollys_espresso_caffeamericano,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "에스프레소", 3500, 4100, R.drawable.hollys_espresso_espresso,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "에스프레소꼰파냐", 3500, 4100, R.drawable.hollys_espresso_espressoconpanna,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "카라멜 마키아또", 5500, 6000, R.drawable.hollys_espresso_caramelmacchiato,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "카페라떼", 4600, 5100, R.drawable.hollys_espresso_caffelatte,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "카페모카", 5100, 5600, R.drawable.hollys_espresso_caffemocha,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "카푸치노", 4600, 5100, R.drawable.hollys_espresso_cappuccino,"에스프레소", 0),
            new MenuOriginal(BRAND_HOLLYS, "화이트 카페모카", 5100, 5600, R.drawable.hollys_espresso_whitecaffemocha,"에스프레소", 0),

            new MenuOriginal(BRAND_HOLLYS, "그린티 할라치노", 5900, 6400, R.drawable.hollys_hollyccinobingsu_greenteahollyccino,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "다크 포레스트 할리치노", 5900, 6400, R.drawable.hollys_hollyccinobingsu_darkforesthollyccino,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "딸기플라워", 6300, 0, R.drawable.hollys_hollyccinobingsu_strawberryflower,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "딸기플라워초코", 6300, 0, R.drawable.hollys_hollyccinobingsu_strawberryflowerchoco,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "리얼생딸기주스", 6000, 0, R.drawable.hollys_hollyccinobingsu_realstrawberry,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "리얼청포도주스", 6800, 0, R.drawable.hollys_hollyccinobingsu_realgreengrape,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "리얼청포도플라워", 6800, 0, R.drawable.hollys_hollyccinobingsu_realgreengrape,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "스무디 골드키위", 5500, 6000, R.drawable.hollys_hollyccinobingsu_smoothiegoldkiwi,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "스무디 딸기", 5500, 6000, R.drawable.hollys_hollyccinobingsu_smoothiestrawberry,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "아이요떼 블루베리", 5700, 6200, R.drawable.hollys_hollyccinobingsu_iyoteblueberry,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "아이요떼 홍자몽", 5700, 6200, R.drawable.hollys_hollyccinobingsu_iyoteredgrapefruit,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "에스프레소 밀크쉐이크", 5700, 0, R.drawable.hollys_hollyccinobingsu_iyoteredgrapefruit,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "유자크러쉬", 5700, 0, R.drawable.hollys_hollyccinobingsu_citroncrush,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "제주한라봉아이요떼", 5700, 0, R.drawable.hollys_hollyccinobingsu_jejuhanrabongiyote,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "카라멜밀크쉐이크", 5500, 0, R.drawable.hollys_hollyccinobingsu_caramelmilkshake,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "커피할라치노", 5200, 5700, R.drawable.hollys_hollyccinobingsu_coffeehollyccino,"할라치노", 0),
            new MenuOriginal(BRAND_HOLLYS, "허니밀크쉐이크", 5500, 0, R.drawable.hollys_hollyccinobingsu_honeymilkshake,"할라치노", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "라즈베리 이탈리안 소다", 3800, 4300, R.drawable.tomntoms_adesoda_blackberryitaliansoda,"에이드소다", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "블루레몬에이드", 4500, 5000, R.drawable.tomntoms_adesoda_bluelemonade,"에이드소다", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "유자에이드", 4800, 5300, R.drawable.tomntoms_adesoda_citronade,"에이드소다", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "레몬에이드", 4800, 5300, R.drawable.tomntoms_adesoda_lemonade,"에이드소다", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "패션후르츠 이탈리안 소다", 3800, 4300, R.drawable.tomntoms_adesoda_passionfruititaliansoda,"에이드소다", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "유자차", 4500, 5000, R.drawable.tomntoms_adesoda_pomeloade,"에이드소다", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "라즈베리 이탈리안 소다", 3800, 4300, R.drawable.tomntoms_adesoda_rasberryitaliansoda,"에이드소다", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "시나몬핫초콜릿", 4400, 4900, R.drawable.tomntoms_chocolate_cinnamonhotchocolate,"초콜렛", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "그린민트핫초콜릿", 4400, 4900, R.drawable.tomntoms_chocolate_greenminthotchocolate,"초콜렛", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "화이트초콜릿", 4500, 5000, R.drawable.tomntoms_chocolate_whitechocolate,"초콜렛", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "아메리카노", 3800, 4300, R.drawable.tomntoms_coffee_caffeamericano,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "카페라떼", 4200, 4700, R.drawable.tomntoms_coffee_caffelatte,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "카페모카", 4800, 5300, R.drawable.tomntoms_coffee_caffemocha,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "카푸치노", 4200, 4700, R.drawable.tomntoms_coffee_cappuccino,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "카라멜마끼아또", 4800, 5300, R.drawable.tomntoms_coffee_caramelmacchiato,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "시나몬카페모카", 5000, 5500, R.drawable.tomntoms_coffee_cinnamoncaffemocha,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "그린티카페모카", 5000, 5500, R.drawable.tomntoms_coffee_greenmintcaffemocha,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "바닐라라떼", 4600, 5100, R.drawable.tomntoms_coffee_vanillalatte,"커피", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "화이트 모카", 5000, 5500, R.drawable.tomntoms_coffee_whitemocha,"커피", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "에스프레소", 3100, 3600, R.drawable.tomntoms_espresso_espresso,"에스프레소", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "에스프레소마끼아", 3400, 3900, R.drawable.tomntoms_espresso_espressomacchiato,"에스프레소", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "블루베리 주스", 7500, 0, R.drawable.tomntoms_juice_blueberryjuice,"주스", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "코코넛 워터", 4500, 5000, R.drawable.tomntoms_juice_coconutwater,"주스", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "망고아일랜드후레쉬주스", 4800, 5300, R.drawable.tomntoms_juice_mangoislandsfreshjuice,"주스", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "오렌지후레쉬주스", 4800, 5300, R.drawable.tomntoms_juice_orangefreshade,"주스", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "포멜로 주스", 4500, 5000, R.drawable.tomntoms_juice_pomelojuice,"주스", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "복분자 주스", 7500, 0, R.drawable.tomntoms_juice_rubuscoreanus,"주스", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "딸기 주스", 6000, 0, R.drawable.tomntoms_juice_strawberryjuice,"주스", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "카라멜라떼", 4600, 5100, R.drawable.tomntoms_coffee_caramellatte,"라떼", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "두유러브미", 5500, 0, R.drawable.tomntoms_latte_doyouloveme,"라떼", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "그린티라떼", 4500, 5000, R.drawable.tomntoms_latte_greenlatte,"라떼", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "군고구마라떼", 5000, 5500, R.drawable.tomntoms_latte_gungogumalatte,"라떼", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "홍삼라떼", 5000, 5500, R.drawable.tomntoms_latte_hongsamlatte,"라떼", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "땅콩라떼", 5000, 5500, R.drawable.tomntoms_latte_roastedsoylatte,"라떼", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "소이라떼", 5500, 0, R.drawable.tomntoms_latte_soylatte,"라떼", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "이사이 블루베리 프리미엄 스무디", 4800, 5300, R.drawable.tomntoms_smoothie_acaiblueberrypremiumsmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "체리 애플 프리미엄 스무디", 4800, 5300, R.drawable.tomntoms_smoothie_cherryapplepremiumsmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "홍시 스무디", 5500, 0, R.drawable.tomntoms_smoothie_hongsismoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "레몬그린애플프리미엄 스무디", 4800, 5300, R.drawable.tomntoms_smoothie_lemonapplepremiumsmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "망고 스무디", 4100, 4600, R.drawable.tomntoms_smoothie_mangosmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "패션후루츠 이탈리안 스무디", 4700, 5300, R.drawable.tomntoms_smoothie_passionfruititaliansmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "피나콜라다요거트스무디", 4100, 4600, R.drawable.tomntoms_smoothie_pinacoladayogurtsmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "플레인 요거트 스무디", 5300, 5800, R.drawable.tomntoms_smoothie_plainyogurtsmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "라즈블랙베리 프리미엄 스무디", 4800, 5300, R.drawable.tomntoms_smoothie_rasberrypremiumsmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "딸기스무디", 4100, 4600, R.drawable.tomntoms_smoothie_strawberrysmoothie,"스무디", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "타로스무디", 5500, 6000, R.drawable.tomntoms_smoothie_tarosmoothie,"스무디", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "쟈스민티", 4200, 4700, R.drawable.tomntoms_tea_jasminetea,"차", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "레몬티", 3500, 4000, R.drawable.tomntoms_tea_lemontea,"차", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "타로 밀크티", 5000, 5500, R.drawable.tomntoms_tea_taromilktea,"차", 0),

            new MenuOriginal(BRAND_TOMNTOMS, "에스프레소 탐앤치노", 4800, 5300, R.drawable.tomntoms_tomncino_espressotomnccino,"탐엔치노", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "모카 탐앤치노", 4800, 5300, R.drawable.tomntoms_tomncino_mochatomnccino,"탐엔치노", 0),
            new MenuOriginal(BRAND_TOMNTOMS, "바닐라 탐앤치노", 4500, 5000, R.drawable.tomntoms_tomncino_vanilatomnccino,"탐엔치노", 0),

            new MenuOriginal(BRAND_STARBUCKS, "딸기딜라이트블렌디드", 5900, 6400, R.drawable.starbucks_blended_strawberrydelightblended,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "라스베리바나나블렌디드", 0, 6300, R.drawable.starbucks_blended_raspberrybananablended,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "망고바나나블렌디드", 0, 6300, R.drawable.starbucks_blended_mangobananablended,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "망고패션후르츠블렌디드주스", 5000, 5500, R.drawable.starbucks_blended_mangopassionfruitfrappuccinoblendedjuice,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "베리베리요거트블렌디드", 6700, 0, R.drawable.starbucks_blended_veryberryyogurtblended,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아보카도요거트블렌디드", 6700, 0, R.drawable.starbucks_blended_avocadoyogurtblended,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "유자블렌디드주스", 5900, 6400, R.drawable.starbucks_blended_yuzublendedjuice,"블렌디드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "풀문초콜릿바나나블렌디드", 0, 6900, R.drawable.starbucks_blended_fullmoonchocolatebananablended,"블렌디드", 0),

            new MenuOriginal(BRAND_STARBUCKS, "아이스커피", 4100, 4600, R.drawable.starbucks_brewed_icedbrewedcoffee,"부르드", 0),
            new MenuOriginal(BRAND_STARBUCKS, "오늘의커피", 3800, 4300, R.drawable.starbucks_brewed_freshlybrewedcoffee,"부르드", 0),

            new MenuOriginal(BRAND_STARBUCKS, "리스트레토비안코", 5700, 6200, R.drawable.starbucks_espresso_ristrettobianco,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "스타벅스더블샷", 4800, 0, R.drawable.starbucks_espresso_starbucksdoubleshotonice,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "스타벅스돌체라떼", 5900, 6400, R.drawable.starbucks_espresso_starbucksdolcelatte,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스두유카페라떼", 4600, 5100, R.drawable.starbucks_espresso_icedcaffelatte,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스카푸치노", 4600, 5100, R.drawable.starbucks_espresso_icedcappuccino,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스화이트초콜릿모카", 5600, 6100, R.drawable.starbucks_espresso_icedwhitechocolatemocha,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "에스프레소", 3800, 4300, R.drawable.starbucks_espresso_espresso,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "에스프레소마키아또", 3900, 4400, R.drawable.starbucks_espresso_espressomacchiato,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "에스프레소콘파나", 3900, 4400, R.drawable.starbucks_espresso_espressoconpanna,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "카라멜마키아또", 5600, 6100, R.drawable.starbucks_espresso_caramelmacchiato,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "카페라떼", 4600, 5100, R.drawable.starbucks_espresso_caffelatte,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "카페모카", 5100, 5600, R.drawable.starbucks_espresso_caffemocha,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아메리카노", 4100, 4600, R.drawable.starbucks_espresso_caffeamericano,"에스프레소", 0),
            new MenuOriginal(BRAND_STARBUCKS, "화이트초콜릿모카", 5600, 6100, R.drawable.starbucks_espresso_whitechocolatemocha,"에스프레소", 0),

            new MenuOriginal(BRAND_STARBUCKS, "레몬진저망고젤리피지오", 6100, 6600, R.drawable.starbucks_fizzio_lemongingerstarbucksfizziowithmangojelly,"피지오", 0),
            new MenuOriginal(BRAND_STARBUCKS, "믹스베리피지오", 6100, 6600, R.drawable.starbucks_fizzio_mixedberrystarbucksfizzio,"피지오", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스쉐이큰레몬그라스젠티레모네이드피지오", 5400, 5900, R.drawable.starbucks_fizzio_icedshakenzentealemonadestarbucksfizzio,"피지오", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스쉐이큰스위트오렌지블랙티레모네이드피지오", 5400, 5900, R.drawable.starbucks_fizzio_icedshakenblacktealemonadestarbucksfizzio,"피지오", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스쉐이큰패션후르츠티레모네이드피지오", 5400, 5900, R.drawable.starbucks_fizzio_icedshakenpassiontealemonadestarbucksfizzio,"피지오", 0),
            new MenuOriginal(BRAND_STARBUCKS, "요거트부스망고젤리피지오", 6100, 6600, R.drawable.starbucks_fizzio_yogurtjuicestarbucksfizziowithmangojelly,"피지오", 0),

            new MenuOriginal(BRAND_STARBUCKS, "그린티크림프라푸치노", 6300, 6800, R.drawable.starbucks_frappuccino_tazogreenteafrappuccinoblendedcreme,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "다크모카프라푸치노", 6300, 6800, R.drawable.starbucks_frappuccino_darkmochafrappuccinoblendedcoffee,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "딸기크림프라푸치노", 5800, 6300, R.drawable.starbucks_frappuccino_strawberriescremefrappuccinoblendedcreme,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "모카프라푸치노", 6100, 6600, R.drawable.starbucks_frappuccino_mochafrappuccinoblendedcoffee,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "바닐라크림프라푸치노", 4600, 5100, R.drawable.starbucks_frappuccino_vanillafrappuccinoblendedcreme,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "에스프레소프라푸치노", 5400, 5900, R.drawable.starbucks_frappuccino_espressofrappuccinoblendedcoffee,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "자바칩프라푸치노", 6100, 6600, R.drawable.starbucks_frappuccino_javachipfrappuccinoblendedcoffee,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "초콜릿크림칩프라푸치노", 5700, 6200, R.drawable.starbucks_frappuccino_chocolatechipfrappuccinoblendedcreme,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "초콜릿크림프라푸치노", 5100, 5600, R.drawable.starbucks_frappuccino_chocolatefrappuccinoblendedcreme,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "카라멜크림프라푸치노", 5100, 5600, R.drawable.starbucks_frappuccino_caramelfrappuccinoblendedcreme,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "카라멜프라푸치노", 5600, 6100, R.drawable.starbucks_frappuccino_caramelfrappuccinoblendedcoffee,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "커피프라푸치노", 4900, 5400, R.drawable.starbucks_frappuccino_coffeefrappuccinoblendedcoffee,"프라푸치노", 0),
            new MenuOriginal(BRAND_STARBUCKS, "화이트초콜릿모카프라푸치노", 6300, 6800, R.drawable.starbucks_frappuccino_whitechocolatemochafrappuccinoblendedcoffee,"프라푸치노", 0),

            new MenuOriginal(BRAND_STARBUCKS, "라스베리오렌지주스", 0, 6900, R.drawable.starbucks_other_raspberryorangejuice,"기타", 0),
            new MenuOriginal(BRAND_STARBUCKS, "선라이즈애플주스", 5900, 6400, R.drawable.starbucks_other_sunriseapplejuice,"기타", 0),
            new MenuOriginal(BRAND_STARBUCKS, "스팀밀크", 4100, 0, R.drawable.starbucks_other_steamedmilk,"기타", 0),
            new MenuOriginal(BRAND_STARBUCKS, "시그니처핫초콜릿", 5300, 5800, R.drawable.starbucks_other_signaturehotchocolate,"기타", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스시그니처초콜릿", 5300, 5800, R.drawable.starbucks_other_icedsignaturechocolate,"기타", 0),
            new MenuOriginal(BRAND_STARBUCKS, "우유", 4100, 4600, R.drawable.starbucks_other_milk,"기타", 0),

            new MenuOriginal(BRAND_STARBUCKS, "라벤더얼그레이티", 4100, 0, R.drawable.starbucks_tea_tazoearlgreybrewedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "라벤더얼그레이티라떼", 5100, 5600, R.drawable.starbucks_tea_lavenderearlgreytealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "레몬그라스젠티레모네이드", 4800, 5300, R.drawable.starbucks_tea_lemongrasszentealemonade,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "바닐라루이보스티", 4100, 0, R.drawable.starbucks_tea_tazovanillarooibosbrewedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "바닐라루이보스티라떼", 5100, 5600, R.drawable.starbucks_tea_vanillarooibostealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "스위트오렌지블랙티레모네이드", 4800, 5300, R.drawable.starbucks_tea_sweetorangeblacktealemonade,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스그린티라떼", 5900, 6400, R.drawable.starbucks_tea_icedgreentealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스라벤더얼그레이티", 4100, 0, R.drawable.starbucks_tea_earlgreybrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스라벤더얼그레이티라떼", 5100, 5600, R.drawable.starbucks_tea_lavenderearlgreyicedtealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스민트블렌드티", 4100, 0, R.drawable.starbucks_tea_mintblendbrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스바닐라루이보스티", 4100, 0, R.drawable.starbucks_tea_vanillarooibosbrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스바닐라루이보스티라떼", 5100, 5600, R.drawable.starbucks_tea_vanillarooibosicedtealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스쉐이크패션후르츠티", 4800, 5300, R.drawable.starbucks_tea_tazoshakenicedpassiontea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스쉐이큰레몬그라스젠티", 4800, 5300, R.drawable.starbucks_tea_tazoshakenicedlemongrasszentea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스쉐이큰스위트오렌지블랙티", 4800, 5300, R.drawable.starbucks_tea_tazoshakenicedblacktea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스잉글리쉬브렉퍼스트티", 4100, 0, R.drawable.starbucks_tea_englishbreakfastbrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스잉글리쉬브렉퍼스트티라떼", 5100, 5600, R.drawable.starbucks_tea_englishbreakfasticedtealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스제주호지티라떼", 4900, 5400, R.drawable.starbucks_tea_icedjejuhojitealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스차이티", 4100, 0, R.drawable.starbucks_tea_chaibrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스차이티라떼", 5100, 5600, R.drawable.starbucks_tea_icedchaitealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스캐모마일블렌드티", 4100, 0, R.drawable.starbucks_tea_chamomileblendbrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "아이스히비스커스블렌드티", 4100, 0, R.drawable.starbucks_tea_hibiscusblendbrewedicedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "유자아이스쉐이큰티", 5600, 6100, R.drawable.starbucks_tea_yuzuicedshakentea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "잉글리쉬브렉퍼스트티", 4100, 0, R.drawable.starbucks_tea_tazoenglishbreakfastbrewedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "제주유기녹차", 4900, 0, R.drawable.starbucks_tea_jejugreentea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "제주호지티라떼", 5900, 6400, R.drawable.starbucks_tea_jejuhojitealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "차이티", 4100, 0, R.drawable.starbucks_tea_tazochaibrewedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "차이티라떼", 5200, 5700, R.drawable.starbucks_tea_tazochaitealatte,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "캐모마일블렌드티", 4100, 0, R.drawable.starbucks_tea_tazochamomileblendbrewedtea,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "패션후르츠티레모네이드", 4800, 5300, R.drawable.starbucks_tea_passionfruittealemonade,"티", 0),
            new MenuOriginal(BRAND_STARBUCKS, "히비스커스블렌드티", 4100, 0, R.drawable.starbucks_tea_tazohibiscusblendbrewedtea,"티", 0),
    };



    public static final MenuOriginal[] ARRAY_BEVERAGE_SECRET = {
            new MenuOriginal(BRAND_STARBUCKS, "곰과자 프라푸치노", 6600, 7100, R.drawable.starbucks_frappuccino_coffeefrappuccinoblendedcoffee, "시크릿", 1, new String[]{"시나몬돌체시럽 추가", "바닐라 추가" , "꿀 추가"}, new Extra("시나몬돌체시럽",600), new Extra("바닐라 시럽",600), new Extra("꿀",600)  ),
            new MenuOriginal(BRAND_STARBUCKS, "그래스호퍼프라푸치노", 7400, 7900, R.drawable.starbucks_frappuccino_mochafrappuccinoblendedcoffee, "시크릿", 1, new String[]{"자바칩 추가", "페퍼민트 시럽 추가" , "초코드리즐 추가"}, new Extra("자바칩",600), new Extra("페퍼민트 시럽",600), new Extra("초코드리즐",600)  ),
            new MenuOriginal(BRAND_STARBUCKS, "누텔라 프라푸치노", 6000, 6500, R.drawable.starbucks_frappuccino_coffeefrappuccinoblendedcoffee, "시크릿", 1, new String[]{"모카시럽 추가", "헤이즐넛 시럽 추가" , null}, new Extra("모카시럽",600), new Extra("헤이즐넛 시럽",600) , new Extra(null,0)),
            new MenuOriginal(BRAND_STARBUCKS, "돼지바프라푸치노", 7400, 7900, R.drawable.starbucks_frappuccino_strawberriescremefrappuccinoblendedcreme, "시크릿", 1, new String[]{"딸기시럽 추가", "자바칩 추가" , "초코드리즐 추가"}, new Extra("딸기시럽",600), new Extra("자바칩",600) , new Extra("초코드리즐",600)),
            new MenuOriginal(BRAND_STARBUCKS, "레인보우샤베트 프라푸치노", 7400, 7900, R.drawable.starbucks_frappuccino_strawberriescremefrappuccinoblendedcreme, "시크릿", 1, new String[]{"바닐라 파우더 추가", "라즈베리시럽 추가" , "복숭아시럽 추가"}, new Extra("바닐라 파우더",600), new Extra("라즈베리시럽",600) , new Extra("복숭아시럽",600)),
            new MenuOriginal(BRAND_STARBUCKS, "몬스터프라푸치노", 8100, 8600, R.drawable.starbucks_frappuccino_tazogreenteafrappuccinoblendedcreme, "시크릿", 1, new String[]{"자바칩 추가", "카라멜 드리즐 추가" , "복숭아시럽 추가"}, new Extra("자바칩",600), new Extra("카라멜 드리즐",600) , new Extra("쿠키크런블",600)),
            new MenuOriginal(BRAND_STARBUCKS, "민트초코칩프라푸치노", 8100, 8600, R.drawable.starbucks_frappuccino_tazogreenteafrappuccinoblendedcreme, "시크릿", 1, new String[]{"페퍼민트시럽 추가", "모카시럽 추가" , "자바칩 추가"}, new Extra("페퍼민트시럽",600), new Extra("모카시럽",600) , new Extra("자바칩",600)),
            new MenuOriginal(BRAND_STARBUCKS, "바닐라초코아이스크림 프라푸치노", 6000, 6500, R.drawable.starbucks_frappuccino_vanillafrappuccinoblendedcreme, "시크릿", 1, new String[]{null, "모카시럽 추가" , "자바칩 추가"}, new Extra(null,0), new Extra("모카시럽",600) , new Extra("자바칩",600)),
            new MenuOriginal(BRAND_STARBUCKS, "슈렉프라푸치노", 8100, 8600, R.drawable.starbucks_frappuccino_tazogreenteafrappuccinoblendedcreme, "시크릿", 1, new String[]{"에스프레소 1샷 추가", "초코드리즐 추가" , "자바칩 추가"}, new Extra("에스프레소 1샷",600), new Extra("초코드리즐",600) , new Extra("자바칩",600)),
            new MenuOriginal(BRAND_STARBUCKS, "스모어프라푸치노", 7300, 7800, R.drawable.starbucks_frappuccino_javachipfrappuccinoblendedcoffee, "시크릿", 1, new String[]{"시나몬돌체시럽 추가", "토핑넛시럽 추가" , null}, new Extra("시나몬돌체시럽",600), new Extra("토핑넛시럽",600) , new Extra(null,0)),
            new MenuOriginal(BRAND_STARBUCKS, "아몬드로카 프라푸치노", 6600, 7100, R.drawable.starbucks_frappuccino_vanillafrappuccinoblendedcreme, "시크릿", 1, new String[]{"토피넛시럽 추가", "카라멜 시럽 추가" , "모카드리즐 추가"}, new Extra("토피넛시럽",600), new Extra("카라멜 시럽",600) , new Extra("모카드리즐",600)),
            new MenuOriginal(BRAND_STARBUCKS, "윌리웡카 프라푸치노", 6900, 7400, R.drawable.starbucks_frappuccino_chocolatefrappuccinoblendedcreme, "시크릿", 1, new String[]{"모카시럽 추가", "헤이즐넛시럽 추가" , "모카드리즐 추가"}, new Extra("모카시럽",600), new Extra("헤이즐넛시럽",600) , new Extra("모카드리즐",600)),
            new MenuOriginal(BRAND_STARBUCKS, "초코크림프라푸치노", 6300, 6800, R.drawable.starbucks_frappuccino_chocolatefrappuccinoblendedcreme, "시크릿", 1, new String[]{"모카시럽 추가", null , "모카드리즐 추가"}, new Extra("모카시럽",600), new Extra(null,600) , new Extra("모카드리즐",600)),
            new MenuOriginal(BRAND_STARBUCKS, "카라멜마끼아또 프라푸치노", 6600, 7100, R.drawable.starbucks_frappuccino_vanillafrappuccinoblendedcreme, "시크릿", 1, new String[]{"에스프레소 더블샷 추가", null , "카라멜시럽 추가"}, new Extra("에스프레소 더블샷",1200), new Extra(null,600) , new Extra("카라멜시럽",600)),
            new MenuOriginal(BRAND_STARBUCKS, "커피케익프라푸치노", 6600, 7100, R.drawable.starbucks_frappuccino_caramelfrappuccinoblendedcoffee, "시크릿", 1, new String[]{"시나몬돌체시럽 추가", "토피넛시럽 추가" , "바닐라 시럽 추가"}, new Extra("시나몬돌체시럽",600), new Extra("토피넛시럽",600) , new Extra("바닐라 시럽",600)),
            new MenuOriginal(BRAND_STARBUCKS, "킷캣프라푸치노", 6600, 7100, R.drawable.starbucks_frappuccino_vanillafrappuccinoblendedcreme, "시크릿", 1, new String[]{"모카드리즐 추가", "자바칩 추가" , "모카소스 추가"}, new Extra("모카드리즐",600), new Extra("자바칩",600) , new Extra("모카소스",600)),
            new MenuOriginal(BRAND_STARBUCKS, "트윅스푸라푸치노", 7400, 7900, R.drawable.starbucks_frappuccino_caramelfrappuccinoblendedcoffee, "시크릿", 1, new String[]{"헤이즐넛시럽 추가", "자바칩 추가" , "초코드리즐 추가"}, new Extra("헤이즐넛시럽",600), new Extra("자바칩",600) , new Extra("초코드리즐",600)),
            new MenuOriginal(BRAND_STARBUCKS, "페레로로쉐프라푸치노", 6900, 7400, R.drawable.starbucks_frappuccino_chocolatefrappuccinoblendedcreme, "시크릿", 1, new String[]{"헤이즐넛시럽 추가", "더블초콜렛칩 추가" , "모카시럽 추가"}, new Extra("헤이즐넛시럽",600), new Extra("더블초콜렛칩",600) , new Extra("모카시럽",600)),
            new MenuOriginal(BRAND_STARBUCKS, "프렌치바닐라 프라푸치노", 6600, 7100, R.drawable.starbucks_frappuccino_vanillafrappuccinoblendedcreme, "시크릿", 1, new String[]{"토피넛 시럽 추가", "바닐라시럽 추가" , "카라멜시럽 추가"}, new Extra("토피넛 시럽",600), new Extra("바닐라시럽",600) , new Extra("카라멜시럽",600)),

            new MenuOriginal(BRAND_GONGCHA, "그린밀크티+화이트펄", 4400, 5700, R.drawable.gongcha_milktea_greenmilktea, "시크릿", 1, new String[]{null, "화이트펄 추가" , null}, new Extra(null,600), new Extra("화이트펄",700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "레몬그린티+화이트펄", 4600, 5900, R.drawable.gongcha_greenteaade_lemonmojitogreenteaade, "시크릿", 1, new String[]{null, "화이트펄 추가" , null}, new Extra(null,600), new Extra("화이트펄",700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "망고스무디+밀크폼", 5300, 0, R.drawable.gongcha_smoothie_mongosmoothie, "시크릿", 1, new String[]{"밀크폼 추가",null , null}, new Extra("밀크폼",600), new Extra(null,700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "망고스무디+코코넛", 5300, 0, R.drawable.gongcha_smoothie_mongosmoothie, "시크릿", 1, new String[]{"코코넛 추가",null , null}, new Extra("밀크폼",300), new Extra(null,700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "망고스무디+코코넛", 5300, 0, R.drawable.gongcha_smoothie_mongosmoothie, "시크릿", 1, new String[]{"코코넛 추가",null , null}, new Extra("밀크폼",300), new Extra(null,700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "망고요거트", 5600, 6200, R.drawable.gongcha_smoothie_mongosmoothie, "시크릿", 1, new String[]{null,null , null}, new Extra(null,300), new Extra(null,700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "블랙밀크티+화이트펄", 4500, 5800, R.drawable.gongcha_milktea_blackmilktea, "시크릿", 1, new String[]{null,"화이트펄 추가", null}, new Extra(null,300), new Extra("화이트펄",700) , new Extra(null,600)),
            new MenuOriginal(BRAND_GONGCHA, "얼그레이밀크티+펄", 4300, 5400, R.drawable.gongcha_milktea_earlgreymilktea, "시크릿", 1, new String[]{null, null, "펄 추가"}, new Extra(null,300), new Extra(null,700) , new Extra("펄",500)),
            new MenuOriginal(BRAND_GONGCHA, "얼그레이블루베리스무디+코코넛", 4800, 0, R.drawable.gongcha_smoothie_earlgreyblueberrysmoothie, "시크릿", 1, new String[]{null,null, "코코넛 추가"}, new Extra(null,300), new Extra(null,700) , new Extra("코코넛",500)),
            new MenuOriginal(BRAND_GONGCHA, "오레오초콜릿스무디", 4800, 0, R.drawable.gongcha_smoothie_chococookiecreamsmoothie, "시크릿", 1, new String[]{null,null, null}, new Extra(null,300), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "유기농그린티스무디", 5300, 0, R.drawable.gongcha_smoothie_greenteasmoothie, "시크릿", 1, new String[]{null,null, null}, new Extra(null,300), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "요거트그린티", 4300, 5500, R.drawable.gongcha_juice_greenteayoghurt, "시크릿", 1, new String[]{null,null, null}, new Extra(null,300), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "패션후르츠그린티", 3900, 4900, R.drawable.gongcha_juice_honeygrapefruitjuice, "시크릿", 1, new String[]{null,null, null}, new Extra(null,300), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "하우스스페셜그린티", 3900, 4900, R.drawable.gongcha_special_milkfoamgreentea, "시크릿", 1, new String[]{null,null, null}, new Extra(null,300), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "우롱밀크티+코코넛", 4300, 5400, R.drawable.gongcha_milktea_oolongmilktea, "시크릿", 1, new String[]{null, null, "코코넛 추가"}, new Extra(null,300), new Extra(null,700) , new Extra("코코넛",500)),
            new MenuOriginal(BRAND_GONGCHA, "자몽그린티에이드+알로에", 4900, 5900, R.drawable.gongcha_greenteaade_grapefruitgreenteaade, "시크릿", 1, new String[]{"알로에 추가", null, null}, new Extra("알로에",600), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "청포도스무디+알로에", 4800, 0, R.drawable.gongcha_smoothie_whitegrapesmoothie, "시크릿", 1, new String[]{"알로에 추가",null, null}, new Extra("알로에",600), new Extra(null,700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "초콜릿밀크티+펄", 4400, 5700, R.drawable.gongcha_milktea_chocolatemilktea, "시크릿", 1, new String[]{null, null, "펄 추가"}, new Extra(null, 600), new Extra(null,700) , new Extra("펄",500)),
            new MenuOriginal(BRAND_GONGCHA, "카라멜밀크티+펄", 4400, 5700, R.drawable.gongcha_milktea_caramelmilktea, "시크릿", 1, new String[]{null, null, "펄 추가"}, new Extra(null, 600), new Extra(null,700) , new Extra("펄",500)),
            new MenuOriginal(BRAND_GONGCHA, "카라멜밀크티+화이트펄", 4600, 5900, R.drawable.gongcha_milktea_caramelmilktea, "시크릿", 1, new String[]{null,"화이트펄 추가", null}, new Extra(null, 600), new Extra("화이트펄",700) , new Extra(null,500)),
            new MenuOriginal(BRAND_GONGCHA, "허니레몬주스+화이트펄", 4600, 5600, R.drawable.gongcha_juice_honeylemonjuice, "시크릿", 1, new String[]{null,"화이트펄 추가", null}, new Extra(null, 600), new Extra("화이트펄",700) , new Extra(null,500)),

            new MenuOriginal(BRAND_HOLLYS, "에스프레소+초코시럽", 4000, 4600, R.drawable.hollys_espresso_espresso, "시크릿", 1, new String[]{null,"초코시럽 추가", null}, new Extra(null, 500), new Extra("초코시럽",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "그린티 할라치뇨 + 샷", 6400, 6900, R.drawable.hollys_hollyccinobingsu_darkforesthollyccino, "시크릿", 1, new String[]{"샷 추가", null, null}, new Extra("샷", 500), new Extra(null,500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "허니밀크 쉐이크 + 샷", 6000, 0, R.drawable.hollys_hollyccinobingsu_honeymilkshake, "시크릿", 1, new String[]{null,"샷 추가", null}, new Extra(null, 500), new Extra("샷",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "아메리카노+헤이즐", 4600, 5100, R.drawable.hollys_espresso_caffeamericano, "시크릿", 1, new String[]{null,"헤이즐 추가", null}, new Extra(null, 500), new Extra("헤이즐",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "민트카페모카+초코시럽", 6400, 6900, R.drawable.hollys_espresso_mintcaffemocca, "시크릿", 1, new String[]{null,"초코시럽 추가", null}, new Extra(null, 500), new Extra("초코시럽",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "커피할라치노+초코시럽", 5700, 6200, R.drawable.hollys_hollyccinobingsu_coffeehollyccino, "시크릿", 1, new String[]{null,"초코시럽 추가", null}, new Extra(null, 500), new Extra("초코시럽",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "아메리카노+휘핑", 4000, 4600, R.drawable.hollys_espresso_espresso, "시크릿", 1, new String[]{null, null,"휘핑 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("휘핑",500)),
            new MenuOriginal(BRAND_HOLLYS, "다크포레스트 할라치뇨 + 샷", 6400, 6900, R.drawable.hollys_hollyccinobingsu_darkforesthollyccino, "시크릿", 1, new String[]{null,"샷 추가", null}, new Extra(null, 500), new Extra("샷",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "카라멜 밀크 쉐이크 + 샷", 6000, 0, R.drawable.hollys_hollyccinobingsu_caramelmilkshake, "시크릿", 1, new String[]{null,"샷 추가", null}, new Extra(null, 500), new Extra("샷",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "에스프레소밀크 쉐이크 + 휘핑", 6200, 0, R.drawable.hollys_hollyccinobingsu_espressomilkshake, "시크릿", 1, new String[]{null,"휘핑 추가", null}, new Extra(null, 500), new Extra("휘핑",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_HOLLYS, "스무디망고+휘핑", 6000, 6500, R.drawable.hollys_hollyccinobingsu_smoothiemango, "시크릿", 1, new String[]{null,"휘핑 추가", null}, new Extra(null, 500), new Extra("휘핑",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_HOLLYS, "카페라떼 + 휘핑", 5100, 5600, R.drawable.hollys_espresso_caffelatte, "시크릿", 1, new String[]{null,"휘핑 추가", null}, new Extra(null, 500), new Extra("휘핑",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_HOLLYS, "그린티 라떼 + 샷", 6400, 6900, R.drawable.hollys_hollyccinobingsu_greenteahollyccino, "시크릿", 1, new String[]{null,"샷 추가", null}, new Extra(null,500), new Extra("샷", 500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "카푸치노+초코시럽", 5100, 5600, R.drawable.hollys_espresso_cappuccino, "시크릿", 1, new String[]{null,"초코시럽 추가", null}, new Extra(null, 500), new Extra("초코시럽",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "바닐라딜라이트+휘핑", 5600, 6100, R.drawable.hollys_espresso_vanilladelight, "시크릿", 1, new String[]{null,"휘핑 추가", null}, new Extra(null, 500), new Extra("휘핑",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_HOLLYS, "밀크티할라키노+초코시럽", 6400, 6900, R.drawable.hollys_hollyccinobingsu_milkteahollyccino, "시크릿", 1, new String[]{null,"초코시럽 추가", null}, new Extra(null, 500), new Extra("초코시럽",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "화이트 카페모카+초코시럽", 5600, 6100, R.drawable.hollys_espresso_whitecaffemocha, "시크릿", 1, new String[]{null,"초코시럽 추가", null}, new Extra(null, 500), new Extra("초코시럽",500) , new Extra(null,500)),
            new MenuOriginal(BRAND_HOLLYS, "유자 크러쉬 + 휘핑", 6200, 0, R.drawable.hollys_hollyccinobingsu_citroncrush, "시크릿", 1, new String[]{null,"휘핑 추가", null}, new Extra(null, 500) , new Extra("휘핑",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_HOLLYS, "민트 초코 라떼 + 휘핑", 6400, 6900, R.drawable.hollys_lattetea_mintchocolate, "시크릿", 1, new String[]{null,"휘핑 추가", null}, new Extra(null, 500), new Extra("휘핑",500), new Extra(null,500) ),

            new MenuOriginal(BRAND_TOMNTOMS, "소이라떼 + 버블", 6000, 0, R.drawable.tomntoms_latte_soylatte, "시크릿", 1, new String[]{null, null, "버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "로스트소이라떼 + 버블", 5500, 6000, R.drawable.tomntoms_latte_roastedsoylatte, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "그린라떼 +버블", 5000, 5500, R.drawable.tomntoms_latte_greenlatte, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra("버블",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_TOMNTOMS, "딸기스무디 + 버블", 4600, 5100, R.drawable.tomntoms_smoothie_strawberrysmoothie, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "망고스무드 + 버블", 4600, 5100, R.drawable.tomntoms_smoothie_mangosmoothie, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "군고구마라떼 + 버블", 5500, 6000, R.drawable.tomntoms_latte_gungogumalatte, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "모카탐엔치노 + 버블", 5300, 5800, R.drawable.tomntoms_tomncino_mochatomnccino, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "에스프레소 + 헤이즐넛시럽", 3600, 4100, R.drawable.tomntoms_espresso_espresso, "시크릿", 1, new String[]{"헤이즐넛시럽 추가",null, null}, new Extra("헤이즐넛시럽", 500), new Extra(null,500) , new Extra(null,500)),
            new MenuOriginal(BRAND_TOMNTOMS, "바닐라라떼 + 버블", 5100, 5600, R.drawable.tomntoms_coffee_vanillalatte, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "바닐라탐엔치노 +버블", 5000, 5500, R.drawable.tomntoms_tomncino_vanilatomnccino, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "아메리카노 + 헤이즐넛시럽", 5000, 5500, R.drawable.tomntoms_latte_greenlatte, "시크릿", 1, new String[]{"헤이즐넛시럽 추가",null, null}, new Extra("헤이즐넛시럽", 500), new Extra(null,500) , new Extra(null,500)),
            new MenuOriginal(BRAND_TOMNTOMS, "레모네이드 + 버블", 5300, 5800, R.drawable.tomntoms_adesoda_lemonade, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "블랙베리이탈리안소다 + 버블", 4300, 4800, R.drawable.tomntoms_adesoda_blackberryitaliansoda, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "블루레모네이드 + 버블", 5000, 5500, R.drawable.tomntoms_adesoda_bluelemonade, "시크릿", 1, new String[]{null, null,"버블 추가"}, new Extra(null, 500), new Extra(null,500) , new Extra("버블",500)),
            new MenuOriginal(BRAND_TOMNTOMS, "차이라떼 +버블", 5000, 5500, R.drawable.tomntoms_latte_chailatte, "시크릿", 1, new String[]{null,"버블 추가", null}, new Extra(null, 500), new Extra("버블",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_TOMNTOMS, "카라멜라떼 + 버블", 5100, 5600, R.drawable.tomntoms_coffee_caramellatte, "시크릿", 1, new String[]{null,"버블 추가", null}, new Extra(null, 500), new Extra("버블",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_TOMNTOMS, "타로스무디 + 버블", 6000, 6500, R.drawable.tomntoms_smoothie_tarosmoothie, "시크릿", 1, new String[]{null,"버블 추가", null}, new Extra(null, 500), new Extra("버블",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_TOMNTOMS, "플레인요거트스무디 + 버블", 5800, 6300, R.drawable.tomntoms_smoothie_plainyogurtsmoothie, "시크릿", 1, new String[]{null,"버블 추가", null}, new Extra(null, 500), new Extra("버블",500), new Extra(null,500) ),
            new MenuOriginal(BRAND_TOMNTOMS, "화이트모카 +버블", 5000, 5500, R.drawable.tomntoms_coffee_whitemocha, "시크릿", 1, new String[]{null,"버블 추가", null}, new Extra(null, 500), new Extra("버블",500), new Extra(null,500) ),
    };
    // 대상 배열을 생략할 경우 전체 메뉴에서 검색합니다.
    public static MenuOriginal[] SearchWithBrand(int brandID){
        return SearchWithBrand(brandID, ARRAY_BEVERAGE);
    }
    // 대상 배열 Menu[] target내에서 Brand ID가 일치한 매뉴를 검색합니다.
    // 검색 결과 발견된 메뉴들을 배열로 반환합니다.
    public static MenuOriginal[] SearchWithBrand(int brandID, MenuOriginal[] target){
        ArrayList<MenuOriginal> beverages = new ArrayList<MenuOriginal>();
        for(MenuOriginal beverage : target){
            if(beverage.brandID == brandID)
                beverages.add(beverage);
        }
        return (MenuOriginal[]) beverages.toArray();
    }


    // 대상 배열을 생략할 경우 전체 메뉴에서 검색합니다.
    public static MenuOriginal[] SearchWithName(String menuName){
        return SearchWithName(menuName, ARRAY_BEVERAGE, ARRAY_BEVERAGE_SECRET);
    }
    // 대상 배열 Menu[] target에서 검색어가 이름에 포함된 메뉴를 검색합니다.
    // 검색 결과 발견된 메뉴들을 배열로 반환합니다.
    public static MenuOriginal[] SearchWithName(String menuName, MenuOriginal[] target1, MenuOriginal[] target2){
        ArrayList<MenuOriginal> beverages = new ArrayList<MenuOriginal>();
        for(MenuOriginal beverage : target1){
            if(beverage.menuName.contains(menuName))
                beverages.add(beverage);
        }
        for(MenuOriginal beverage : target2){
            if(beverage.menuName.contains(menuName))
                beverages.add(beverage);
        }
        return beverages.toArray(new MenuOriginal[beverages.size()]);
    }
        public static boolean SearchStar(String menuName, ArrayList<MenuOriginal> target1){
                for(MenuOriginal beverage : target1){
                    if(beverage.menuName.contains(menuName)){
                        return true;
                    }
                }
                return false;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(typeID);
        parcel.writeInt(drawableID);
        parcel.writeInt(brandID);
        parcel.writeInt(menuPrice1);
        parcel.writeInt(menuPrice2);
        parcel.writeString(menuName);
        parcel.writeString(menuCategory);
        parcel.writeStringArray(ingredients);
        parcel.writeTypedArray(extras, i);
    }

    //추가 메뉴에 대한 정보
    public static class Extra implements Parcelable{
        public int price;
        public String name;
        Extra(String name, int price){
            this.name = name;
            this.price = price;
        }

        protected Extra(Parcel in) {
            price = in.readInt();
            name = in.readString();
        }

        public static final Creator<Extra> CREATOR = new Creator<Extra>() {
            @Override
            public Extra createFromParcel(Parcel in) {
                return new Extra(in);
            }

            @Override
            public Extra[] newArray(int size) {
                return new Extra[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(price);
            parcel.writeString(name);
        }
    }
}