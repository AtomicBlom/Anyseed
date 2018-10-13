package com.github.atomicblom.anyseed;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public final class RenderingReadyHandler
{
	@SubscribeEvent
	public static void onRenderingReady(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(ItemLibrary.seed_packet, 0, new ModelResourceLocation(Reference.Items.SEED_PACKET, "inventory"));
	}
}
