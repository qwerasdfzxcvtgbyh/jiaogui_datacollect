package com.qmtec.servicecore.controller.login;

import com.alibaba.fastjson.JSON;
import com.qmtec.common.web.ResultModel;
import com.qmtec.servicecore.config.WebMvcConfg;
import com.qmtec.servicecore.service.user.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by caesar on 2016/4/26.
 */
@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 显示登录页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() throws Exception {
        return "/login/login";
    }

    /**
     * 登录验证操作
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel<Boolean> loginVerify(@RequestBody String data, HttpSession session) {
        ResultModel<Boolean> resultModel = new ResultModel<>();

        Map<String, String> map = JSON.parseObject(data, Map.class);
        String userName = map.get("userName");
        String passWord = map.get("passWord");
        boolean verify = userInfoService.verifyLogin(userName, passWord);
        if (verify) {
            session.setAttribute(WebMvcConfg.SESSION_KEY, userName);
        }
        resultModel.setData(verify);
        resultModel.setMessage("处理成功");

        return resultModel;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebMvcConfg.SESSION_KEY);
        return "redirect:/login";
    }

}
