package com.github.chenjj100419.vectormech.item;

import com.github.chenjj100419.vectormech.Vectormech;
import com.github.chenjj100419.vectormech.register.itemRegister;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PropertyRegistry {
        @SubscribeEvent
        public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ItemModelsProperties.registerProperty(itemRegister.battery.get(), new ResourceLocation("n_b_energy"), (itemStack, clientWorld, livingEntity) -> {
                    LazyOptional<IEnergyStorage> lazyOptional = itemStack.getCapability(CapabilityEnergy.ENERGY);
                    return lazyOptional.map(e -> (float) e.getEnergyStored() / e.getMaxEnergyStored()).orElse(0.0F);
                });
            });
        }
}
