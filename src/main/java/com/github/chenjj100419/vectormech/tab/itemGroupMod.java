package com.github.chenjj100419.vectormech.tab;

import com.github.chenjj100419.vectormech.register.itemRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.github.chenjj100419.vectormech.Vectormech.MOD_ID;

public class itemGroupMod extends ItemGroup {
    public itemGroupMod(){
        super(MOD_ID+"_tab");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(itemRegister.battery.get());
    }
}
