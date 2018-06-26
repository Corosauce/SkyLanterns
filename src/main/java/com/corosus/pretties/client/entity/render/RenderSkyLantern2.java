package com.corosus.pretties.client.entity.render;

import com.corosus.pretties.ClientProxy;
import com.corosus.pretties.EntitySkyLantern2;
import com.corosus.pretties.Pretties;
import com.corosus.pretties.client.entity.model.ModelPaperLanternPink;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

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

        long time = entitylivingbaseIn.world.getTotalWorldTime();



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

    @Override
    public void doRender(EntitySkyLantern2 entity, double x, double y, double z, float entityYaw, float partialTicks) {

        //GlStateManager.disableLighting();

        /*int i = 15728880;//entitylivingbaseIn.getBrightnessForRender(1);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);*/

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        //GlStateManager.enableLighting();

        renderTexture(x, y + 0.435D, z, 32, 32, Minecraft.getMinecraft().getRenderManager().playerViewY, ClientProxy.radianLight);
    }

    @Override
    protected void applyRotations(EntitySkyLantern2 entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks) {
        //super.rotateCorpse(entityLiving, p_77043_2_, p_77043_3_, partialTicks);

        GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);

        if (entityLiving.deathTime > 0)
        {
            float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f = MathHelper.sqrt(f);

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            GlStateManager.rotate(f * 700F/*this.getDeathMaxRotation(entityLiving)*/, 0.0F, 1.0F, 0.0F);
        }
    }

    @Override
    protected void renderLeash(EntitySkyLantern2 entityLivingIn, double x, double y, double z, float entityYaw, float partialTicks) {
        super.renderLeash(entityLivingIn, x, y - (entityLivingIn.height * 1.1D), z, entityYaw, partialTicks);
    }

    public void renderTexture(double x, double y, double z, int width, int height, float angle, TextureAtlasSprite parIcon) {
        float f6 = parIcon.getMinU();
        float f7 = parIcon.getMaxU();
        float f9 = parIcon.getMinV();
        float f8 = parIcon.getMaxV();

        float scale = 0.022F;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-angle, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-scale, -scale, -scale);

        int borderSize = 0;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();

        //GlStateManager.disableFog();

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);

        GlStateManager.disableLighting();

        //Minecraft.getMinecraft().entityRenderer.enableLightmap();

        /*int i = 15728880;
        //i = (new Random()).nextInt(99999999);
        int j = i >> 16 & 65535;
        int k = i & 65535;*/

        /*int i = 15728880;//entitylivingbaseIn.getBrightnessForRender(1);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);*/

        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        //worldrenderer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

        float r = 1F;
        float g = 1F;
        float b = 1F;

        worldrenderer
                .pos((double)(-width / 2 - borderSize), (double)(-borderSize), 0.0D)
                .tex(f6, f9)
                .color(r, g, b, 1.0F)
                //.lightmap(j, k)
                .endVertex();

        worldrenderer
                .pos((double)(-width / 2 - borderSize), (double)(height), 0.0D)
                .tex(f6, f8)
                .color(r, g, b, 1.0F)
                //.lightmap(j, k)
                .endVertex();

        worldrenderer
                .pos((double)(width / 2 + borderSize), (double)(height), 0.0D)
                .tex(f7, f8)
                .color(r, g, b, 1.0F)
                //.lightmap(j, k)
                .endVertex();

        worldrenderer
                .pos((double)(width / 2 + borderSize), (double)(-borderSize), 0.0D)
                .tex(f7, f9)
                .color(r, g, b, 1.0F)
                //.lightmap(j, k)
                .endVertex();

        tessellator.draw();

        GlStateManager.enableLighting();

        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);

        GL11.glPopMatrix();
    }
}