package com.kube.pilot.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author tongysh
 */
public interface ISysPermissionService {


    /**
     * 获取用户平台角色权限
     * @param userId
     * @return
     */
    Set<String> getPlatformRolePermission(Long userId);


    /**
     * 获取用户平台菜单权限
     * @param userId
     * @return
     */
    Set<String> getPlatformMenuPermission(Long userId);


    /**
     * 获取用户在某个租户系统下的角色权限
     * @param userId
     * @param tenantId
     * @return
     */
    Set<String> getTenantRolePermission(Long userId,Long tenantId);


    /**
     * 获取用户在某个租户系统下的菜单权限
     * @param userId
     * @param tenantId
     * @return
     */
    Set<String> getTenantMenuPermission(Long userId,Long tenantId);



    /**
     * 获取当前用户在所有租户系统下的角色权限列表
     * @param userId
     * @param tenantIds
     * @return
     */
    Map<Long,Set<String>> getAllTenantRolePermission(Long userId, List<Long> tenantIds);



    /**
     * 获取当前用户在所有租户系统下的菜单权限列表
     * @param userId
     * @param tenantIds
     * @return
     */
    Map<Long,Set<String>> getAllTenantMenuPermission(Long userId, List<Long> tenantIds);




}
