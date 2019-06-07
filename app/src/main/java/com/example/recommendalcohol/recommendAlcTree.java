package com.example.recommendalcohol;


public class recommendAlcTree {
    public node rNode;
    private String
            q1="甘いものがいいですか？",
            q2="炭酸がいいですか？",
            q3="度数は強い方がいいですか？",
            q4="果実系かミルク系なら、果実系でしょうか？",
            q5="洋酒か他なら、洋酒でしょうか？";


    private String[]
            SweetSoda_Frequent={"ラムコーク","モスコミュール"},
            SweetSoda_LowFrequent={"チャイナブルー","マリブジンジャー"},
            Sweet_Frequent={"スクリュードライバー"},
            Sweet_LowFrequent_Milk={"ピニャコラーダ","カルーアミルク"},
            Sweet_LowFrequent_Fruit={"カシスオレンジ","ファジーネーブル","パッソアオレンジ","アペロールオレンジ"};
    private String[]
            DrySoda_Frequent={"シャンパン"},
            DrySoda_LowFrequent={"ジントニック","シャンディガフ","ビール"},
            Dry_FrequentLiquor={"ソルティドッグ","ブラッディメアリー","ワイン"},
            Dry_FrequentOther={"ワイン","日本酒","紹興酒","焼酎"},
            Dry_LowFrequent={"該当データなし"};

    public recommendAlcTree(){
        if(this.rNode==null){
            node sweetSoda = new node(q3,new node(SweetSoda_Frequent),new node(SweetSoda_LowFrequent));
            node sweetNonSoda = new node(
                    q3,
                    new node(Sweet_Frequent),
                    new node(q4,new node(Sweet_LowFrequent_Fruit),new node(Sweet_LowFrequent_Milk)));

            node drySoda = new node(q3,new node(DrySoda_Frequent),new node(DrySoda_LowFrequent));
            node dryNonSoda = new node(
                    q3,
                    new node(q5,new node(Dry_FrequentLiquor),new node(Dry_FrequentOther)) ,
                    new node(Dry_LowFrequent) );

            this.rNode = new node(q1,new node(q2,sweetSoda,sweetNonSoda),new node(q2,drySoda,dryNonSoda));
        }
    }
    public void setrNodeY(){
        this.rNode=this.rNode.getNodeY();
    }
    public void setrNodeN(){
        this.rNode=this.rNode.getNodeN();
    }
    public boolean hasNext(){
        if(rNode.getQuestion()==null)return false;
        if(rNode.getData()!=null)return false;
        return true;
    }

}
class node{
    private String data[]=null;
    private node yes=null,no=null;
    private String question;

    public node(String q,node y,node n){
        this.question=q;
        this.yes=y;
        this.no=n;
    }
    public node(String[] data){
        this.data=data;
    }

    public node getNodeY(){
        return this.yes;
    }
    public node getNodeN(){
        return this.no;
    }
    public String getQuestion(){
        return this.question;
    }
    public String[] getData(){
        return  this.data;
    }

}