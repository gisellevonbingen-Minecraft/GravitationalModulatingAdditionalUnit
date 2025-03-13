package giselle.gmut.common.registries;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.content.gear.mekasuit.VerticalSpeed;
import mekanism.api.gear.config.ModuleBooleanConfig;
import mekanism.api.gear.config.ModuleEnumConfig;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;

public class GMUTModules
{
	public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(GravitationalModulatingUnitTweaks.MODID);

	public static final ModuleRegistryObject<ModuleGravitationalModulatingAdditionalUnit> GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT = MODULES.register("gravitational_modulating_additional_unit", //
			ModuleGravitationalModulatingAdditionalUnit::new, () -> GMUTItems.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT, builder -> builder.maxStackSize(1)//
					.addConfig(ModuleBooleanConfig.create(ModuleGravitationalModulatingAdditionalUnit.FLY_ALWAYS, false))//
					.addConfig(ModuleBooleanConfig.create(ModuleGravitationalModulatingAdditionalUnit.STOP_IMMEDIATELY, true))//
					.addConfig(ModuleBooleanConfig.create(ModuleGravitationalModulatingAdditionalUnit.FIX_FOV, false))//
					.addConfig(//
							ModuleEnumConfig.create(ModuleGravitationalModulatingAdditionalUnit.VERTICAL_SPEED, VerticalSpeed.OFF), //
							ModuleEnumConfig.codec(VerticalSpeed.CODEC), //
							ModuleEnumConfig.streamCodec(VerticalSpeed.STREAM_CODEC))//
	);

	private GMUTModules()
	{

	}

}
