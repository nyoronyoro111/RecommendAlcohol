package com.example.recommendalcohol;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class recommendPub extends AppCompatActivity {
    Spinner spinner;
    String spinnerList[] ={
            "ラムコーク","モスコミュール","チャイナブルー","マリブジンジャー","スクリュードライバー",
            "ピニャコラーダ","カルーアミルク","カシスオレンジ","ファジーネーブル","パッソアオレンジ","アペロールオレンジ",
            "シャンパン","ジントニック","シャンディガフ","ビール",
            "ソルティドッグ","ブラッディメアリー","ワイン","日本酒","紹興酒","焼酎"
    };


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.recommend_pub );

        spinner = findViewById( R.id.spinner );

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView <?> adapterView, View view, int i, long l ) {
                Spinner spinner = (Spinner)adapterView;
                String item = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected( AdapterView <?> adapterView ) {//何も選択されていないときは何もしない
            }
        });


    }

}
