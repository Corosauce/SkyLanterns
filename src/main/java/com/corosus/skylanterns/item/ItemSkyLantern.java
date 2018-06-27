package com.corosus.skylanterns.item;

import com.corosus.skylanterns.EntitySkyLantern2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSkyLantern extends Item {

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            if (!stack.isEmpty()) {
                stack.shrink(1);

                EntitySkyLantern2 entity = new EntitySkyLantern2(worldIn);
                entity.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
                worldIn.spawnEntity(entity);
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
