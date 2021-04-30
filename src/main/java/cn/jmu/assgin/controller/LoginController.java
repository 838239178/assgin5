package cn.jmu.assgin.controller;

import cn.jmu.assgin.domain.dto.LoginDTO;
import cn.jmu.assgin.domain.entity.Person;
import cn.jmu.assgin.domain.vo.UserVO;
import cn.jmu.assgin.service.LoginService;
import lombok.extern.java.Log;
import org.apache.commons.beanutils.BeanUtils;
import org.shijh.myframework.framework.annotation.Autowired;
import org.shijh.myframework.framework.annotation.Component;
import org.shijh.myframework.framework.annotation.Mapping;
import org.shijh.myframework.framework.bean.ModelAndView;
import org.shijh.myframework.framework.controller.Controller;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

@Component("loginController")
@Mapping("/auth")
@Log
public class LoginController extends Controller {
    @Autowired
    private LoginService loginService;

    @Mapping("/login")
    public ModelAndView login(LoginDTO loginInfo, HttpSession session) throws IllegalAccessException, InvocationTargetException {
        ModelAndView mv = new ModelAndView();
        Person principal = loginService.verifyLogin(loginInfo.getUsername(), loginInfo.getPassword());
        boolean success = principal != null;
        mv.setSuccess(success);
        if (success) {
            mv.setView("/index.jsp");
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userVO, principal);
            session.setAttribute("user", userVO);
            mv.setModel(userVO);
        } else {
            mv.setView("/login.jsp");
            mv.setModel("账号或密码错误");
        }
        return mv;
    }

    @Mapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Object userId = httpSession.getAttribute("user");
        if (userId == null) {
            modelAndView.setSuccess(false);
            modelAndView.setView("/login.jsp");
            modelAndView.setModel("没有登录");
        } else {
            httpSession.removeAttribute("user");
            modelAndView.setSuccess(true);
            modelAndView.setView("/index.jsp");
        }
        return modelAndView;
    }
}
