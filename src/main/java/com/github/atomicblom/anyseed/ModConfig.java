package com.github.atomicblom.anyseed;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Ignore;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Config(modid = Reference.MOD_ID)
public class ModConfig
{
	@Ignore
	private static final String config = Reference.MOD_ID + ".config.";

	@LangKey(config + "seeds")
	@Comment("Registry String IDs of items that will be available in the seed_packet bag. (domain:regname:meta$chances) meta and chances are optional")
	public static String[] seeds = {
			"minecraft:wheat_seeds$1",
			"minecraft:beetroot_seeds$1",
			"minecraft:melon_seeds$1",
			"minecraft:pumpkin_seeds$1",
			"minecraft:carrot$1",
			"minecraft:potato$1"
	};

	@LangKey(config + "add_loot")
	@Comment("Add loot to dungeons")
	public static boolean addLoot = true;

	@Ignore
	public static ItemStack[] parsedSeeds;

	public static void parse()
	{
		final List<ItemStack> stacks = new ArrayList<>(60);

		for (final String s : seeds) {

			final String[] chanceSplit = s.split("\\$");

			final String[] itemSplit = chanceSplit[0].split(":");
			if (itemSplit.length < 2) continue;

			final ResourceLocation resourceLocation = new ResourceLocation(itemSplit[0], itemSplit[1]);

			final Item item = ForgeRegistries.ITEMS.getValue(resourceLocation);
			if (item == null) {
				Log.REGISTRATION.warning("Could not match item of name {}", resourceLocation);
				continue;
			}

			if (!(item instanceof IPlantable)) {
				Log.REGISTRATION.warning("Item {} does not implement IPlantable and cannot be used", resourceLocation);
				continue;
			}

			int meta = OreDictionary.WILDCARD_VALUE;
			if (itemSplit.length > 2) {
				try {
					meta = Integer.parseInt(itemSplit[2]);
				} catch (final NumberFormatException e) {
					continue;
				}
			}

			int chances = 1;

			try {
				if (chanceSplit.length > 1) {
					chances = Integer.parseInt(chanceSplit[1]);

					if (chances <= 0) {
						Log.REGISTRATION.warning("Item {} requested chances <= 0", resourceLocation);
						continue;
					}
				}
			} catch (final NumberFormatException e) {
				Log.REGISTRATION.warning("Error parsing chances for {}", resourceLocation);
				continue;
			}

			for (int i = 0; i < chances; ++i)
			{
				stacks.add(new ItemStack(item, 1, meta));
			}
		}

		Log.REGISTRATION.info("Randoseed contains {} items", stacks.size());

		parsedSeeds = stacks.toArray(new ItemStack[0]);
	}
}
