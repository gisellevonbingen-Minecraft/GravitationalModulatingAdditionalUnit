package giselle.gmut.client;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.client.key.GMUTKeyHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration
{
	@SubscribeEvent
	public static void init(FMLClientSetupEvent event)
	{
		GMUTKeyHandler.registerKeybindings();
	}

}
