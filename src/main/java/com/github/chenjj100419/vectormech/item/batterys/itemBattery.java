package com.github.chenjj100419.vectormech.item.batterys;

import com.github.chenjj100419.vectormech.Vectormech;
import com.github.chenjj100419.vectormech.register.itemRegister;
import com.github.chenjj100419.vectormech.tab.ItemTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class itemBattery extends Item {
    public itemBattery(){
        super(new Properties()
                .maxStackSize(1)
                .group(ItemTab.group)
        );
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e ->
        {
            String msg = e.getEnergyStored() + " FE / " + e.getMaxEnergyStored() + " FE";
            tooltip.add(new StringTextComponent(msg));
        });
    }

    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, CompoundNBT nbt)
    {
        return new ICapabilityProvider()
        {
            private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage()
            {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate)
                {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(this.getMaxEnergyStored() - energy, maxReceive);
                    if (!simulate)
                    {
                        stack.getOrCreateTag().putInt("BatteryEnergy", energy + diff);
                    }
                    return diff;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate)
                {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(energy, maxExtract);
                    if (!simulate)
                    {
                        stack.getOrCreateTag().putInt("BatteryEnergy", energy - diff);
                    }
                    return diff;
                }

                @Override
                public int getEnergyStored()
                {
                    if (stack.hasTag())
                    {
                        int energy = Objects.requireNonNull(stack.getTag()).getInt("BatteryEnergy");
                        return Math.max(0, Math.min(this.getMaxEnergyStored(), energy));
                    }
                    return 0;
                }

                @Override
                public int getMaxEnergyStored() { return 48_000; }
                @Override
                public boolean canExtract() { return true; }
                @Override
                public boolean canReceive() { return true; }});

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
            {
                boolean isEnergy = Objects.equals(cap, CapabilityEnergy.ENERGY);
                return isEnergy ? this.lazyOptional.cast() : LazyOptional.empty();
            }
        };
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)){
            IntStream.rangeClosed(0, 1).forEach(i ->
            {
                ItemStack stack = new ItemStack(this);
                stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e ->
                {
                    int energy = e.getMaxEnergyStored() * i;
                    e.receiveEnergy(energy, false);
                    items.add(stack);
                });
            });
        }
    }

}
