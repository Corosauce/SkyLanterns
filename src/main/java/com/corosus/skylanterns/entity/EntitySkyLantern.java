package com.corosus.skylanterns.entity;

import com.corosus.skylanterns.CommonProxy;
import com.corosus.skylanterns.config.ConfigSkyLanterns;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class EntitySkyLantern extends EntityCreature {

	public BlockPos posLight = new BlockPos(BlockPos.ORIGIN);

	public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntitySkyLantern.class, DataSerializers.VARINT);

	public EntitySkyLantern(World worldIn) {
		super(worldIn);
        this.setSize(1F, 1.0F);

        //CommandSummon is retarded and calls readFromNBT which undoes this, so just force set it elsewhere
		//this.setNoGravity(true);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.getDataManager().register(COLOR, Integer.valueOf(0));
	}

	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {

		getEntityData().setBoolean("weather2_WindAffected", true);
		getEntityData().setFloat("weather2_WindWeight", 5);

		return super.onInitialSpawn(difficulty, livingdata);
	}

	public void setColor(EnumDyeColor color) {
		this.getDataManager().set(COLOR, color.getMetadata());
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.setNoGravity(true);
		this.enablePersistence();
		
		Random rand = this.getEntityWorld().rand;

		if (this.motionY < 0.05D) {
			if (ticksExisted >= 40) {
				this.motionY += rand.nextDouble() * 0.01D;
			} else {
				this.motionY += rand.nextDouble() * 0.005D;
			}
		}

		if (!this.getEntityWorld().isRemote) {
			long time = (this.getEntityId() * 3) + world.getTotalWorldTime() * 3;
			float timeClampSpeed = (((time)) % 360) - 180;

			float tiltMax = (float)Math.toRadians(timeClampSpeed);

			float speed = 0.01F;

			if (ticksExisted < 40) {
				speed = 0.006F;
			}

			if (getLeashed()) {
				speed = 0.001F;
			}

			this.motionX += -Math.cos(tiltMax) * speed;
			this.motionZ += Math.sin(tiltMax) * speed;
		}

		this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D, this.posZ);

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

		if (ConfigSkyLanterns.lightUpdateRate != -1) {
			if (!this.getEntityWorld().isRemote && (ConfigSkyLanterns.lightUpdateRate <= 0 || world.getTotalWorldTime() % ConfigSkyLanterns.lightUpdateRate == 0)) {
				double dist = getDistanceSq(posLight);
				if (dist >= ConfigSkyLanterns.lightUpdateDistanceAccuracy || world.getBlockState(posLight).getBlock() != CommonProxy.blockAirLit) {
					//remove old if needed
					clearCurrentLightBlock();
					//set new, setting light high in the sky is a lag fest, only allow it when directly above ground
					posLight = getPosition();
					if (world.isAirBlock(posLight) && world.getHeight(posLight).getY() + ConfigSkyLanterns.lightUpdateDistanceToGround > posY) {
						world.setBlockState(posLight, CommonProxy.blockAirLit.getDefaultState());
					}
				}
			}
		} else {
			//if they changed the config, make sure to remove old light
			if (!this.getEntityWorld().isRemote) {
				clearCurrentLightBlock();
			}
		}

		if (!this.getEntityWorld().isRemote && world.getTotalWorldTime() % 20 == 0) {
			this.heal(1);
		}

		this.fallDistance = 0;
	}

	public void clearCurrentLightBlock() {
		if (!posLight.equals(BlockPos.ORIGIN)) {
			IBlockState state = world.getBlockState(posLight);
			if (state.getBlock() == CommonProxy.blockAirLit) {
				//System.out.println("set " + posLight + " to air");
				world.setBlockState(posLight, Blocks.AIR.getDefaultState());
			}
			posLight = BlockPos.ORIGIN;
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		posLight = new BlockPos(compound.getInteger("light_X"), compound.getInteger("light_Y"), compound.getInteger("light_Z"));

		this.getDataManager().set(COLOR, compound.getInteger("color"));

		if (this.getDataManager().get(COLOR) == 0) {
			this.getDataManager().set(COLOR, EnumDyeColor.ORANGE.getMetadata());
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setInteger("light_X", posLight.getX());
		compound.setInteger("light_Y", posLight.getY());
		compound.setInteger("light_Z", posLight.getZ());

		compound.setInteger("color", this.getDataManager().get(COLOR));

		//detect broken vanilla state and force set the leash nbt
		if (this.getLeashed() && getLeashedToEntity() == null) {
			NBTTagCompound tag = ReflectionHelper.getPrivateValue(EntityLiving.class, this, "field_110170_bx", "leashNBTTag");
			if (tag != null) {
				//System.out.println("writing leashNBTTag to disk for vanilla bug fix: " + tag);
				compound.setTag("Leash", tag);
			}
		}
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

			if (f > 3F)
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

	@Override
	public void setDead() {
		super.setDead();
		if (!world.isRemote) {
			clearCurrentLightBlock();
		}
	}
}
