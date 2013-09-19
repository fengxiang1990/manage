/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Role;
import entity.Smenu;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MenuService;
import util.SingletonMap;

/**
 *
 * @author admin
 */
@Controller
public class MenuController {

    private List<Smenu> data = new ArrayList<Smenu>();
    private List<Smenu> lev1;
    private List<Smenu> lev2;
    private static String CURRENT_MENU_ID ="current_menu_id";
    SingletonMap smap;
    @Autowired
    private MenuService menuService;

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping("/test.interface")
    public void test() throws SQLException {
        this.getMenuService().test();
    }

    @RequestMapping("/menu1.interface")
    public void findOneLevleMenuByUserName(HttpServletResponse response,HttpServletRequest request) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            String  user  = request.getParameter("username");
            List<Smenu> datas = this.menuService.findSmenuByUserName(user);
            System.out.println(datas.size());
            this.data = datas;
            JSONArray ja = null;
            lev1 = new ArrayList<Smenu>();
            for (Smenu s : datas) {
                if (s.getLevel() == 1) {
                    lev1.add(s);
                    ja = JSONArray.fromObject(lev1);
                }
            }
            System.out.println(ja.toString());
            out.print(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/menu2.interface")
    public void findSecondLevleMenuByPid(HttpServletResponse response,HttpServletRequest request) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JSONArray ja = null;
            int pid  = Integer.parseInt(request.getParameter("id"));
            lev2 = new ArrayList<Smenu>();
            for (Smenu s : this.data) {
                if (s.getPid() == pid) {
                    lev2.add(s);
                    ja = JSONArray.fromObject(lev2);
                }
            }
            System.out.println(ja.toString());
            out.print(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/tree.interface")
    public void initTree(HttpServletResponse response,HttpServletRequest request) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JSONArray ja = null;
            int id =0;
            List<Smenu> data =null;
            try{
            id = Integer.parseInt(request.getParameter("id"));
            }catch(Exception e){
                id = 0;
            }
            if(id == 0){
             data = this.menuService.findSmenuByLv(1);
            }else if(id>=1){
                data = this.menuService.getChildren(id);
            }
            System.out.println("id:"+id);
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map map;
            for (Smenu s :data){
                System.out.println("first:"+s.getName());
               map = new HashMap<String,Object>();
               map.put("id",s.getId());
               map.put("text",s.getName());
               map.put("state","closed");
               List<Map<String,Object>> childlist = new ArrayList<Map<String,Object>>();
               Map<String,Object> childmap=null;
               if(this.menuService.hasChildren(s.getId())){
                   List<Smenu> child_data = this.menuService.getChildren(s.getId());
                   for(Smenu sc:child_data){
                      childmap = new HashMap<String,Object>();
                      System.out.println(sc.getName());
                      childmap.put("id",sc.getId());
                      childmap.put("text",sc.getName());
                 /* *   if(this.menuService.hasChildren(sc.getId())){
                          childmap.put("state","closed"); 
                      }else{
                          childmap.put("state","opened"); 
                      }
                      * */
                      childmap.put("state","closed"); 
                      childlist.add(childmap);
                      map.put("children", childlist);
                   }
               }
               list.add(map);
            }
            ja =JSONArray.fromObject(list);
            System.out.println(ja.toString());
            out.print(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/treejsp.interface")
    public String treeJsp(HttpServletResponse response,HttpServletRequest request) {
       return "tree";
    }
    
    @RequestMapping("/rolejsp.interface")
    public String roleJsp(HttpServletResponse response,HttpServletRequest request) {
       return "role";
    }
    
    @RequestMapping("/role.interface")
    public void role(HttpServletResponse response,HttpServletRequest request) {
         try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JSONArray ja = null;
            List<Role> roles= new ArrayList<Role>();
            for (Role s : this.menuService.findAllRoles()) {
                    roles.add(s);
                    ja = JSONArray.fromObject(roles);
            }
            System.out.println(ja.toString());
            out.print(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/getAction.interface")
    public void  getAction(HttpServletResponse response,HttpServletRequest request) {
         try {
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String id = request.getParameter("id");
            out.print(this.menuService.getActionById(Integer.parseInt(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
     @RequestMapping("/rolemenujsp.interface")
    public String roleMenuJsp(HttpServletResponse response,HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        session.setAttribute("id",id);
        smap = SingletonMap.getInstance();
        smap.put(CURRENT_MENU_ID,id);
        return "rolemenu";
    }
     
    @RequestMapping("/roleMenu.interface")
    public void roleMenu(HttpServletResponse response,HttpServletRequest request) {
         try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            JSONArray ja = null;
            List<Map<String,Object>> childlist = new ArrayList<Map<String,Object>>();
            Map<String,Object> childmap=null;
            int id = Integer.parseInt(String.valueOf(smap.get(CURRENT_MENU_ID)));
            System.out.println("id from map:"+id);
            List<Smenu> child_data = this.menuService.getChildren(id);
                   for(Smenu sc:child_data){
                      childmap = new HashMap<String,Object>();
                      childmap.put("id",sc.getId());
                      childmap.put("text",sc.getName());
                      childmap.put("action",sc.getAction()); 
                      childlist.add(childmap);
                   }
             ja = JSONArray.fromObject(childlist);
            System.out.println(ja.toString());
            out.print(ja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @RequestMapping("/addrole.interface")
    public void addRole(HttpServletResponse response,HttpServletRequest request) {
         response.setContentType("application/json;charset=utf-8");
         JSONObject json= null;
         try {
            PrintWriter out = response.getWriter();
            String name= request.getParameter("name");
            name = new String(name.getBytes("iso8859-1"),"UTF-8");
            System.out.println(name);          
            boolean flag = this.menuService.addRole(name);
            json = new JSONObject();
            json.put("success",flag);
            json.put("message","Message sent successfully");
            out.print(json);
         }catch(Exception e){
         e.printStackTrace();
         }
    }
}
