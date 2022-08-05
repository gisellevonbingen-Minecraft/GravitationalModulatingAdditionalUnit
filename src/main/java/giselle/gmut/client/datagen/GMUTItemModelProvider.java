package giselle.gmut.client.datagen;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.registries.GMUTItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GMUTItemModelProvider extends BaseItemModelProvider
{
	public GMUTItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, GravitationalModulatingUnitTweaks.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		this.registerModules(GMUTItems.ITEMS);
	}

}
