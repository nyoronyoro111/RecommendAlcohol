package com.example.recommendalcohol;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class recommendAlc extends AppCompatActivity{
    recommendAlcTree tree;
    TextView text,title;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.recommend_alcohol );

        tree = new recommendAlcTree();
        text = findViewById( R.id.text );
        text.setText(tree.rNode.getQuestion());//textフィールドに最初の問題文をセットする
        title = findViewById( R.id.title );

        findViewById(R.id.yes).setOnClickListener(Onclick_choice);
        findViewById(R.id.no).setOnClickListener(Onclick_choice);
    }

    View.OnClickListener Onclick_choice = new View.OnClickListener() {
        @Override
        public void onClick( View view ) {
            if (view.getId()==R.id.yes){
                tree.setrNodeY();
            }
            else if (view.getId()==R.id.no){
                tree.setrNodeN();
            }
            if(tree.hasNext()){
                text.setText(tree.rNode.getQuestion());
            }else{
                showResult();
            }
        }
    };

    private void showResult(){
        title.setText( "Recommends are ..." );
        text.setText( subStr( tree.rNode.getData() ) );
        findViewById( R.id.choice ).setVisibility( View.GONE );
    }
    private String subStr(String[] str){
        String res=str[0];
        for(int i =1;i<str.length;i++)res=res+","+str[i];
        return res;
    }


}
