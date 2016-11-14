package com.corosus.pretties.client.entity.render;

import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.corosus.pretties.EntitySkyLantern;
import com.corosus.pretties.Pretties;
import com.corosus.pretties.client.entity.model.ModelPaperLanternPink;

@SideOnly(Side.CLIENT)
public class RenderSkyLantern extends Render<EntitySkyLantern>
{
    /** instance of ModelBoat for rendering */
    protected ModelBase model = new ModelPaperLanternPink();
    public static ResourceLocation TEXTURES = new ResourceLocation(Pretties.MODID + ":textures/entities/paperlanternpink.png");

    public RenderSkyLantern(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.5F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntitySkyLantern entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	GlStateManager.pushMatrix();
    	this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
    	this.bindEntityTexture(entity);
    	
    	float scale = 0.25F;
    	GlStateManager.scale(scale, scale, scale);
    	GlStateManager.disableLighting();
    	model.render(entity, partialTicks, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
    	GlStateManager.enableLighting();
    	GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
    {
        GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_ + 0.375F, (float)p_188309_5_);
    }
    
    public void setupRotation(EntitySkyLantern p_188311_1_, float p_188311_2_, float p_188311_3_)
    {
        GlStateManager.rotate(180.0F - p_188311_2_, 0.0F, 1.0F, 0.0F);
        float f = 0;//(float)p_188311_1_.getTimeSinceHit() - p_188311_3_;
        float f1 = 0;//p_188311_1_.getDamageTaken() - p_188311_3_;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f > 0.0F)
        {
            //GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float)p_188311_1_.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        //GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntitySkyLantern entity)
    {
        return TEXTURES;
    }
}