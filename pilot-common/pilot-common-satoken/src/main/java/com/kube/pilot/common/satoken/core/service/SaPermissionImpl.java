package com.kube.pilot.common.satoken.core.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.kube.pilot.common.satoken.utils.LoginHelper;
import com.kube.pilot.common.core.domain.model.LoginUser;
import com.kube.pilot.common.core.enums.UserType;
import com.kube.pilot.common.core.exception.ServiceException;
import com.kube.pilot.common.core.service.PermissionService;
import com.kube.pilot.common.core.utils.SpringUtils;
import com.kube.pilot.common.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 权限管理实现类
 *
 * @author tongysh
 */
public class SaPermissionImpl implements StpInterface {

    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (ObjectUtil.isNull(loginUser) || !loginUser.getLoginId().equals(loginId)) {
            PermissionService permissionService = getPermissionService();
            if (ObjectUtil.isNotNull(permissionService)) {
                List<String> list = StringUtils.splitList(loginId.toString(), ":");
                return new ArrayList<>(permissionService.getPlatformMenuPermission(Long.parseLong(list.get(1))));
            } else {
                throw new ServiceException("PermissionService 实现类不存在");
            }
        }
        UserType userType = UserType.getUserType(loginUser.getUserType());
        if (userType == UserType.APP_USER) {
            // 其他端 自行根据业务编写
        }
        if (CollUtil.isNotEmpty(loginUser.getPlatformMenuPermission())) {
            // SYS_USER 默认返回权限
            return new ArrayList<>(loginUser.getPlatformMenuPermission());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (ObjectUtil.isNull(loginUser) || !loginUser.getLoginId().equals(loginId)) {
            PermissionService permissionService = getPermissionService();
            if (ObjectUtil.isNotNull(permissionService)) {
                List<String> list = StringUtils.splitList(loginId.toString(), ":");
                return new ArrayList<>(permissionService.getPlatformRolePermission(Long.parseLong(list.get(1))));
            } else {
                throw new ServiceException("PermissionService 实现类不存在");
            }
        }
        UserType userType = UserType.getUserType(loginUser.getUserType());
        if (userType == UserType.APP_USER) {
            // 其他端 自行根据业务编写
        }
        if (CollUtil.isNotEmpty(loginUser.getPlatformRolePermission())) {
            // SYS_USER 默认返回权限
            return new ArrayList<>(loginUser.getPlatformRolePermission());
        } else {
            return new ArrayList<>();
        }
    }

    private PermissionService getPermissionService() {
        try {
            return SpringUtils.getBean(PermissionService.class);
        } catch (Exception e) {
            return null;
        }
    }

}
