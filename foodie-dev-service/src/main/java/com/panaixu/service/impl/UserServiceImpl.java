package com.panaixu.service.impl;

import com.panaixu.enums.Sex;
import com.panaixu.mapper.UsersMapper;
import com.panaixu.pojo.Users;
import com.panaixu.pojo.bo.UserBo;
import com.panaixu.service.UserService;
import com.panaixu.utils.DateUtil;
import com.panaixu.utils.MD5Utils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @PACKAGE_NAME: com.panaixu.service.impl
 * @Auther: PJaker
 * @DATE: 2019/12/23
 * @TIME: 17:18
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBo userBo) {
        Users users = Users.builder()
                .id(sid.nextShort())
                .username(userBo.getUsername())
                .nickname(userBo.getUsername())//默认用户昵称为用户名
                .face(USER_FACE)//默认头像
                .birthday(DateUtil.stringToDate("1900-01-01"))
                .sex(Sex.secret.type)
                .createdTime(new Date())
                .build();
        try {
            users.setPassword(MD5Utils.getMD5Str(userBo.getConfirmPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
            usersMapper.insert(users);
        return users;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);
        Users one = usersMapper.selectOneByExample(userExample);
        return one;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        Users one = usersMapper.selectOneByExample(userExample);
        return one == null ? false : true;
    }
}
