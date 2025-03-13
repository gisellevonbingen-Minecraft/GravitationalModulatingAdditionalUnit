package giselle.gmut.client.datagen;

import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.ModuleData;
import mekanism.api.text.IHasTranslationKey;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

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

	protected <MODULE extends ICustomModule<MODULE>> void add(DeferredHolder<ModuleData<?>, ModuleData<MODULE>> moduleDataProvider, String name, String description)
	{
		ModuleData<MODULE> moduleData = moduleDataProvider.get();
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
