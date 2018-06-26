package com.corosus.pretties;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	
    public CommonProxy()
    {
    	
    }

    public void init()
    {
    	addEntity(EntitySkyLantern2.class, "sky_lantern", 0, 256, 1, true);
    }
    
    /*public void addBlock(Block parBlock, String unlocalizedName, String blockNameBase) {
		GameRegistry.registerBlock(parBlock, unlocalizedName);
		parBlock.setUnlocalizedName(getNamePrefixed(unlocalizedName));
	}*/
    
    public static String getNamePrefixed(String name) {
    	return Pretties.MODID + "." + name;
    }
    
    public void addEntity(Class par0Class, String par1Str, int entityId, int distSync, int tickRateSync, boolean syncMotion) {
    	EntityRegistry.registerModEntity(new ResourceLocation(Pretties.MODID, par1Str), par0Class, par1Str, entityId, Pretties.instance, distSync, tickRateSync, syncMotion);
    }
}
