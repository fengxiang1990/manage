  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping("/helloWorld.interface")
public class HelloWorldController {
    @RequestMapping
    public String load(String str){
        System.out.println(str);
        return "login";
    }
    @RequestMapping(params="method=syaHello")
    public String syaHello(Model model) {
        model.addAttribute("message", "Hello World!");
        System.out.println("ssss");
        return "login";
    }
}
