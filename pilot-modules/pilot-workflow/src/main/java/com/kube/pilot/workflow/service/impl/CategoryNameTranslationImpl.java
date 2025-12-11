package com.kube.pilot.workflow.service.impl;

import cn.hutool.core.convert.Convert;
import com.kube.pilot.workflow.service.IFlwCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kube.pilot.common.translation.annotation.TranslationType;
import com.kube.pilot.common.translation.core.TranslationInterface;
import com.kube.pilot.workflow.common.ConditionalOnEnable;
import com.kube.pilot.workflow.common.constant.FlowConstant;
import org.springframework.stereotype.Service;

/**
 * 流程分类名称翻译实现
 *
 * @author tongysh
 */
@ConditionalOnEnable
@Slf4j
@RequiredArgsConstructor
@Service
@TranslationType(type = FlowConstant.CATEGORY_ID_TO_NAME)
public class CategoryNameTranslationImpl implements TranslationInterface<String> {

    private final IFlwCategoryService flwCategoryService;

    @Override
    public String translation(Object key, String other) {
        return flwCategoryService.selectCategoryNameById(Convert.toLong(key));
    }
}
