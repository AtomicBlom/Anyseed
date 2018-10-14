package com.github.atomicblom.anyseed;

import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = "[1.12.2, 1.13)")
public class AnyseedMod
{
	/**
	 * This is the instance of your mod as created by Forge. It will never be null.
	 */
	@Instance(Reference.MOD_ID)
	public static AnyseedMod INSTANCE;
	static Logger logger;

	public static final String CI_BUILD = "@CI_BUILD@";
	public static ReleaseType getReleaseType() {
		try {
			return Enum.valueOf(ReleaseType.class, CI_BUILD.toUpperCase());
		} catch (Exception e)
		{
			return ReleaseType.PRIVATE;
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		switch (getReleaseType()) {
			case BETA:
				Log.REGISTRATION.info("You are running a pre-release build of Anyseed. This message is purely for informational purposes.");
				break;
			case PRIVATE:
				Log.REGISTRATION.warning("You are running a private build of Anyseed. This message is purely for informational purposes.");
				break;
		}

		ModConfig.parse();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLootTablesLoaded(LootTableLoadEvent event) {

		if (!ModConfig.addLoot) return;

		ResourceLocation name = event.getName();
		if (name.equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {

			final LootPool simpleDungeonPool1 = event.getTable().getPool("pool1");
			if (simpleDungeonPool1 != null) {
				simpleDungeonPool1.addEntry(new LootEntryItem(ItemLibrary.seed_packet, 20, 0, new LootFunction[0], new LootCondition[0], "loottable:cookie"));
			}
		} else if (name.equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)) {
			final LootPool mineshaftPool1 = event.getTable().getPool("pool1");
			if (mineshaftPool1 != null) {
				mineshaftPool1.addEntry(new LootEntryItem(ItemLibrary.seed_packet, 20, 0, new LootFunction[0], new LootCondition[0], "loottable:cookie"));
			}
		} else if (name.equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
			final LootPool desertPyramidMain = event.getTable().getPool("main");
			if (desertPyramidMain != null) {
				desertPyramidMain.addEntry(new LootEntryItem(ItemLibrary.seed_packet, 20, 0, new LootFunction[0], new LootCondition[0], "loottable:cookie"));
			}
		} else if (name.equals(LootTableList.CHESTS_SPAWN_BONUS_CHEST)) {
			final LootPool spawnChestPool2 = event.getTable().getPool("pool2");
			if (spawnChestPool2 != null) {
				spawnChestPool2.addEntry(new LootEntryItem(ItemLibrary.seed_packet, 20, 0, new LootFunction[0], new LootCondition[0], "loottable:cookie"));
			}
		}
	}

	@SubscribeEvent
	public static void onConfigChanged(OnConfigChangedEvent event) {
		if (event.getModID().equals(Reference.MOD_ID)) {
			ConfigManager.sync(Reference.MOD_ID, Type.INSTANCE);

			ModConfig.parse();
		}
	}

	public enum ReleaseType {
		RELEASE,
		BETA,
		PRIVATE
	}
}
