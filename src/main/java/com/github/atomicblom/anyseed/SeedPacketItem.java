package com.github.atomicblom.anyseed;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import static com.github.atomicblom.anyseed.ModConfig.parsedSeeds;

public class SeedPacketItem extends Item
{
	SeedPacketItem()
	{
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		final ItemStack itemStack = parsedSeeds[world.rand.nextInt(parsedSeeds.length)];

		IBlockState blockBeneath = world.getBlockState(pos.down());

		EnumActionResult result = itemStack.getItem().onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
		if (result == EnumActionResult.SUCCESS) {
			if (world.isRemote) {
				//Override block to air
				//disabled because it causes flickering.
				//world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
				//world.setBlockState(pos.down(), blockBeneath, 4);

				Minecraft.getMinecraft().renderGlobal.spawnParticle(
						EnumParticleTypes.BLOCK_CRACK.getParticleID(),
						false,
						pos.getX() + (player.getRNG().nextFloat() - 0.5D),
						pos.getY() + 0.1D,
						pos.getZ() + (player.getRNG().nextFloat() - 0.5D),
						4.0D,
						1.5D,
						4.0D,
						Block.getStateId(blockBeneath)
				);
			} else {
				((WorldServer)world).spawnParticle(EnumParticleTypes.BLOCK_DUST, (double)pos.getX() + 0.5d, (double)pos.getY() + 1.0d, (double)pos.getZ() + 0.5d, 32, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, Block.getStateId(blockBeneath));
			}
		}
		return result;
	}
}
