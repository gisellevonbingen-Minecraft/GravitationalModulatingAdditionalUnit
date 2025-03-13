package giselle.gmut.common;

import mekanism.api.gear.IModuleHelper;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registries.MekanismModules;
import net.minecraft.world.item.ItemStack;

public class GMUTCommonPlayerTickHandler
{
	public static boolean isGravitationalModulationReady(ItemStack stack)
	{
		var module = IModuleHelper.INSTANCE.getIfEnabled(stack, MekanismModules.GRAVITATIONAL_MODULATING_UNIT);
		return module != null && module.hasEnoughEnergy(stack, MekanismConfig.gear.mekaSuitEnergyUsageGravitationalModulation);
	}

	private GMUTCommonPlayerTickHandler()
	{

	}

}
