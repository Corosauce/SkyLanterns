package com.corosus.skylanterns;

import com.corosus.skylanterns.client.entity.render.RenderSkyLantern2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
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
    
    public static void addEntityRender(Class<? extends Entity> entityClass, Render render) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}

    @Override
    public void addItem(RegistryEvent.Register<Item> event, Item item, String name) {
        super.addItem(event, item, name);

        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(SkyLanterns.MODID + ":" + name, "inventory"));
    }

    @Override
    public void addItemBlock(RegistryEvent.Register<Item> event, Item item) {
        super.addItemBlock(event, item);

        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
