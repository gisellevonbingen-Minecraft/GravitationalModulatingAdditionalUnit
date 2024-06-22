package giselle.gmut.client;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

@EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class ClientEventHandler
{
	@SubscribeEvent
	public static void onComputeFovModifier(ComputeFovModifierEvent e)
	{
		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;
		IModule<ModuleGravitationalModulatingAdditionalUnit> module = ModuleHelper.get().getModule(player.getItemBySlot(EquipmentSlot.CHEST), GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT);

		if (module != null && module.isEnabled())
		{
			boolean fixFOV = module.getCustomInstance().getFixFOV();

			if (fixFOV)
			{
				e.setNewFovModifier(1.0F);
			}

		}

	}

	private ClientEventHandler()
	{

	}

}
