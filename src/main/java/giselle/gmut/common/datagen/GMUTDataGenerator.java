package giselle.gmut.common.datagen;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.client.datagen.GMUTItemModelProvider;
import giselle.gmut.client.datagen.GMUTLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, bus = Bus.MOD)
public class GMUTDataGenerator
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		gen.addProvider(event.includeClient(), new GMUTLangProvider(gen));
		gen.addProvider(event.includeClient(), new GMUTItemModelProvider(gen, existingFileHelper));

		gen.addProvider(event.includeServer(), new GMUTRecipeProvider(gen, existingFileHelper));

	}

	private GMUTDataGenerator()
	{

	}

}
