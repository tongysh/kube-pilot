package com.kube.pilot.demo.mapper;

import com.kube.pilot.demo.domain.vo.TestTreeVo;
import com.kube.pilot.common.mybatis.annotation.DataColumn;
import com.kube.pilot.common.mybatis.annotation.DataPermission;
import com.kube.pilot.common.mybatis.core.mapper.BaseMapperPlus;
import com.kube.pilot.demo.domain.TestTree;

/**
 * 测试树表Mapper接口
 *
 * @author tongysh
 * @date 2021-07-26
 */
@DataPermission({
    @DataColumn(key = "deptName", value = "dept_id"),
    @DataColumn(key = "userName", value = "user_id")
})
public interface TestTreeMapper extends BaseMapperPlus<TestTree, TestTreeVo> {

}
