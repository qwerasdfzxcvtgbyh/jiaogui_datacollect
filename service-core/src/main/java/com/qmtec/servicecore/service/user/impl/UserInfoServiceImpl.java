package com.qmtec.servicecore.service.user.impl;

import com.qmtec.common.exception.CustomException;
import com.qmtec.common.web.HttpCode;
import com.qmtec.servicecore.dao.UserInfoMapper;
import com.qmtec.servicecore.entity.FlumeConfig;
import com.qmtec.servicecore.entity.UserInfo;
import com.qmtec.servicecore.entity.example.UserInfoExample;
import com.qmtec.servicecore.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 用户登录验证
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Boolean verifyLogin(String userName, String passWord) {
        Boolean flag = false;

        passWord = DigestUtils.md5DigestAsHex(passWord.getBytes());

        Optional<UserInfo> UserInfoOptional = null;

        try {
            UserInfoOptional = Optional.ofNullable(
                    userInfoMapper.selectOneByExample(
                            UserInfoExample.newAndCreateCriteria()
                                    .andUserNameEqualTo(userName)
                                    .andPassWordEqualTo(passWord)
                                    .example()));

            if(UserInfoOptional.isPresent()){
                flag = true;
            }else{
                throw new CustomException("用户名或密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage(), HttpCode.CODE_500);
        }

        return flag;
    }
}
