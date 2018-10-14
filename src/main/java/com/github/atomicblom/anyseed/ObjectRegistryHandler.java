package com.github.atomicblom.anyseed;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper
 * time.
 */
@EventBusSubscriber
public final class ObjectRegistryHandler
{
	/**
	 * Listen for the register event for creating custom items
	 */
	@SubscribeEvent
	public static void addItems(Register<Item> event)
	{
		event.getRegistry().register(
	            new SeedPacketItem()
			            .setRegistryName(Reference.Items.SEED_PACKET)
			            .setTranslationKey(Reference.Items.SEED_PACKET.getPath())
	                    .setCreativeTab(CreativeTabs.FOOD)
		);
	}
}
