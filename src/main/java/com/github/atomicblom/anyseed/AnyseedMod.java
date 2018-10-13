package com.github.atomicblom.anyseed;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
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
