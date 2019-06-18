/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miku.advs.modular.api;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import com.alibaba.fastjson.JSONObject;
import com.miku.advs.core.common.annotion.BussinessLog;
import com.miku.advs.core.common.constant.Const;
import com.miku.advs.core.common.exception.BizExceptionEnum;
import com.miku.advs.core.shiro.ShiroKit;
import com.miku.advs.core.shiro.ShiroUser;
import com.miku.advs.core.util.HttpEncryptUtil;
import com.miku.advs.core.util.JwtTokenUtil;
import com.miku.advs.core.util.ip.IpAddrUtils;
import com.miku.advs.modular.system.entity.system.User;
import com.miku.advs.modular.system.mapper.system.UserMapper;
import com.miku.advs.modular.system.service.api.SystemApiService;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 接口控制器提供
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/advsApi")
public class SystemApiController extends BaseController {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SystemApiService systemApiService;

    /**
     * 获取配置接口
     * 1，持久化设备信息
     * 2，统计每天活跃记录
     * 3，返回广告配置信息
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/getCfg",method = RequestMethod.POST)
    @ResponseBody
    public Object getConfig(@RequestParam("jsonParam") String jsonParam){

        JSONObject paramDecrypt = HttpEncryptUtil.paramDecrypt(JSONObject.parseObject(jsonParam));

        JSONObject dataJson= systemApiService.getConfig(paramDecrypt);

        if(dataJson!= null){
            return dataJson.toJSONString();
        } else {
            return new ErrorResponseData(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
        }
    }

    /**
     * 错误日志
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/upLog",method = RequestMethod.POST)
    @ResponseBody
    public Object upLog(@RequestParam("jsonParam") String jsonParam){

        JSONObject paramDecrypt = HttpEncryptUtil.paramDecrypt(JSONObject.parseObject(jsonParam));
        int rows = systemApiService.upLog(paramDecrypt);
        if(rows>0){
            return SuccessResponseData.success(Const.SUCCESS_STATUS,"日志上传成功","");
        } else {
            return SuccessResponseData.error(BizExceptionEnum.RUNTIME_ERR.getCode(),BizExceptionEnum.RUNTIME_ERR.getMessage());
        }
    }

    /**
     * 点击，展示数据
     * @param jsonParam
     * @return
     */
    @RequestMapping(value = "/report",method = RequestMethod.POST)
    @BussinessLog
    @ResponseBody
    public Object reportData(@RequestParam("jsonParam") String jsonParam){

        JSONObject paramDecrypt = HttpEncryptUtil.paramDecrypt(JSONObject.parseObject(jsonParam));
        int rows = systemApiService.reportData(paramDecrypt);
        if(rows>0){
            return SuccessResponseData.success(Const.SUCCESS_STATUS,"数据上报成功","");
        } else {
            return SuccessResponseData.error(BizExceptionEnum.RUNTIME_ERR.getCode(),BizExceptionEnum.RUNTIME_ERR.getMessage());
        }
    }


    /**
     * api登录接口，通过账号密码获取token
     */
    @RequestMapping("/auth")
    public Object auth(@RequestParam("username") String username,
                       @RequestParam("password") String password) {

        //封装请求账号密码为shiro可验证的token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());

        //获取数据库中的账号密码，准备比对
        User user = userMapper.getByAccount(username);

        String credentials = user.getPassword();
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(), credentials, credentialsSalt, "");

        //校验用户账号密码
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(
                usernamePasswordToken, simpleAuthenticationInfo);

        if (passwordTrueFlag) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("randomKey",user.getSalt());
            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getUserId())));
            return result;
        } else {
            return new ErrorResponseData(500, "账号密码错误！");
        }
    }

    /**
     * 测试接口是否走鉴权
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test() {
        return SUCCESS_TIP;
    }

}

