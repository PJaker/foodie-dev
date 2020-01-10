package com.panaixu.controller;

import com.panaixu.pojo.Users;
import com.panaixu.pojo.bo.UserBo;
import com.panaixu.service.UserService;
import com.panaixu.utils.CookieUtils;
import com.panaixu.utils.IMOOCJSONResult;
import com.panaixu.utils.JsonUtils;
import com.panaixu.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PACKAGE_NAME: com.panaixu.controller
 * @Auther: PJaker
 * @DATE: 2019/12/23
 * @TIME: 14:59
 * @Description:
 */
@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "检测用户名是否存在",notes = "检测用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        } else {
            return IMOOCJSONResult.ok();
        }
    }

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBo userBo) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("密码不能为空");
        }
        if (StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("确认密码不能为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能小于6");
        }
        if (!password.endsWith(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }
        Users user = userService.createUser(userBo);
        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBo userBo,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("密码不能为空");
        }
        try {
            password = MD5Utils.getMD5Str(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Users user = userService.queryUserForLogin(username,password);
        if(user == null){
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }
        user = setNullProperty(user);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(user), true);

        return IMOOCJSONResult.ok(user);
    }


    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }


    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        //清楚用户相关信息
        CookieUtils.deleteCookie(request, response, "user");
        //TODO 用户退出登录需要清空购物车
        //TODO 分布式回话需要清空用户数据
        return IMOOCJSONResult.ok();
    }


}
