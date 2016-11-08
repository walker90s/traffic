package com.tt.traffic.common.enums;

/**
 * Created by admin on 2015/11/21.
 * 模型类型枚举
 */
public enum ModelTypeEnum {
    BIG_MODEL(1, "宏观模型"),
    MEDIUM_MODEL(2, "中观模型"),
    MICROCOSMIC_MODEL(3, "微观模型"),
    INTERSECTION_MODEL(4, "路口模型");
    private final int value;
    private final String name;

    ModelTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
