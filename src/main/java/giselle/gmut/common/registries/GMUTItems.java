package giselle.gmut.common.registries;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;

public class GMUTItems
{
	public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(GravitationalModulatingUnitTweaks.MODID);

	public static final ItemRegistryObject<ItemModule> GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT = ITEMS.registerModule(GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT);

	private GMUTItems()
	{

	}

}
