package giselle.gmut.common.content.gear.mekasuit;

import mekanism.api.gear.ICustomModule;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.content.gear.Module;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class EntityModuleHelper
{
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
