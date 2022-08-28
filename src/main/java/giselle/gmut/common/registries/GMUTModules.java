package giselle.gmut.common.registries;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.item.Rarity;

public class GMUTModules
{
	public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(GravitationalModulatingUnitTweaks.MODID);

	public static final ModuleRegistryObject<ModuleGravitationalModulatingAdditionalUnit> GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT = MODULES.register("gravitational_modulating_additional_unit", ModuleGravitationalModulatingAdditionalUnit::new, () -> GMUTItems.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.asItem(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));

	private GMUTModules()
	{

	}

}
