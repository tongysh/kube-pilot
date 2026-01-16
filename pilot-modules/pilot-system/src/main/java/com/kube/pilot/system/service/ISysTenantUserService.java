package com.kube.pilot.system.service;


import com.kube.pilot.system.domain.vo.SysTenantVo;
import com.kube.pilot.system.domain.vo.SysUserVo;

import java.util.List;



public interface ISysTenantUserService {


    /**
     * 获取某用户所管理的所有租户系统列表
     *
     * @param userId 用户id
     * @return 租户系统列表
     */
    List<SysTenantVo> getAllManageTenantOfUser(String userId);


    /**
     * 获取某租户系统下的所有用户列表
     * @param tenantId 租户系统id
     * @return 用户列表
     */
    List<SysUserVo> getAllUserOfTenant(String tenantId);

}
