package com.panaixu.service;

import com.panaixu.pojo.Users;
import com.panaixu.pojo.bo.UserBo;

/**
 * @PACKAGE_NAME: com.panaixu.service
 * @Auther: PJaker
 * @DATE: 2019/12/23
 * @TIME: 17:15
 * @Description:
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     */
   public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBo
     * @return
     */
   public Users createUser(UserBo userBo);

    /**
     * 检索用户名和密码是否匹配，用于登录
     * @param username
     * @param password
     * @return
     */
   public Users queryUserForLogin(String username,String password);
}
