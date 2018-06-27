package com.corosus.skylanterns;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class EventHandlerForge {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerIcons(TextureStitchEvent.Pre event) {

        //optifine breaks (removes) forge added method setTextureEntry, dont use it

        ClientProxy.radianLight = event.getMap().registerSprite(new ResourceLocation(SkyLanterns.MODID + ":entities/radiant_light"));

    }
	
	
}
