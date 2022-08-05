package giselle.gmut.common.datagen;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.client.datagen.GMUTItemModelProvider;
import giselle.gmut.client.datagen.GMUTLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, bus = Bus.MOD)
public class GMUTDataGenerator
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeClient() == true)
		{
			gen.addProvider(new GMUTLangProvider(gen));
			gen.addProvider(new GMUTItemModelProvider(gen, existingFileHelper));
		}

		if (event.includeServer() == true)
		{
			gen.addProvider(new GMUTRecipeProvider(gen, existingFileHelper));
		}

	}

	private GMUTDataGenerator()
	{
	}

}
