package giselle.gmut.common.content.gear.mekasuit;

import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.gear.ICustomModule;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.content.gear.Module;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class EntityModuleHelper
{
	public static Module<ModuleGravitationalModulatingAdditionalUnit> getEnabledGMAUModule(Entity entity)
	{
		return findArmorEnabledModule(entity, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.get());
	}

	public static <T extends ICustomModule<T>> Module<T> findArmorEnabledModule(Entity entity, IModuleDataProvider<T> type)
	{
		for (ItemStack itemStack : entity.getArmorSlots())
		{
			Module<T> module = ModuleHelper.INSTANCE.load(itemStack, type);

			if (module != null && module.isEnabled() == true)
			{
				return module;
			}

		}

		return null;
	}

}
