package com.flycinema.flycinema_boot.controller;


import com.flycinema.flycinema_boot.entity.UserDO;
import com.flycinema.flycinema_boot.entity.UserVo;
import com.flycinema.flycinema_boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liudingrong
 */
@Controller
public class MySpringBootApplication {
    @Autowired
    UserService userService;
    @RequestMapping("/quick")
    public String quick() {
        return "html/mypage";
    }

    @RequestMapping("/loginView")
    public String login2() {
        return "login";
    }

    //templates目录下的页面只能Controller来访问。需要模板引擎支持
    @RequestMapping("/index")
    public String index() {
        System.out.println("进入index");
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password){
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "redirect:loginView";
    }

    @RequestMapping("/register")
    public String regist( UserVo userVo) {
        System.out.println("注册userVO"+userVo);
        UserDO userDO = new UserDO();
        try {
            userDO.setPassword(userVo.getPassword());
            userDO.setUsername(userVo.getUsername());
            userService.save(userDO);
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            return "register";
        }
    }
    @RequestMapping("/selectUser")
    public String selectUser() {
        return "login";
    }

}
