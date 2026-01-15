package com.kube.pilot.system.service.impl;

import lombok.RequiredArgsConstructor;
import com.kube.pilot.common.core.constant.TenantConstants;
import com.kube.pilot.common.core.service.PermissionService;
import com.kube.pilot.common.satoken.utils.LoginHelper;
import com.kube.pilot.system.service.ISysMenuService;
import com.kube.pilot.system.service.ISysPermissionService;
import com.kube.pilot.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class SysPermissionServiceImpl implements ISysPermissionService, PermissionService {

    private final ISysRoleService roleService;
    private final ISysMenuService menuService;


    @Override
    public Set<String> getPlatformRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginHelper.isSuperAdmin(userId)) {
            roles.add(TenantConstants.SUPER_ADMIN_ROLE_KEY);
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }


    @Override
    public Set<String> getPlatformMenuPermission (Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (LoginHelper.isSuperAdmin(userId)) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }

    @Override
    public Set<String> getTenantRolePermission(Long userId, Long tenantId) {
        return Set.of();
    }

    @Override
    public Set<String> getTenantMenuPermission(Long userId, Long tenantId) {
        return Set.of();
    }

    @Override
    public Map<Long, Set<String>> getAllTenantRolePermission(Long userId, List<Long> tenantIds) {
        return Map.of();
    }

    @Override
    public Map<Long, Set<String>> getAllTenantMenuPermission(Long userId, List<Long> tenantIds) {
        return Map.of();
    }
}
