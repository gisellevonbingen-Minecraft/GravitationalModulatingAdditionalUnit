package giselle.gmut.client;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import mekanism.api.gear.IModule;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler
{
	@SubscribeEvent
	public static void onFOVModifier(FOVUpdateEvent e)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		IModule<ModuleGravitationalModulatingAdditionalUnit> module = EntityModuleHelper.getEnabledGMAUModule(player);

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
