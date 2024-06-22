package giselle.gmut.common.datagen;

import java.util.concurrent.CompletableFuture;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.client.datagen.GMUTItemModelProvider;
import giselle.gmut.client.datagen.GMUTLangProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = GravitationalModulatingUnitTweaks.MODID, bus = Bus.MOD)
public class GMUTDataGenerator
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator gen = event.getGenerator();
		PackOutput output = gen.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<Provider> lookupProvider = event.getLookupProvider();

		gen.addProvider(event.includeClient(), new GMUTLangProvider(output));
		gen.addProvider(event.includeClient(), new GMUTItemModelProvider(output, existingFileHelper));

		gen.addProvider(event.includeServer(), new GMUTRecipeProvider(output, lookupProvider));
	}

	private GMUTDataGenerator()
	{

	}

}
