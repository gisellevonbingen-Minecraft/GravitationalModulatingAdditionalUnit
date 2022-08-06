package giselle.gmut.client;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.MekanismAPI;
import mekanism.api.gear.IModule;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler
{
	@SubscribeEvent
	public static void onFOVModifier(FOVModifierEvent e)
	{
		Minecraft mc = Minecraft.getInstance();
		Player player = mc.player;
		IModule<ModuleGravitationalModulatingAdditionalUnit> module = MekanismAPI.getModuleHelper().load(player.getItemBySlot(EquipmentSlot.CHEST), GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT);

		if (module != null)
		{
			boolean fixFOV = module.getCustomInstance().getFixFOV().get();

			if (fixFOV == true)
			{
				e.setNewfov(1.0F);
			}

		}

	}

	private ClientEventHandler()
	{

	}

}
