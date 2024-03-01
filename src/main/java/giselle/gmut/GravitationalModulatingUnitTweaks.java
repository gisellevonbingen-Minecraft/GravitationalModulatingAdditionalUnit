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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

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

		ModContainer modContainer = ModLoadingContext.get().getActiveContainer();
		IEventBus fml_bus = modContainer.getEventBus();
		this.version = new Version(modContainer);
		this.packetHandler = new GMUTPacketHandler(fml_bus, MODID, new Version(modContainer));

		this.registerFML();
	}

	private void registerFML()
	{
		IEventBus fml_bus = ModLoadingContext.get().getActiveContainer().getEventBus();
		fml_bus.addListener(this::imcQueue);
		fml_bus.addListener(this::buildCreativeModeTabContents);

		GMUTItems.ITEMS.register(fml_bus);
		GMUTModules.MODULES.register(fml_bus);
	}

	private void imcQueue(InterModEnqueueEvent event)
	{
		MekanismIMC.addMekaSuitBodyarmorModules(GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT);
	}

	private void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTab() == MekanismCreativeTabs.MEKANISM.get())
		{
			GMUTItems.ITEMS.getEntries().stream().map(i -> i.get()).forEach(event::accept);
		}

	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
