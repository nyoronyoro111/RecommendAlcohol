package com.example.recommendalcohol;

import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class scrapingpub_gurunabi extends AsyncTask {
    String location=null,kods=null;


    public scrapingpub_gurunabi(String l,String k) {
        this.location =l;
        this.kods=k;
    }
    public String makeURL() {
        String url="https://r.gnavi.co.jp/area/"+location+"/izakaya/"+kods+"/rs/";
        return url;
    }


    @Override
    protected Object doInBackground( Object[] objects ) {

        try {
            Document doc = Jsoup.connect( makeURL() ).maxBodySize( 0 ).timeout( 0 ).postDataCharset( "utf-8" ).ignoreContentType( true ).get();
            Elements elements =doc.select( "div.shopCassette__main" ).select( "div.flex" ).select( "div.flex__item" );
            Elements storeData = new Elements();
            Element el;
            Iterator<Element> ite= elements.iterator();
            while(ite.hasNext()){
                el=ite.next();
                if(! el.children().first().children().first().hasClass( "label-pr" ))storeData.add( el );
            }

            String pubName,pubLocation,pubBudget;
            Iterator<Element> ite2 = null;
            int counter=0;
            for(Element elm:storeData){
                ite2=elm.select( "li" ).iterator();
                pubName=elm.children().first().text();
                ite2.next();
                pubLocation=ite2.next().text();
                pubBudget=ite2.next().text();
                Log.v("テスト", "これはメッセージです「" + pubName+","+pubLocation+","+pubBudget + "」終わり");
                recommendPub.publist[counter]=new pub(pubName,pubBudget,pubLocation);
                Log.v("テスト", "これはメッセージです「publist["+counter+"].name="+recommendPub.publist[counter].getName()+"」終わり");
                counter++;
                recommendPub.latch.countDown();
                if(counter>2)break;
            }
            Log.v("テスト", "これはメッセージです「" +"for文を抜けました"+ "」終わり");
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


class pub {
    public String name;
    public String buget;
    public String locaton;
    public pub(String n,String b,String l) {
        this.name=n;
        this.buget=b;
        this.locaton=l;
    }
    public String getName(){
        return this.name;
    }
    public String getLocaton(){
        return this.locaton;
    }
    public String getBuget(){
        return this.buget;
    }
}