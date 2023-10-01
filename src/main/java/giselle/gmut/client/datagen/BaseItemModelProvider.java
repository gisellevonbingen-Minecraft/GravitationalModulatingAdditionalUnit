package giselle.gmut.client.datagen;

import mekanism.api.providers.IItemProvider;
import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseItemModelProvider extends ItemModelProvider
{
	public BaseItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper)
	{
		super(output, modid, existingFileHelper);
	}

	protected void registerModules(ItemDeferredRegister register)
	{
		for (IItemProvider itemProvider : register.getAllItems())
		{
			Item item = itemProvider.asItem();

			if (item instanceof ItemModule)
			{
				this.generated(itemProvider);
			}

		}

	}

	protected ResourceLocation itemTexture(IItemProvider itemProvider)
	{
		return this.modLoc("item/" + itemProvider.getName());
	}

	protected ItemModelBuilder generated(IItemProvider itemProvider)
	{
		return this.generated(itemProvider, this.itemTexture(itemProvider));
	}

	protected ItemModelBuilder generated(IItemProvider itemProvider, ResourceLocation texture)
	{
		return this.withExistingParent(itemProvider.getName(), "item/generated").texture("layer0", texture);
	}

}
