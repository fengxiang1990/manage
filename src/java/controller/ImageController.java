/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ImageService;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/ImageController.interface")
public class ImageController extends HttpServlet{
    ImageService service =   new ImageService();
    @RequestMapping
    public String getImagePg(Model model,HttpServletResponse res,HttpServletRequest request) throws IOException {
        //@RequestParam String json,
        String json = null;
        try{
        //request.getParameter("json");
            // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb1 = new StringBuilder();
        while((line = br.readLine())!=null){
            sb1.append(line);
        }
        // 将资料解码
        json = sb1.toString();
        System.out.println(json);
        PrintWriter out;
        out = res.getWriter();
        JSONObject  jo =JSONObject.fromObject(json);
        String[] args = service.getImagePg(jo.getInt("pg"),jo.getInt("size"));
        StringBuilder sb  = new StringBuilder();
        Map<String,String> data =null;
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for(String url:args){
            data = new HashMap<String,String> ();
            data.put("url",url);
            list.add(data);
        }
        JSONArray array  = JSONArray.fromObject(list);
        out.print(array);
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("参数为空");
        }
        return null;
    }
}
