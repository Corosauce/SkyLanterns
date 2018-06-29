package com.corosus.skylanterns.config;

import modconfig.ConfigComment;
import modconfig.IConfigCategory;

public class ConfigSkyLanterns implements IConfigCategory {

	@ConfigComment("Time in ticks between checks to see if skylantern should place a new invisible light source, performance sensitive, set to -1 to make it never place invisible light blocks")
	public static int lightUpdateRate = 10;

	@ConfigComment("How far the sky lantern needs to be from the previously placed light source before it places a new one, performance sensitive")
	public static int lightUpdateDistanceAccuracy = 4;

	@ConfigComment("How close to the ground the sky lantern needs to be to place a light source, this prevents it from placing light sources high up in the sky which kills performance, very performance sensitive")
	public static int lightUpdateDistanceToGround = 6;

	@Override
	public String getName() {
		return "SkyLanterns";
	}

	@Override
	public String getRegistryName() {
		return "sky_lanterns";
	}

	@Override
	public String getConfigFileName() {
		return getName();
	}

	@Override
	public String getCategory() {
		return "sky_lanterns";
	}

	@Override
	public void hookUpdatedValues() {

	}

}
