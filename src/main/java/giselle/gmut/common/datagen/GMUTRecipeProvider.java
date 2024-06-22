package giselle.gmut.common.datagen;

import java.util.concurrent.CompletableFuture;

import giselle.gmut.common.registries.GMUTItems;
import mekanism.common.registries.MekanismItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

public class GMUTRecipeProvider extends RecipeProvider
{
	public GMUTRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
	{
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput pRecipeOutput)
	{
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GMUTItems.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT)//
				.pattern("ACA").pattern("ABA").pattern("PPP")//
				.define('A', MekanismTags.Items.ALLOYS_REINFORCED)//
				.define('C', MekanismTags.Items.CIRCUITS_ULTIMATE)//
				.define('B', MekanismItems.MODULE_BASE)//
				.define('P', MekanismTags.Items.PELLETS_POLONIUM)//
				.unlockedBy(getHasName(MekanismItems.MODULE_GRAVITATIONAL_MODULATING), has(MekanismItems.MODULE_GRAVITATIONAL_MODULATING))//
				.save(pRecipeOutput);
	}

}
