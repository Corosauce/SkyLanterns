package com.corosus.skylanterns.block;

import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLit extends BlockAir {

    public BlockLit() {
        setLightLevel(1);
        //TEMP
        //setTickRandomly(true);
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        super.randomTick(worldIn, pos, state, random);
        //TEMP
        if (!worldIn.isRemote) {
            //worldIn.setBlockToAir(pos);
        }
    }
}
