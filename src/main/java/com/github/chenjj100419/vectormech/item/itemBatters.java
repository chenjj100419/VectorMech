package com.github.chenjj100419.vectormech.item;

import com.github.chenjj100419.vectormech.Vectormech;
import com.github.chenjj100419.vectormech.tab.ItemTab;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class itemBatters extends Item {
    public itemBatters(){
        super(new Item.Properties()
                .maxStackSize(1)
                .maxDamage(6)
                .defaultMaxDamage(3)
                .group(ItemTab.group)
        );
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(I18n.format(Vectormech.MOD_ID+"tooltip.batters")));
    }
}
