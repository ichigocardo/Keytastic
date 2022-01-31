package com.jay_the_pea.keytastic.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab KEYTASTIC_TAB = new CreativeModeTab("keytastictab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TIER_1_BASE_ITEM.get());
        }
    };
}
