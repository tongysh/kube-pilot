package com.kube.pilot.common.core.service;

import java.util.Set;

/**
 * 用户权限处理
 *
 * @author tongysh
 */
public interface PermissionService {

    /**
     * 获取角色数据权限
     *
     * @param userId  用户id
     * @return 角色权限信息
     */
    Set<String> getPlatformRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     *
     * @param userId  用户id
     * @return 菜单权限信息
     */
    Set<String> getPlatformMenuPermission(Long userId);

}
