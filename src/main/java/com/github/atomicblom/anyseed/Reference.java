package com.github.atomicblom.anyseed;

import net.minecraft.util.ResourceLocation;

public class Reference
{
	public static final String MOD_ID = "anyseed";
	public static final String MOD_NAME = "Anyseed";
	public static final String VERSION = "@MOD_VERSION@";

	public static final class Items {

		public static final ResourceLocation SEED_PACKET = resource("seed_packet");

		private Items() { }
	}



	private Reference() {}

	private static ResourceLocation resource(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
