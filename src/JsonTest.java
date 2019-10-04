import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name="JsonTest", urlPatterns={""}, description = "myServlet", displayName = "myServlet", loadOnStartup = 1)
@WebServlet("/test")
public class JsonTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set content data type
        resp.setContentType("application/json;charset=UTF-8");
        // allow Cross-Origin Resource Sharing by add new header
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, HEAD");

        // servlet test 19-03-09
        /*
        KeelungSightCrawler test = new KeelungSightCrawler("http://travel.network.com.tw/tourguide/twnmap/keelungcity/");
        ArrayList<Sight> sights = test.result();

        //System.out.println(sights.get(0));
        Gson gson = new Gson();
        String sightData = gson.toJson(sights);

        PrintWriter out = resp.getWriter();
        out.println(sightData);
        out.flush();
        out.close();
        */
        PrintWriter test = resp.getWriter();
        test.println("connect test");
        resp.getWriter().write("direct write test");
        test.flush();
        test.close();
    }
}
