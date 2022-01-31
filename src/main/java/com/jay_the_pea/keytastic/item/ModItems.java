package com.jay_the_pea.keytastic.item;

import com.jay_the_pea.keytastic.keytastic;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,keytastic.MOD_ID);
    public static Item tier_1_base_item = new Tier_1_Base_Item(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB));
    public static final RegistryObject<Item> TIER_1_BASE_ITEM = ITEMS.register("tier_1_base_item", () -> tier_1_base_item);

    public static Item tier_2_base_item = new tier_2_base_item(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(48));
    public static final RegistryObject<Item> TIER_2_BASE_ITEM = ITEMS.register("tier_2_base_item",
            () -> tier_2_base_item);

    public static Item tier_3_base_item = new tier_3_base_item(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(32));
    public static final RegistryObject<Item> TIER_3_BASE_ITEM = ITEMS.register("tier_3_base_item",() -> tier_3_base_item);

    public static Item tier_0_key = new tier_0_key(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB));
    public static final RegistryObject<Item> TIER_0_KEY = ITEMS.register("tier_0_key",() -> tier_0_key);

    public static Item tier_011_key = // 0 -> Origin Tier ; 1 -> first part Crafting ; 1 -> Crafting towards Tier
            new Item(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(1));
    public static final RegistryObject<Item> TIER_011_KEY =
            ITEMS.register("tier_011_key",() -> tier_011_key);

    public static Item tier_021_key = // 0 -> Origin Tier ; 2 -> second part Crafting ; 1 -> Crafting towards Tier
            new Item(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(1));
    public static final RegistryObject<Item> TIER_021_KEY =
            ITEMS.register("tier_021_key",() -> tier_021_key);

    public static Item tier_1_key = new Tier_1_Key(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(1));
    public static final RegistryObject<Item> TIER_1_KEY = ITEMS.register("tier_1_key",() -> tier_1_key);

    public static Item tier_0_locked_book =
            new Tier_0_locked_book(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(1));
    public static final RegistryObject<Item> TIER_0_LOCKED_BOOK = ITEMS.register("tier_0_locked_book", () -> tier_0_locked_book);


    public static Item common_spellbook_torch =
            new Common_Spellbook_Torch(new Item.Properties().tab(ModCreativeModeTab.KEYTASTIC_TAB).stacksTo(1));
    public static final RegistryObject<Item> COMMON_SPELLBOOK_TORCH = ITEMS.register("common_spellbook_torch", () -> common_spellbook_torch);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
