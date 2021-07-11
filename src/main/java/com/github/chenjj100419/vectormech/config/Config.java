package com.github.chenjj100419.vectormech.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue ENABLE_REGISTER;
    public static ForgeConfigSpec.IntValue NORM_BATTER_MAX_FE;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");

        ENABLE_REGISTER = COMMON_BUILDER.comment("Enable mod register...").define("enable_register",true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
