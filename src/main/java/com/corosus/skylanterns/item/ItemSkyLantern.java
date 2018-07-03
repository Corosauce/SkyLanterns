package com.corosus.skylanterns.item;

import com.corosus.skylanterns.entity.EntitySkyLantern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSkyLantern extends Item {

    public EnumDyeColor color = EnumDyeColor.ORANGE;

    public ItemSkyLantern(EnumDyeColor color) {
        this.color = color;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {



        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            if (!stack.isEmpty()) {
                stack.shrink(1);
                playerIn.swingArm(handIn);

                EntitySkyLantern entity = new EntitySkyLantern(worldIn);
                entity.setPosition(pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ);
                entity.setColor(color);
                entity.onInitialSpawn(entity.world.getDifficultyForLocation(new BlockPos(entity)), null);
                worldIn.spawnEntity(entity);

                return EnumActionResult.SUCCESS;
            }
        }

        return super.onItemUse(playerIn, worldIn, pos, handIn, facing, hitX, hitY, hitZ);
    }
}
