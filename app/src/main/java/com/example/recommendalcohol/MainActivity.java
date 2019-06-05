package com.example.recommendalcohol;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        findViewById(R.id.recommend_alc).setOnClickListener(Onclick_sendRecAlc);
        findViewById(R.id.recommend_pub).setOnClickListener(Onclick_sendRecPub);

    }


    View.OnClickListener Onclick_sendRecAlc = new View.OnClickListener() {
        @Override
        public void onClick( View view ) {
            Intent intent = new Intent(MainActivity.this,com.example.recommendalcohol.recommendAlc.class);
            startActivity(intent);
        }
    };
    View.OnClickListener Onclick_sendRecPub = new View.OnClickListener(){
        @Override
        public void onClick( View view ) {
            Intent intent = new Intent(MainActivity.this,com.example.recommendalcohol.recommendPub.class);
            startActivity(intent);
        }
    };
}
