/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */
@Controller
public class LoginController {
  
   @RequestMapping("/login.interface")
   public String login(Model mod,HttpServletRequest request){
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       if(username.equals("admin")&&password.equals("admin")){
           mod.addAttribute("msg","admin");
           return "main";
       }
       mod.addAttribute("msg","用户名或密码错误");
       return "index";
   }
}
