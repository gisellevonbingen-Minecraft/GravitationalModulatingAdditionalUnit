package giselle.gmut;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import giselle.gmut.common.network.GMUTPacketHandler;
import giselle.gmut.common.registries.GMUTItems;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.MekanismIMC;
import mekanism.common.lib.Version;
import mekanism.common.registries.MekanismCreativeTabs;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

	public static GMUTPacketHandler packetHandler()
	{
		return instance.packetHandler;
	}

	public static final String MODID = "gravitationalmodulatingunittweaks";
	public static final Logger LOGGER = LogManager.getLogger();

	public final Version version;
	private final GMUTPacketHandler packetHandler;

	public GravitationalModulatingUnitTweaks()
	{
		instance = this;
		this.version = new Version(ModLoadingContext.get().getActiveContainer());
		this.packetHandler = new GMUTPacketHandler();

		this.registerFML();
	}

	private void registerFML()
	{
		IEventBus fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		fml_bus.addListener(this::commonSetup);
		fml_bus.addListener(this::imcQueue);
		fml_bus.addListener(this::buildCreativeModeTabContents);

		GMUTItems.ITEMS.register(fml_bus);
		GMUTModules.MODULES.register(fml_bus);
	}

	private void commonSetup(FMLCommonSetupEvent event)
	{
		LOGGER.info("Version {} initializing...", this.version);
		packetHandler.initialize();
	}

	private void imcQueue(InterModEnqueueEvent event)
	{
		MekanismIMC.addMekaSuitBodyarmorModules(GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT);
	}

	private void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTab() == MekanismCreativeTabs.MEKANISM.get())
		{
			GMUTItems.ITEMS.getAllItems().stream().forEach(event::accept);
		}

	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
