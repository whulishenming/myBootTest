package com.lsm.boot.shiro.controller;

import com.lsm.boot.shiro.model.User;
import com.lsm.boot.shiro.util.ShiroUtils;
import com.lsm.boot.shiro.vo.Result;
import com.lsm.boot.shiro.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class LoginController {

    //首页
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index() {

        return "index";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {

        return "/login";
    }

    @RequestMapping(value="/ajaxLogin",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> submitLogin(User user) {

        try {

            Subject subject = ShiroUtils.getSubject();
            String passwordEncrypt = new Sha256Hash(user.getPassword()).toHex();

            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), passwordEncrypt);
            subject.login(token);

            return new Result<>(ResultCode.C200.getCode(), ResultCode.C200.getDesc(), true);

        } catch (AccountException e) {
            return new Result<>(ResultCode.C500.getCode(), ResultCode.C200.getDesc(), true);
        }
    }

    @RequestMapping(value="/logout",method =RequestMethod.GET)
    @ResponseBody
    public Result<Boolean> logout(){
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(ResultCode.C500.getCode(), ResultCode.C200.getDesc(), true);
        }

        return new Result<>(ResultCode.C200.getCode(), ResultCode.C200.getDesc(), true);
    }

}
