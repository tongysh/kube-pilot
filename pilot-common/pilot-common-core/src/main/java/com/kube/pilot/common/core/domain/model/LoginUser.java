package com.kube.pilot.common.core.domain.model;

import com.kube.pilot.common.core.domain.dto.TenantDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kube.pilot.common.core.domain.dto.PostDTO;
import com.kube.pilot.common.core.domain.dto.RoleDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author tongysh
 */
@Data
@NoArgsConstructor
public class LoginUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;



    /**
     * 用户基本信息
     */
    private Long userId;
    private String userName;
    private String nickName;
    private String userType;
    private String email;
    private String phoneNumber;



    /**
     * 当前用户管理的租户系统id列表
     */
    private List<TenantDTO> manageTenants;


    /**
     * 当前用户登录到的租户系统
     */
    private TenantDTO loginTenant;



    /**
     * 用户唯一标识
     */
    private String token;


    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;


    /**
     * 用户权限信息
     */
    private Set<String> platformMenuPermission;  // 平台菜单权限
    private Set<String> platformRolePermission;  // 平台角色权限
    private Set<String> tenantMenuPermission;    // 当前登录租户菜单权限
    private Set<String> tenantRolePermission;    // 当前登录租户角色权限


    /**
     * 角色对象
     */
    private List<RoleDTO> roles;

    /**
     * 岗位对象
     */
    private List<PostDTO> posts;

    /**
     * 数据权限 当前角色ID
     */
    private Long roleId;

    /**
     * 客户端
     */
    private String clientKey;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 获取登录id
     */
    public String getLoginId() {
        if (userType == null) {
            throw new IllegalArgumentException("用户类型不能为空");
        }
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return userType + ":" + userId;
    }

}
