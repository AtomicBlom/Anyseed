package com.github.atomicblom.anyseed;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import javax.annotation.Nonnull;

@SuppressWarnings({"ConstantConditions", "AssignmentToNull"})
@ObjectHolder(Reference.MOD_ID)
public final class ItemLibrary
{
	@Nonnull
	public static final RandoSeedItem seed_packet;

	static {
		seed_packet = null;
	}
}
