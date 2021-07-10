package com.github.chenjj100419.vectormech.register;

import com.github.chenjj100419.vectormech.Vectormech;
import com.github.chenjj100419.vectormech.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class itemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Vectormech.MOD_ID);
    public static final RegistryObject<Item> battery = ITEMS.register("battery", itemBattery::new);
}
