package com.kube.pilot.web.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kube.pilot.common.core.constant.Constants;
import com.kube.pilot.common.core.constant.GlobalConstants;
import com.kube.pilot.common.core.constant.SystemConstants;
import com.kube.pilot.common.core.domain.model.LoginUser;
import com.kube.pilot.common.core.domain.model.PasswordLoginBody;
import com.kube.pilot.common.core.enums.LoginType;
import com.kube.pilot.common.core.exception.user.CaptchaException;
import com.kube.pilot.common.core.exception.user.CaptchaExpireException;
import com.kube.pilot.common.core.exception.user.UserException;
import com.kube.pilot.common.core.utils.MessageUtils;
import com.kube.pilot.common.core.utils.StringUtils;
import com.kube.pilot.common.core.utils.ValidatorUtils;
import com.kube.pilot.common.json.utils.JsonUtils;
import com.kube.pilot.common.redis.utils.RedisUtils;
import com.kube.pilot.common.satoken.utils.LoginHelper;
import com.kube.pilot.common.tenant.helper.TenantHelper;
import com.kube.pilot.common.web.config.properties.CaptchaProperties;
import com.kube.pilot.system.domain.SysUser;
import com.kube.pilot.system.domain.vo.SysClientVo;
import com.kube.pilot.system.domain.vo.SysUserVo;
import com.kube.pilot.system.mapper.SysUserMapper;
import com.kube.pilot.web.domain.vo.LoginVo;
import com.kube.pilot.web.service.IAuthStrategy;
import com.kube.pilot.web.service.SysLoginService;
import org.springframework.stereotype.Service;

/**
 * 密码认证策略
 *
 * @author tongysh
 */
@Slf4j
@Service("password" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class PasswordAuthStrategy implements IAuthStrategy {

    private final CaptchaProperties captchaProperties;
    private final SysLoginService loginService;

    @Override
    public LoginVo login(String body, SysClientVo client) {
        PasswordLoginBody loginBody = JsonUtils.parseObject(body, PasswordLoginBody.class);
//        ValidatorUtils.validate(loginBody);
//        String tenantId = loginBody.getTenantId();
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
//        String code = loginBody.getCode();
//        String uuid = loginBody.getUuid();
//
//        boolean captchaEnabled = captchaProperties.getEnable();
        // 验证码开关
//        if (captchaEnabled) {
//            validateCaptcha(tenantId, username, code, uuid);
//        }
//        LoginUser loginUser = TenantHelper.dynamic(tenantId, () -> {
//            SysUserVo user = loadUserByUsername(username);
//            loginService.checkLogin(LoginType.PASSWORD, tenantId, username, () -> !BCrypt.checkpw(password, user.getPassword()));
//            // 此处可根据登录用户的数据不同 自行创建 loginUser
//            return loginService.buildLoginUser(user);
//        });


        /**
         * 校验用户名和密码信息并构建loginUser
         */
        SysUserVo user = loginService.checkUser(username);
        loginService.checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, user.getPassword()));
        LoginUser loginUser = loginService.buildLoginUser(user,client);



        SaLoginParameter model = new SaLoginParameter();
        model.setDeviceType(client.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(client.getTimeout());
        model.setActiveTimeout(client.getActiveTimeout());
        model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
        // 生成token
        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    private void validateCaptcha(String tenantId, String username, String code, String uuid) {
        String verifyKey = GlobalConstants.CAPTCHA_CODE_KEY + StringUtils.blankToDefault(uuid, "");
        String captcha = RedisUtils.getCacheObject(verifyKey);
        RedisUtils.deleteObject(verifyKey);
        if (captcha == null) {
            loginService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"));
            throw new CaptchaExpireException();
        }
        if (!StringUtils.equalsIgnoreCase(code, captcha)) {
            loginService.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
    }



}
