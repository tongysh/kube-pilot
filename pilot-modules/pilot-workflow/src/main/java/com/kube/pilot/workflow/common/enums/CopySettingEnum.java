package com.kube.pilot.workflow.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 抄送设置枚举
 *
 * @author tongysh
 */
@Getter
@AllArgsConstructor
public enum CopySettingEnum implements NodeExtEnum {
    ;
    private final String label;
    private final String value;
    private final boolean selected;

}

