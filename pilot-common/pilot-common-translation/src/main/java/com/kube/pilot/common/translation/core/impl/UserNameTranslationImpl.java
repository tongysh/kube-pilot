package com.kube.pilot.common.translation.core.impl;

import com.kube.pilot.common.translation.annotation.TranslationType;
import com.kube.pilot.common.translation.constant.TransConstant;
import com.kube.pilot.common.translation.core.TranslationInterface;
import com.kube.pilot.common.core.service.UserService;
import lombok.AllArgsConstructor;

/**
 * 用户名翻译实现
 *
 * @author tongysh
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NAME)
public class UserNameTranslationImpl implements TranslationInterface<String> {

    private final UserService userService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof Long id) {
            return userService.selectUserNameById(id);
        }
        return null;
    }
}
