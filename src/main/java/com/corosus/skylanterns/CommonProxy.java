package com.corosus.skylanterns;

import com.corosus.skylanterns.block.BlockLit;
import com.corosus.skylanterns.item.ItemSkyLantern;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy
{

    public static final String air_lit = "air_lit";

    public static final String sky_lantern_item = "sky_lantern_item";

    @GameRegistry.ObjectHolder(SkyLanterns.MODID + ":" + air_lit)
    public static Block blockAirLit;

    @GameRegistry.ObjectHolder(SkyLanterns.MODID + ":" + sky_lantern_item)
    public static Item itemSkyLantern;
	
    public CommonProxy()
    {
    	
    }

    public void init()
    {
    	addEntity(EntitySkyLantern2.class, "sky_lantern", 0, 256, 1, true);

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        SkyLanterns.proxy.addBlock(event, (new BlockLit()), air_lit);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        SkyLanterns.proxy.addItem(event, new ItemSkyLantern(), sky_lantern_item);

        SkyLanterns.proxy.addItemBlock(event, new ItemBlock(blockAirLit).setRegistryName(blockAirLit.getRegistryName()));
    }

    public void addItem(RegistryEvent.Register<Item> event, Item item, String name) {
        item.setUnlocalizedName(SkyLanterns.MODID + "." + name);
        item.setRegistryName(SkyLanterns.MODID + ":" + name);

        item.setCreativeTab(CreativeTabs.MISC);

        event.getRegistry().register(item);
    }

    public void addItemBlock(RegistryEvent.Register<Item> event, Item item) {
        event.getRegistry().register(item);
    }

    public void addBlock(RegistryEvent.Register<Block> event, Block parBlock, String unlocalizedName) {
        parBlock.setUnlocalizedName(SkyLanterns.MODID + "." + unlocalizedName);
        parBlock.setRegistryName(SkyLanterns.MODID + ":" + unlocalizedName);

        //parBlock.setCreativeTab(CreativeTabs.MISC);
        event.getRegistry().register(parBlock);
    }
    
    public void addEntity(Class par0Class, String par1Str, int entityId, int distSync, int tickRateSync, boolean syncMotion) {
    	EntityRegistry.registerModEntity(new ResourceLocation(SkyLanterns.MODID, par1Str), par0Class, par1Str, entityId, SkyLanterns.instance, distSync, tickRateSync, syncMotion);
    }
}
