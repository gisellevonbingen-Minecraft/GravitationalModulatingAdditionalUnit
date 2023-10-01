package giselle.gmut.common.content.gear.mekasuit;

import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class EntityModuleHelper
{
	public static <T extends ICustomModule<T>> IModule<T> findArmorEnabledModule(Entity entity, IModuleDataProvider<T> type)
	{
		for (ItemStack itemStack : entity.getArmorSlots())
		{
			IModule<T> module = ModuleHelper.get().load(itemStack, type);

			if (module != null && module.isEnabled() == true)
			{
				return module;
			}

		}

		return null;
	}

}
