package giselle.gmut;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import giselle.gmut.common.registries.GMUTItems;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.MekanismIMC;
import mekanism.common.lib.Version;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GravitationalModulatingUnitTweaks.MODID)
public class GravitationalModulatingUnitTweaks
{
	private static GravitationalModulatingUnitTweaks instance = null;

	public static GravitationalModulatingUnitTweaks instance()
	{
		return instance;
	}

	public static final String MODID = "gravitationalmodulatingunittweaks";
	public static final Logger LOGGER = LogManager.getLogger();

	public final Version version;

	public GravitationalModulatingUnitTweaks()
	{
		instance = this;
		this.version = new Version(ModLoadingContext.get().getActiveContainer());

		this.registerFML();
	}

	private void registerFML()
	{
		IEventBus fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		fml_bus.addListener(this::commonSetup);
		fml_bus.addListener(this::imcQueue);

		GMUTItems.ITEMS.register(fml_bus);
		GMUTModules.MODULES.register(fml_bus);
	}

	private void commonSetup(FMLCommonSetupEvent event)
	{
		LOGGER.info("Version {} initializing...", this.version);
	}

	private void imcQueue(InterModEnqueueEvent event)
	{
		MekanismIMC.addMekaSuitBodyarmorModules(GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT);
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
