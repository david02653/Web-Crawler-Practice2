import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KeelungSightCrawler {
    public KeelungSightCrawler(){}
    public KeelungSightCrawler(String link){
        this.url = "http://travel.network.com.tw/tourguide/twnmap/keelungcity/";
        getItem("http://travel.network.com.tw/tourguide/twnmap/keelungcity/");
    }

    private ArrayList<Sight> sightList = new ArrayList<>();
    private Sight sight;
    private String url = "";

    // return the final result in Sight[] type
    public Sight[] getItem( String link ){
        url = link;

        int length = 0;
        String innerLink = "";
        Sight[] result = new Sight[1];
        result[0] = new Sight();

        try{
            Document doc = Jsoup.connect(url).timeout(80000)
                                .userAgent("\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                                .get();
            Elements list = doc.getElementsByClass("tourgudes").select("li");
            Elements urlList = doc.getElementsByClass("tourgudes").select("a[href]");
            //System.out.println(urlList.size());
            //System.out.println(urlList.attr("abs:href"));
            //for(int i=0; i<urlList.size(); i++){
                //System.out.println(urlList.get(i).attr("abs:href"));
            //}

            length = list.size();
            //System.out.println(list.size());
            for(int i=0; i<length; i++){
                sight = new Sight();
                sight.setSightName(list.get(i).text());
                //sight.setUrl(urlList.get(i).attr("abs:href"));

                innerLink = urlList.get(i).attr("abs:href");
                //System.out.print(i + list.get(i).text() + " ");
                //System.out.println(urlList.get(i).attr("abs:href"));
                /*try{
                    System.out.println(innerLink.length());
                }catch(Exception e){
                    System.out.println("CAN'T DO");
                }*/

                if(innerLink.length() > 0){
                    sight.setUrl(urlList.get(i).attr("abs:href"));
                    addDetail(urlList.get(i).attr("abs:href"), sight);
                }
                //System.out.println(i + ": " + sight.getSightName());
                //System.out.println(sight);

                sightList.add(sight);
                innerLink = "";
            }

            result = new Sight[sightList.size()];
            result = sightList.toArray(result);

        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println("error");
        }

        return result;
    }

    public void addDetail( String link, Sight temp ){

        //System.out.println("start tracing");

        try{
            Document web = Jsoup.connect(link).timeout(800000)
                                .userAgent("\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                                .get();
            //System.out.println(web.title());

            /*Elements name = web.select("div.type > strong > span");
            //System.out.print("This site is = ");
            //System.out.println(name.text());
            temp.setSightName(name.text());*/

            //Elements info = web.getElementsByTag("a").select("strong").select("span");
            Element info = web.select("ol > li.bc_last > a").first();
            //System.out.println("Zone: " + info.text());

            temp.setZone(info.text());

            //Elements typ = web.getElementsByClass("type").select("em").select("strong");
            Element typ = web.select("span[property=\"rdfs:label\"]").first();
            //System.out.println("Category: " + typ.text());

            temp.setCategory(typ.text());

            Elements img;
            if(web.getElementsByClass("swiper-lazy") == null){
                temp.setPhotoURL("none");
            }
            else{
                img = web.getElementsByClass("swiper-lazy");
                String imgUrl = img.attr("data-src");

                temp.setPhotoURL(imgUrl);
            }

            //System.out.println("PhotoUrl: " + imgUrl.attr("abs:src"));

            /*if(sight.getPhotoURL() == null){
                sight.setPhotoURL("none");
            }
            else{
                sight.setPhotoURL(imgUrl.attr("abs:src"));
            }*/

            //Element descrip = web.select("div.text").first();
            //Elements descripCont = descrip.select("font");
            //System.out.println("Description: " + descripCont.text());
            //Element descripCont = web.select("div[property=\"dc:description\"]").first();
            Element descripCont = web.select("meta[itemprop=\"description\"]").first();
            String descrip = descripCont.attr("content");

            try{
                if(descrip == null || descrip.isEmpty()){
                    Element anotherDescriptCont = web.select("div.point_list > p").first();
                    descrip = anotherDescriptCont.text();
                }
            }catch(Exception e){
                System.out.println("error getting description");
            }

            temp.setDescription(descrip);
            //temp.setDescription(descripCont.text());

            Element address = web.select("div.address").first();
            Elements addressCont = address.select("span");
            //System.out.println("Address: " + addressCont.text());

            temp.setAddress(addressCont.text());

            Element googleMapLink = web.select("div.address > p > a").first();
            String gUrl = googleMapLink.attr("href");
            temp.setGoogleMapUrl(gUrl);


        }
        catch( Exception e ){
            System.out.println( e.getMessage() );
            System.out.println("error - of - detail");
        }
    }

    public ArrayList<Sight> result(){
        return sightList;
    }

}
