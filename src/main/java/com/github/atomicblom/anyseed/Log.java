package com.github.atomicblom.anyseed;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;

public enum Log
{
	REGISTRATION(true);

	private boolean enabled;

	Log(boolean enabled)
	{
		logger = AnyseedMod.logger;
		this.enabled = enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@SuppressWarnings({"NonSerializableFieldInSerializableClass", "InstanceVariableMayNotBeInitialized"})
	private final Logger logger;

	public void info(final String format, final Object... args)
	{
		if (enabled) logger.log(Level.INFO, format, args);
	}

	public void severe(final Throwable error, final String format, final Object... args)
	{
		//Always log severe
		logger.log(Level.ERROR, new FormattedMessage(format, args), error);
	}

	public void severe(final String format, final Object... args)
	{
		//Always log severe
		logger.log(Level.ERROR, format, args);
	}

	public void warning(final String format, final Object... args)
	{
		//Always log warnings
		logger.log(Level.WARN, format, args);
	}

	public void trace(final String format, final Object... args)
	{
		if (enabled) logger.log(Level.TRACE, format, args);
	}
}
