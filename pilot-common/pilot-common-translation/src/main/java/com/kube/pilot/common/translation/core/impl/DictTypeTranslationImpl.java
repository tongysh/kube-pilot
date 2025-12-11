package com.kube.pilot.common.translation.core.impl;

import com.kube.pilot.common.translation.annotation.TranslationType;
import com.kube.pilot.common.translation.constant.TransConstant;
import com.kube.pilot.common.translation.core.TranslationInterface;
import com.kube.pilot.common.core.service.DictService;
import com.kube.pilot.common.core.utils.StringUtils;
import lombok.AllArgsConstructor;

/**
 * 字典翻译实现
 *
 * @author tongysh
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String dictValue && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, dictValue);
        }
        return null;
    }
}
