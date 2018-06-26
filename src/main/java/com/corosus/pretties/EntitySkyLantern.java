package com.corosus.pretties;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkyLantern extends Entity {

	public EntitySkyLantern(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		Random rand = this.getEntityWorld().rand;
		
		this.motionY += rand.nextDouble() * 0.01D;
		if (this.motionY > 0.15D) {
			this.motionY = 0.15D;
		}
		
		double speedAdj = 0.005D;
		if (!this.getEntityWorld().isRemote) {
			this.motionX += rand.nextDouble() * speedAdj - rand.nextDouble() * speedAdj;
			this.motionZ += rand.nextDouble() * speedAdj - rand.nextDouble() * speedAdj;
			
			if (this.motionX > 0.3D) {
				this.motionX = 0.3D;
			} else if (this.motionX < -0.3D) {
				this.motionX = -0.3D;
			}
			
			if (this.motionZ > 0.3D) {
				this.motionZ = 0.3D;
			} else if (this.motionZ < -0.3D) {
				this.motionZ = -0.3D;
			}
		}
		
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		
		if (!this.getEntityWorld().isRemote) {
			if (this.posY > 300) {
				this.setDead();
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getBrightnessForRender()
    {
        return 15728880;
    }

}
