package com.kube.pilot.generator.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.kube.pilot.generator.domain.GenTableColumn;
import com.kube.pilot.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 业务字段 数据层
 *
 * @author Lion Li
 */
@InterceptorIgnore(dataPermission = "true", tenantLine = "true")
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumn, GenTableColumn> {

}
