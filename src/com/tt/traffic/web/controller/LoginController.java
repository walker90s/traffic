package com.tt.traffic.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tt.traffic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tt.traffic.common.util.Const;
import com.tt.traffic.common.util.Tools;
import com.tt.traffic.domain.model.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 95 on 2015/11/13.
 */
@Controller
public class LoginController {

	@Resource
    private UserService userService;

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public String loginGet(){
        return "login";
    }

    @RequestMapping(value = "/login",method=RequestMethod.POST)
    public ModelAndView loginPost(HttpSession session,@RequestParam String loginname,@RequestParam String password,@RequestParam String code){
		String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);
		ModelAndView mv = new ModelAndView();
		String errInfo = "";
		User user = null;
		if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){
			user = userService.loginInfo(loginname, password);
			if(user!=null){
				session.setAttribute(Const.SESSION_USER, user);
				session.removeAttribute(Const.SESSION_SECURITY_CODE);
			}else{
				errInfo = "用户名或密码有误�?";
			}
		}else{
			errInfo = "验证码输入有误！";
		}
		if(Tools.isEmpty(errInfo)){
			mv.setViewName("redirect:index.html");
		}else{
			mv.addObject("errInfo", errInfo);
			mv.addObject("loginname",loginname);
			mv.addObject("password",password);
			mv.setViewName("login");
		}
		return mv;
	}

	/**
	 * 安全�?�?
	 * @param session
	 * @return
	 */
	@RequestMapping("/quitting")
	public String Quitting(HttpSession session){
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_USER_RIGHTS);
		return "index";
	}
    @RequestMapping(value = "/regsiter")
    public String regsiter(){
    	return "regsiter";
    }
    @RequestMapping(value = "/regsit")
    public void regsit(User user,HttpServletResponse response){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	String registeredTime = df.format(new Date());
    	user.setRegisteredTime(registeredTime);
    	PrintWriter out; 
		response.setContentType("text/html; charset=UTF-8"); //转码
		try {
			out = response.getWriter();
			out.flush();
			out.println("<script>");
			if (userService.getUsetByName(user.getLoginname())!=null){
				out.println("alert('注册失败!用户名已存在');");
				out.println("history.back();");
			}else{
				if(userService.saveUser(user)){
					out.println("alert('注册成功!请您登录');");
					out.println("window.location.href='login.html'");
				}else{
					out.println("alert('注册失败!');");
					out.println("history.back();");
				}

    		}
			out.println("</script>");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
