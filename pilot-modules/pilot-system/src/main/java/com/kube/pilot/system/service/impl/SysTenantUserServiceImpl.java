package com.kube.pilot.system.service.impl;

import com.kube.pilot.system.domain.vo.SysTenantVo;
import com.kube.pilot.system.domain.vo.SysUserVo;
import com.kube.pilot.system.mapper.SysTenantUserMapper;
import com.kube.pilot.system.service.ISysTenantUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class SysTenantUserServiceImpl implements ISysTenantUserService {


    private final SysTenantUserMapper baseMapper;


    @Override
    public List<SysTenantVo> getAllManageTenantOfUser(String userId) {
        return baseMapper.getAllManageTenantOfUser(userId);
    }

    @Override
    public List<SysUserVo> getAllUserOfTenant(String tenantId) {
        return baseMapper.getAllUserOfTenant(tenantId);
    }
}
