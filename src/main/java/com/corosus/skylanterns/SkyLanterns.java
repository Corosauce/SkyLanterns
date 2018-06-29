package com.corosus.skylanterns;

import java.util.ArrayList;
import java.util.List;

import com.corosus.skylanterns.config.ConfigSkyLanterns;
import modconfig.ConfigMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SkyLanterns.MODID, name = "SkyLanterns", version = SkyLanterns.VERSION)
public class SkyLanterns
{
	@Mod.Instance( value = "sky_lanterns" )
	public static SkyLanterns instance;
	
    public static final String MODID = "sky_lanterns";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide = "com.corosus.skylanterns.ClientProxy", serverSide = "com.corosus.skylanterns.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigMod.addConfigFile(event, new ConfigSkyLanterns());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        ResourceLocation group = new ResourceLocation(SkyLanterns.MODID, "sky_lanterns_misc");

        GameRegistry.addShapedRecipe(new ResourceLocation(SkyLanterns.MODID, CommonProxy.sky_lantern_item_orange), group,
                new ItemStack(CommonProxy.itemSkyLanternOrange, 1),
                new Object[] {"   ", " I ", "XDX", 'D', Blocks.TORCH, 'I', new ItemStack(Blocks.WOOL, 1, EnumDyeColor.ORANGE.getMetadata()), 'X', Items.STICK});

        GameRegistry.addShapedRecipe(new ResourceLocation(SkyLanterns.MODID, CommonProxy.sky_lantern_item_pink), group,
                new ItemStack(CommonProxy.itemSkyLanternPink, 1),
                new Object[] {"   ", " I ", "XDX", 'D', Blocks.TORCH, 'I', new ItemStack(Blocks.WOOL, 1, EnumDyeColor.PINK.getMetadata()), 'X', Items.STICK});
    }
}
