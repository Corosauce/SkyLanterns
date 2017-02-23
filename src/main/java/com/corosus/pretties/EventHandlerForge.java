package com.corosus.pretties;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandlerForge {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureStitchEvent.Pre event) {

        //optifine breaks (removes) forge added method setTextureEntry, dont use it

        ClientProxy.radianLight = event.getMap().registerSprite(new ResourceLocation(Pretties.MODID + ":entities/radiant_light"));

    }
	
	
}
