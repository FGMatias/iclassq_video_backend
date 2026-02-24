package com.iclass.video.enums;

public enum ConfigType {
    STRING,
    NUMBER,
    BOOLEAN,
    PATH,
    JSON;

    public static ConfigType fromString(String value) {
        if (value == null) {
            return null;
        }

        for (ConfigType type : ConfigType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Tipo de configuración invalid: " + value);
    }
}
