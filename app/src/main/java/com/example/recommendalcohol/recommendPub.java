package com.example.recommendalcohol;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import androidx.appcompat.app.AppCompatActivity;

public class recommendPub extends AppCompatActivity {
    public static pub[] publist=new pub[3];
    static CountDownLatch latch;
    Spinner spinner1,spinner2;
    TextView res1,res2,res3,errorlog;
    String location="品川・五反田・大崎",kods="日本酒";
    static scrapingpub_gurunabi scraping;

    final HashMap<String,String> locationList=new HashMap<String,String>(){
        {
            put("飯田橋・四ツ谷・神楽坂","areal2184");
            put("池袋・巣鴨・駒込","areal2156");
            put("品川・五反田・大崎","areal2169");
            put("下北沢・明大前・成城学園前","areal2207");
            put("銀座・有楽町・築地","areal2101");
            put("新宿・代々木","areal2115");
            put("東京駅・丸の内・日本橋","areal2141");
            put("中野・吉祥寺・三鷹","areal2217");
            put("新橋・浜松町・田町","areal2107");
            put("大久保・高田馬場","areal2116");
            put("上野・浅草・日暮里","areal2198");
            put("恵比寿・中目黒・目黒","areal2178");
            put("小金井・国分寺・国立","areal2278");
            put("赤坂・六本木・麻布","areal2133");
            put("渋谷・原宿・青山","areal2125");
            put("神田・秋葉原・水道橋","areal2142");
            put("大井町・大森・蒲田","areal2254");
            put("立川・八王子・青梅","areal2286");
        }
    };
    final HashMap<String,String> kodsList = new HashMap<String,String>(){
        {
            put("日本酒","kods00017");
            put("焼酎","kods00020");
            put("ウイスキー","kods00024");
            put("チュウハイ","kods00022");
            put("ワイン","kods00018");
            put("カクテル","kods00023");
            put("紹興酒","kods00029");
            put("ビール","kods00019");
            put("ハイボール","kods00021");
            put("リキュール","kods00027");
        }
    };


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.recommend_pub );


        //spinner1にプルダウンを設定する＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
        spinner1 = findViewById( R.id.spinner1 );
        final ArrayAdapter <String> adapter1 = new ArrayAdapter <>(
                this,
                android.R.layout.simple_spinner_item,
                new ArrayList <String>( locationList.keySet() )
        );
        adapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner1.setAdapter( adapter1 );
        spinner1.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView <?> adapterView, View view, int i, long l ) {
                Spinner spinner = (Spinner) adapterView;
                location = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected( AdapterView <?> adapterView ) {//何も選択されていないときは何もしない
            }
        } );




        //spinner2にプルダウンを設定する＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
        spinner2 = findViewById( R.id.spinner2 );
        final ArrayAdapter <String> adapter2 = new ArrayAdapter <>(
                this,
                android.R.layout.simple_spinner_item,
                new ArrayList <String>( kodsList.keySet() )
        );
        adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner2.setAdapter( adapter2 );
        spinner2.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView <?> adapterView, View view, int i, long l ) {
                Spinner spinner = (Spinner) adapterView;
                kods = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected( AdapterView <?> adapterView ) {//何も選択されていないときは何もしない
            }
        } );




        //検索ボタンを設定する＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊

        findViewById( R.id.search ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if (locationList.containsKey( location ) && kodsList.containsKey( kods )) {
                    scraping = new scrapingpub_gurunabi( locationList.get( location ), kodsList.get( kods ) );
                    Log.v("テスト", "これはメッセージです「スレッドを開始しました」終わり");
                    latch = new CountDownLatch(3);
                    scraping.execute();
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (publist.length <= 0) {
                            Log.v("テスト", "これはメッセージです「publistの長さが0」終わり");
                            errorlog = findViewById( R.id.errorlog );
                            errorlog.setText( "検索結果なし" );
                        } else {
                            setContentView( R.layout.show_search_result );
                            res1 = findViewById( R.id.res1 );
                            res2 = findViewById( R.id.res2 );
                            res3 = findViewById( R.id.res3 );
                            Log.v("テスト", "これはメッセージです「publistの長さが1以上」終わり");
                            if (publist.length > 0) res1.setText( pubinfo( 0 ) );
                            if (publist.length > 1) res2.setText( pubinfo( 1 ) );
                            if (publist.length > 2) res3.setText( pubinfo( 2 ) );
                        }
                }
            }
        } );
    }
    public String pubinfo(int index) {
        return publist[index].getName()+"\n"+publist[index].getLocaton()+"\n"+publist[index].getBuget();
    }
}
