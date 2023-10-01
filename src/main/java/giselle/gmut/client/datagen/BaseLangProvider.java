package giselle.gmut.client.datagen;

import mekanism.api.gear.ModuleData;
import mekanism.api.providers.IModuleDataProvider;
import mekanism.api.text.IHasTranslationKey;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class BaseLangProvider extends LanguageProvider
{
	private final String modid;

	public BaseLangProvider(PackOutput output, String modid)
	{
		super(output, modid, "en_us");
		this.modid = modid;
	}

	protected void add(IHasTranslationKey key, String value)
	{
		this.add(key.getTranslationKey(), value);
	}

	protected void add(IModuleDataProvider<?> moduleDataProvider, String name, String description)
	{
		ModuleData<?> moduleData = moduleDataProvider.getModuleData();
		this.add(moduleData.getTranslationKey(), name);
		this.add(moduleData.getDescriptionTranslationKey(), description);
	}

	@Override
	public String getName()
	{
		return super.getName() + ": " + this.modid;
	}

	public String getModid()
	{
		return this.modid;
	}

}
