package com.corosus.skylanterns;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SkyLanterns.MODID, name = "SkyLanterns", version = SkyLanterns.VERSION)
public class SkyLanterns
{
	@Mod.Instance( value = "sky_lanterns" )
	public static SkyLanterns instance;
	
    public static final String MODID = "sky_lanterns";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide = "com.corosus.skylanterns.ClientProxy", serverSide = "com.corosus.skylanterns.CommonProxy")
    public static CommonProxy proxy;
    
    public static List<Block> listDegradeProgression = new ArrayList<Block>();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    }
}
