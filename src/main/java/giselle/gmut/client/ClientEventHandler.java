package giselle.gmut.client;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import mekanism.api.gear.IModule;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Tuple;
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
		Tuple<EquipmentSlot, IModule<ModuleGravitationalModulatingAdditionalUnit>> pair = EntityModuleHelper.getEnabledGMAUModule(player);

		if (pair != null)
		{
			boolean fixFOV = pair.getB().getCustomInstance().getFixFOV();

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
