package com.jay_the_pea.keytastic.item;

import com.jay_the_pea.keytastic.Tier_Property;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;

public class Tier_1_Key extends Item {

    private static String RARITY_TIER_NBT_KEY = "Rarity_Tier";
    private static String[][] tier_chances =
            {       {"heroic","3"},
                    {"ultra_rare","4"},
                    {"rare","5"},
                    {"uncommon","8"},
                    {"common","8"}
            };

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("item.keytastic.tier_1_key.tooltip"));
        String Rarity = getRarityTier(pStack);
        //System.out.println("Rarity: " + Rarity);
        switch (Rarity){
            case"common":
                pTooltipComponents.add(new TextComponent("Common").withStyle(ChatFormatting.GRAY));
                break;

            case"uncommon":
                pTooltipComponents.add(new TextComponent("Uncommon").withStyle(ChatFormatting.GREEN));
                break;

            case"rare":
                pTooltipComponents.add(new TextComponent("Rare").withStyle(ChatFormatting.YELLOW));
                break;

            case"ultra_rare":
                pTooltipComponents.add(new TextComponent("Ultra Rare").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD));
                break;

            case"heroic":
                pTooltipComponents.add(new TextComponent("Heroic").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
                break;

            default:
                pTooltipComponents.add(new TextComponent("No Rarity."));
        }

    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        super.onCraftedBy(pStack, pLevel, pPlayer);
        if(pLevel.isClientSide){
            return;
        }
        CompoundTag tag = pStack.getOrCreateTag();
        String tier = Tier_Property.generate_Tier(tier_chances);
        System.out.println("Tier: " + tier);
        tag.putString(RARITY_TIER_NBT_KEY,tier);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            ItemStack off_hand_item = pContext.getPlayer().getItemInHand(InteractionHand.OFF_HAND);
            ItemStack main_hand_item = pContext.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            if((off_hand_item.getItem().getDescriptionId().equals("item.keytastic.tier_0_locked_book")
                    && main_hand_item.getItem().getDescriptionId().equals("item.keytastic.tier_1_key"))
                    ||(off_hand_item.getItem().getDescriptionId().equals("item.keytastic.tier_1_key")
                    && main_hand_item.getItem().getDescriptionId().equals("item.keytastic.tier_0_locked_book"))){
                pContext.getPlayer().setItemInHand(InteractionHand.MAIN_HAND, Blocks.AIR.asItem().getDefaultInstance());
                pContext.getPlayer().setItemInHand(InteractionHand.OFF_HAND, Blocks.AIR.asItem().getDefaultInstance());
                pContext.getPlayer().addItem(Common_Spellbook_Torch.createSpellbook());
            }

            return super.useOn(pContext);
        }


        return super.useOn(pContext);
    }

    public static String getRarityTier(ItemStack pStack){
        return pStack.getOrCreateTag().getString(RARITY_TIER_NBT_KEY);
    }

    public Tier_1_Key(Properties p) {
        super(p);
    }
}