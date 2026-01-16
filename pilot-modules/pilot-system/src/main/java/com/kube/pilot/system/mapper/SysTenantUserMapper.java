package com.kube.pilot.system.mapper;

import com.kube.pilot.common.mybatis.core.mapper.BaseMapperPlus;
import com.kube.pilot.system.domain.SysTenantUser;
import com.kube.pilot.system.domain.vo.SysTenantVo;
import com.kube.pilot.system.domain.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysTenantUserMapper extends BaseMapperPlus<SysTenantUser,SysTenantUser> {



    List<SysTenantVo> getAllManageTenantOfUser(String userId);


    List<SysUserVo> getAllUserOfTenant(String tenantId)


}
