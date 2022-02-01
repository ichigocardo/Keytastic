package com.jay_the_pea.keytastic.block;

import com.jay_the_pea.keytastic.block.custom.Illuminating_Stone;
import com.jay_the_pea.keytastic.item.ModCreativeModeTab;
import com.jay_the_pea.keytastic.item.ModItems;
import com.jay_the_pea.keytastic.keytastic;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, keytastic.MOD_ID);

    public static final RegistryObject<Block> TIER_1_ORE = registerBlock("tier_1_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(25f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.KEYTASTIC_TAB);

    public static final RegistryObject<Block> TIER_1_RICH_ORE = registerBlock("tier_1_rich_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(25f)
                    .requiresCorrectToolForDrops()), ModCreativeModeTab.KEYTASTIC_TAB);

    public static final RegistryObject<Block> TIER_1_SPELL_ITEM_ILLUMINATING_STONE =
            registerBlock("tier_1_spell_item_illuminating_stone",() ->
                    new Illuminating_Stone( BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_1_) -> {
                        return 18;
                    }).sound(SoundType.STONE)),ModCreativeModeTab.KEYTASTIC_TAB);

    private static <T extends Block>RegistryObject<T>registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                           CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
