package com.hksar.sar.constant;

public enum SectorLevels {
    SECTOR("SECTOR"),
    MINOR_SECTOR("Minor Sector"),
    SUB_SECTOR("Sub Sector Category"),
    SECTOR_CATEGORY("Sector Category");

    private String name;

    SectorLevels(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
