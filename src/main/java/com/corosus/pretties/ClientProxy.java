package com.corosus.pretties;

import com.corosus.pretties.client.entity.render.RenderSkyLantern;

import com.corosus.pretties.client.entity.render.RenderSkyLantern2;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{

    public static TextureAtlasSprite radianLight;

    public ClientProxy()
    {
    }

    @Override
    public void init()
    {
    	super.init();
    	
    	//addEntityRender(EntitySkyLantern.class, new RenderSkyLantern(Minecraft.getMinecraft().getRenderManager()));
        addEntityRender(EntitySkyLantern2.class, new RenderSkyLantern2(Minecraft.getMinecraft().getRenderManager()));
    }
    
    /*@Override
    public void addBlock(Block parBlock, String unlocalizedName, String blockNameBase) {
    	super.addBlock(parBlock, unlocalizedName, blockNameBase);
    	
    	registerItem(Item.getItemFromBlock(parBlock), 0, new ModelResourceLocation(Pretties.MODID + ":" + unlocalizedName, "inventory"));
    }
    
    public void registerItem(Item item, int meta, ModelResourceLocation location) {
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, location);
    }*/
    
    public static void addEntityRender(Class<? extends Entity> entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}
}
