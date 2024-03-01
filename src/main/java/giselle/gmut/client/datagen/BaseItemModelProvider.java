package giselle.gmut.client.datagen;

import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public abstract class BaseItemModelProvider extends ItemModelProvider
{
	public BaseItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper)
	{
		super(output, modid, existingFileHelper);
	}

	protected void registerModules(ItemDeferredRegister register)
	{
		for (DeferredHolder<Item, ? extends Item> holder : register.getEntries())
		{
			Item item = holder.get();

			if (item instanceof ItemModule)
			{
				this.generated(holder);
			}

		}

	}

	protected ResourceLocation itemTexture(DeferredHolder<Item, ? extends Item> itemProvider)
	{
		return this.modLoc("item/" + itemProvider.getId().getPath());
	}

	protected ItemModelBuilder generated(DeferredHolder<Item, ? extends Item> itemProvider)
	{
		return this.generated(itemProvider, this.itemTexture(itemProvider));
	}

	protected ItemModelBuilder generated(DeferredHolder<Item, ? extends Item> itemProvider, ResourceLocation texture)
	{
		return this.withExistingParent(itemProvider.getId().getPath(), "item/generated").texture("layer0", texture);
	}

}
