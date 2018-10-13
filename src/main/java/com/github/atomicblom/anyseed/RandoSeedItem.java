package com.github.atomicblom.anyseed;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.github.atomicblom.anyseed.ModConfig.parsedSeeds;

public class RandoSeedItem extends Item
{
	RandoSeedItem()
	{
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		final ItemStack itemStack = parsedSeeds[world.rand.nextInt(parsedSeeds.length)];

		return itemStack.getItem().onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}
}
