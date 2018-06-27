package com.corosus.skylanterns.client.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Scale it to 25%
 * 
 * @author Corosus
 *
 */
public class ModelPaperLanternPink extends ModelBase
{
	ModelRenderer Bottom;
	ModelRenderer Top2;
	ModelRenderer Top3;
	ModelRenderer Top;
	ModelRenderer Top21;
	ModelRenderer Top31;
	ModelRenderer Front;
	ModelRenderer Left;
	ModelRenderer Right;
	ModelRenderer Back;

	public ModelPaperLanternPink()
	{
		Bottom = new ModelRenderer( this, 57, 67 );
		Bottom.setTextureSize( 256, 256 );
		Bottom.addBox( -24F, -0.5F, -24F, 48, 1, 48);
		Bottom.setRotationPoint( 0F, 21F, 0F );
		Top2 = new ModelRenderer( this, 65, 116 );
		Top2.setTextureSize( 256, 256 );
		Top2.addBox( -22F, -0.5F, -22F, 44, 1, 44);
		Top2.setRotationPoint( 0F, 22F, 0F );
		Top3 = new ModelRenderer( this, 73, 161 );
		Top3.setTextureSize( 256, 256 );
		Top3.addBox( -20F, -0.5F, -20F, 40, 1, 40);
		Top3.setRotationPoint( 0F, 23F, 0F );
		Top = new ModelRenderer( this, 57, 15 );
		Top.setTextureSize( 256, 256 );
		Top.addBox( -24F, -0.5F, -24F, 48, 1, 48);
		Top.setRotationPoint( 0F, -36F, 0F );
		Top21 = new ModelRenderer( this, 65, 20 );
		Top21.setTextureSize( 256, 256 );
		Top21.addBox( -22F, -0.5F, -22F, 44, 1, 44);
		Top21.setRotationPoint( 0F, -37F, 0F );
		Top31 = new ModelRenderer( this, 73, 23 );
		Top31.setTextureSize( 256, 256 );
		Top31.addBox( -20F, -0.5F, -20F, 40, 1, 40);
		Top31.setRotationPoint( 0F, -38F, 0F );
		Front = new ModelRenderer( this, 0, 0 );
		Front.setTextureSize( 256, 256 );
		Front.addBox( -24F, -28F, -0.5F, 48, 56, 1);
		Front.setRotationPoint( 0F, -7.5F, -24F );
		Left = new ModelRenderer( this, 0, 0 );
		Left.setTextureSize( 256, 256 );
		Left.addBox( -24F, -28F, -0.5F, 48, 56, 1);
		Left.setRotationPoint( -24F, -7.5F, 0F );
		Right = new ModelRenderer( this, 0, 0 );
		Right.setTextureSize( 256, 256 );
		Right.addBox( -24F, -28F, -0.5F, 48, 56, 1);
		Right.setRotationPoint( 24F, -7.5F, 0F );
		Back = new ModelRenderer( this, 0, 0 );
		Back.setTextureSize( 256, 256 );
		Back.addBox( -24F, -28F, -0.5F, 48, 56, 1);
		Back.setRotationPoint( 0F, -7.5F, 24F );
	}

	@Override
	public void render(Entity par1Entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		Bottom.rotateAngleX = 0F;
		Bottom.rotateAngleY = 0F;
		Bottom.rotateAngleZ = 0F;
		Bottom.renderWithRotation(scale);

		Top2.rotateAngleX = 0F;
		Top2.rotateAngleY = 0F;
		Top2.rotateAngleZ = 0F;
		Top2.renderWithRotation(scale);

		Top3.rotateAngleX = 0F;
		Top3.rotateAngleY = 0F;
		Top3.rotateAngleZ = 0F;
		Top3.renderWithRotation(scale);

		Top.rotateAngleX = 0F;
		Top.rotateAngleY = 0F;
		Top.rotateAngleZ = 0F;
		Top.renderWithRotation(scale);

		Top21.rotateAngleX = 0F;
		Top21.rotateAngleY = 0F;
		Top21.rotateAngleZ = 0F;
		Top21.renderWithRotation(scale);

		Top31.rotateAngleX = 0F;
		Top31.rotateAngleY = 0F;
		Top31.rotateAngleZ = 0F;
		Top31.renderWithRotation(scale);

		Front.rotateAngleX = 0F;
		Front.rotateAngleY = 0F;
		Front.rotateAngleZ = 0F;
		Front.renderWithRotation(scale);

		Left.rotateAngleX = 0F;
		Left.rotateAngleY = -1.570796F;
		Left.rotateAngleZ = 0F;
		Left.renderWithRotation(scale);

		Right.rotateAngleX = 0F;
		Right.rotateAngleY = -1.570796F;
		Right.rotateAngleZ = 0F;
		Right.renderWithRotation(scale);

		Back.rotateAngleX = 0F;
		Back.rotateAngleY = 0F;
		Back.rotateAngleZ = 0F;
		Back.renderWithRotation(scale);

	}

}
