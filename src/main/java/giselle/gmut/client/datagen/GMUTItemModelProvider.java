package giselle.gmut.client.datagen;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.registries.GMUTItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class GMUTItemModelProvider extends BaseItemModelProvider
{
	public GMUTItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
	{
		super(output, GravitationalModulatingUnitTweaks.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		this.registerModules(GMUTItems.ITEMS);
	}

}
