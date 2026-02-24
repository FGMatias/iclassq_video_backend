package com.iclass.video.enums;

public enum ConfigCategory {
    VIDEO("Video"),
    DEVICE("Dispositivo"),
    SYSTEM("Sistema"),
    NETWORK("Red");

    private final String displayName;

    ConfigCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ConfigCategory fromString(String value) {
        if (value == null) {
            return null;
        }

        for (ConfigCategory category : ConfigCategory.values()) {
            if (category.name().equalsIgnoreCase(value)) {
                return category;
            }
        }

        throw new IllegalArgumentException("Categoria de configuración inválida: " + value);
    }
}
