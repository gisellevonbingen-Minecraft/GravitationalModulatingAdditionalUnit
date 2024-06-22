package giselle.gmut.common.content.gear.mekasuit;

import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EntityModuleHelper
{
	public static <T extends ICustomModule<T>> Tuple<EquipmentSlot, IModule<T>> findArmorEnabledModule(LivingEntity entity, IModuleDataProvider<T> type)
	{
		for (EquipmentSlot slot : EquipmentSlot.values())
		{
			ItemStack itemStack = entity.getItemBySlot(slot);
			IModule<T> module = ModuleHelper.get().getModule(itemStack, type);

			if (module != null && module.isEnabled())
			{
				return new Tuple<>(slot, module);
			}

		}

		return null;
	}

}
