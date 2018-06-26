package com.corosus.pretties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class EntitySkyLantern2 extends EntityCreature {

	public EntitySkyLantern2(World worldIn) {
		super(worldIn);
        this.setSize(1F, 1.0F);
		//this.moveHelper = new EntitySkyLantern2.FlyingMoveHelper(this);

		//setNoGravity
		this.setNoGravity(true);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		//this.setSize(1F, 1.0F);
		//this.motionY = 0;
		
		Random rand = this.getEntityWorld().rand;
		
		this.motionY += rand.nextDouble() * 0.01D;
		if (this.motionY > 0.05D) {
			this.motionY = 0.05D;
		}
		
		double speedAdj = 0.005D;
		if (!this.getEntityWorld().isRemote) {
			/*this.motionX += rand.nextDouble() * speedAdj - rand.nextDouble() * speedAdj;
			this.motionZ += rand.nextDouble() * speedAdj - rand.nextDouble() * speedAdj;*/

			long time = (this.getEntityId() * 3) + world.getTotalWorldTime() * 3;
			float timeClampSpeed = (((time)) % 360) - 180;

			float tiltMax = (float)Math.toRadians(timeClampSpeed);

			float speed = 0.01F;

			//System.out.println(Math.cos(tiltMax)/* * speed*/);
			//System.out.println(timeClampSpeed);

			this.motionX += -Math.cos(tiltMax) * speed;
			this.motionZ += Math.sin(tiltMax) * speed;

			/*if (this.motionX > 0.3D) {
				this.motionX = 0.3D;
			} else if (this.motionX < -0.3D) {
				this.motionX = -0.3D;
			}
			
			if (this.motionZ > 0.3D) {
				this.motionZ = 0.3D;
			} else if (this.motionZ < -0.3D) {
				this.motionZ = -0.3D;
			}*/
		}
		
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if (!this.getEntityWorld().isRemote) {
			if (this.posY > 300) {
				this.setDead();
			}
		} else {
			if (world.rand.nextInt(5) == 0) {
				double d0 = posX;// + 0.5D;
				double d1 = posY + 0.15D;
				double d2 = posZ;// + 0.5D;
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
				world.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		this.fallDistance = 0;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender()
	{
		return 15728880;
	}

	/**
	 * Gets how bright this entity is.
	 */
	@Override
	public float getBrightness()
	{
		return 1.0F;
	}

	static class FlyingMoveHelper extends EntityMoveHelper
	{
		private final EntitySkyLantern2 parentEntity;
		private int courseChangeCooldown;

		public FlyingMoveHelper(EntitySkyLantern2 ghast)
		{
			super(ghast);
			this.parentEntity = ghast;
		}

		public void onUpdateMoveHelper()
		{
			if (this.action == EntityMoveHelper.Action.MOVE_TO)
			{
				double d0 = this.posX - this.parentEntity.posX;
				double d1 = this.posY - this.parentEntity.posY;
				double d2 = this.posZ - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;

				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					d3 = (double) MathHelper.sqrt(d3);

					if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
					{
						this.parentEntity.motionX += d0 / d3 * 0.1D;
						this.parentEntity.motionY += d1 / d3 * 0.1D;
						this.parentEntity.motionZ += d2 / d3 * 0.1D;
					}
					else
					{
						this.action = EntityMoveHelper.Action.WAIT;
					}
				}
			}
		}

		/**
		 * Checks if entity bounding box is not colliding with terrain
		 */
		private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
		{
			double d0 = (x - this.parentEntity.posX) / p_179926_7_;
			double d1 = (y - this.parentEntity.posY) / p_179926_7_;
			double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
			AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

			for (int i = 1; (double)i < p_179926_7_; ++i)
			{
				axisalignedbb = axisalignedbb.offset(d0, d1, d2);

				if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
				{
					return false;
				}
			}

			return true;
		}
	}

	@Override
	public float getEyeHeight() {
		return super.getEyeHeight() - 0.5F;
	}

	protected void updateLeashedState()
	{
		super.updateLeashedState();

		if (this.getLeashed() && this.getLeashedToEntity() != null && this.getLeashedToEntity().world == this.world)
		{
			Entity entity = this.getLeashedToEntity();
			this.setHomePosAndDistance(new BlockPos((int)entity.posX, (int)entity.posY, (int)entity.posZ), 5);
			float f = this.getDistanceToEntity(entity);

			if (f > 5F)
			{
				double d0 = (entity.posX - this.posX) / (double)f;
				double d1 = (entity.posY - this.posY) / (double)f;
				double d2 = (entity.posZ - this.posZ) / (double)f;
				this.motionX += d0 * Math.abs(d0) * 0.1D;
				this.motionY += d1 * Math.abs(d1) * 0.03D;
				this.motionZ += d2 * Math.abs(d2) * 0.1D;
			}
		}
	}
}
