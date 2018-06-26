package com.corosus.pretties;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Pretties.MODID, name = "Pretties", version = Pretties.VERSION)
public class Pretties
{
	@Mod.Instance( value = "pretties" )
	public static Pretties instance;
	
    public static final String MODID = "pretties";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide = "com.corosus.pretties.ClientProxy", serverSide = "com.corosus.pretties.CommonProxy")
    public static CommonProxy proxy;
    
    public static List<Block> listDegradeProgression = new ArrayList<Block>();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    }
}
