package com.jay_the_pea.keytastic.item;

import com.jay_the_pea.keytastic.Tier_Property;
import com.jay_the_pea.keytastic.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Common_Spellbook_Torch extends Item {
    private static String SPELL_1_NBT_KEY = "spell_1";
    private static String SPELL_2_NBT_KEY = "spell_2";
    private static String SPELL_3_NBT_KEY = "spell_3";
    private static String SELECTED_SPELL_NBT_KEY = "selected_spell";

    private static int[] TORCH_AMOUNT = {4,8,16,32,64};
    private static int[] TORCH_COST = {15,10,8,5,5};
    private static int[] TORCH_COOLDOWN = {300,300,400,400,500};

    private static int[] LIGHT_STONE_AMOUNT = {1,2,4,8,16};
    private static int[] LIGHT_STONE_COST = {50,45,40,35,20};
    private static int[] LIGHT_STONE_COOLDOWN = {500,400,300,200,100};



    private static String[][] spell_chances = {{"torches","15"},{"illuminating_stone","7"},{"light_aura","3"}};

    public Common_Spellbook_Torch(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("item.keytastic.common_spellbook_torch.tooltip"));
        int spell_counter = 0;
        for(String spell : getSpells(pStack)){
            if(spell.contains("torches")){
                String spell_level = spell.substring(spell.length()-1);
                pTooltipComponents.add(new TextComponent("Spell: Manifest Torches Lvl: " + spell_level));
                spell_counter++;
            }
            if(spell.contains("illuminating_stone")){
                String spell_level = spell.substring(spell.length()-1);
                pTooltipComponents.add(new TextComponent("Spell: Manifest Illuminating Stone Lvl: " + spell_level));
                spell_counter++;
            }
            if(spell.contains("light_aura")){
                String spell_level = spell.substring(spell.length()-1);
                pTooltipComponents.add(new TextComponent("Aura: Light Lvl: " + spell_level));
                spell_counter++;
            }
        }
        if(spell_counter == 3){
            pTooltipComponents.add(new TextComponent("Spells known: 3/3").withStyle(ChatFormatting.RED));
        }else{
            pTooltipComponents.add(new TextComponent("Spells known: " + spell_counter + "/3"));
        }
    }

    public static ItemStack createSpellbook(){
        ItemStack spellbook = ModItems.COMMON_SPELLBOOK_TORCH.get().getDefaultInstance();
        CompoundTag tag = spellbook.getOrCreateTag();
        tag.putString(SPELL_1_NBT_KEY, Tier_Property.generate_Tier(spell_chances) + ":1");
        tag.putString(SELECTED_SPELL_NBT_KEY,"1");
        return spellbook;
    }

    public static List<String> getSpells(ItemStack pStack){
        List<String> result = new ArrayList<String>();
        CompoundTag tag = pStack.getOrCreateTag();
        for(String key : tag.getAllKeys()){
            if(!tag.getString(key).equals("") &&
                    (key.equals(SPELL_1_NBT_KEY)
                    || key.equals(SPELL_2_NBT_KEY)
                    || key.equals(SPELL_3_NBT_KEY)
                    )){
                result.add(tag.getString(key));
            }
        }
        return result;
    }


    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(!pLevel.isClientSide()){

        }
        if(pStack.getDescriptionId().equals("item.keytastic.common_spellbook_torch")){
            if(pStack.getDamageValue() > 1){
                pStack.setDamageValue(pStack.getDamageValue()-1);
            }
        }
    }

    public static void cast_torches(ItemStack book , Player pPlayer , int amount , int cost_per_item , int cast_time){
        System.out.println("cast_torches invoked");
        if(cast_time > (book.getMaxDamage() - book.getDamageValue())) {
            pPlayer.sendMessage(new TextComponent("Spell on Cooldown."),pPlayer.getUUID());
            return;
        }else{
            book.setDamageValue(cast_time);
            System.out.println("Increased Couldown by: " + cast_time);
            pPlayer.sendMessage(new TextComponent("Manifesting Torches."),pPlayer.getUUID());
            ItemStack torch = new ItemStack(Items.TORCH);
            torch.setCount(amount);
            pPlayer.addItem(torch);
        }
    }

    public static void cast_illuminating_stone(ItemStack book , Player pPlayer , int amount , int cost_per_item , int cast_time){
        System.out.println("cast_illuminating_stone invoked");
        if(cast_time > (book.getMaxDamage() - book.getDamageValue())) {
            pPlayer.sendMessage(new TextComponent("Spell on Cooldown."),pPlayer.getUUID());
            return;
        }else{
            book.setDamageValue(cast_time);
            System.out.println("Increased Couldown by: " + cast_time);
            pPlayer.sendMessage(new TextComponent("Manifesting Illuminating Stones."),pPlayer.getUUID());
            ItemStack illuminating_stones = new ItemStack(ModBlocks.TIER_1_SPELL_ITEM_ILLUMINATING_STONE.get());
            illuminating_stones.setCount(amount);
            pPlayer.addItem(illuminating_stones);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        String selected_spell;
        String spell = "";
        int spell_level = 0;
        ItemStack usedItem = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide && (usedItem.getDescriptionId().equals("item.keytastic.common_spellbook_torch"))) {
            selected_spell = usedItem.getOrCreateTag().getString(SELECTED_SPELL_NBT_KEY);
            spell = usedItem.getOrCreateTag().getString(("spell_"+selected_spell));
            spell_level = Integer.parseInt(spell.substring(spell.length()-1));
            cast_a_spell(pPlayer,usedItem,spell,spell_level-1);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private static void cast_a_spell(Player pPlayer, ItemStack pStack, String spell, int lvl){
        if(spell.contains("torches")){
            cast_torches(pStack, pPlayer, TORCH_AMOUNT[lvl],TORCH_COST[lvl],TORCH_COOLDOWN[lvl]);
        }
        if(spell.contains("illuminating_stone")){
            cast_illuminating_stone(pStack, pPlayer,LIGHT_STONE_AMOUNT[lvl], LIGHT_STONE_COST[lvl],LIGHT_STONE_COOLDOWN[lvl] );
        }
        if(spell.contains("light_aura")){
            //TODO: implement cast light Aura
        }
    }
}
