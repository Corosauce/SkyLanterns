package com.corosus.pretties.client.entity.render;

import com.corosus.pretties.EntitySkyLantern2;
import com.corosus.pretties.Pretties;
import com.corosus.pretties.client.entity.model.ModelPaperLanternPink;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkyLantern2 extends RenderLiving<EntitySkyLantern2>
{
    /** instance of ModelBoat for rendering */
    //protected ModelBase model = new ModelPaperLanternPink();
    public static ResourceLocation TEXTURES = new ResourceLocation(Pretties.MODID + ":textures/entities/paperlanternpink.png");
    private float scale = 0.2F;

    public RenderSkyLantern2(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPaperLanternPink(), 0.5F);
        //this.model = new ModelPaperLanternPink();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntitySkyLantern2 entity)
    {
        return TEXTURES;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
    protected void preRenderCallback(EntitySkyLantern2 entitylivingbaseIn, float partialTickTime)
    {
        float scale = 0.25F;
        GlStateManager.scale(scale, scale, scale);

        long time = entitylivingbaseIn.worldObj.getTotalWorldTime();



        long timeBase = time + (entitylivingbaseIn.getEntityId() * 10);
        float rate = 5;

        float tiltMax = (float)Math.sin(Math.toRadians(((timeBase) * 1F) % 360)) * 5F;

        float tiltCurX = (float)Math.sin(Math.toRadians(((timeBase) * rate) % 360)) * tiltMax;
        float tiltCurY = (float)Math.sin(Math.toRadians(((timeBase + 45) * rate) % 360)) * tiltMax;
        float tiltCurZ = (float)Math.sin(Math.toRadians(((timeBase + 90) * rate) % 360)) * tiltMax;

        float rotateY = (((float)timeBase * 0.1F) % 360);

        //tiltCur = (float)Math.sin(Math.toRadians(90)) * tiltMax;

        GlStateManager.rotate(tiltCurX, 1, 0, 0);
        GlStateManager.rotate(tiltCurY + rotateY, 0, 1, 0);
        GlStateManager.rotate(tiltCurZ, 0, 0, 1);
    }
}