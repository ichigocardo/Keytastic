package com.jay_the_pea.keytastic.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class tier_3_base_item extends Item {
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("item.keytastic.tier_3_base_item.tooltip"));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    public tier_3_base_item(Properties p) {
        super(p);
    }
}
