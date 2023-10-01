package giselle.gmut.common.datagen;

import java.util.function.Consumer;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.registries.GMUTItems;
import mekanism.common.registries.MekanismItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GMUTRecipeProvider extends BaseRecipeProvider
{
	public GMUTRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper)
	{
		super(output, existingFileHelper, GravitationalModulatingUnitTweaks.MODID);
	}

	@Override
	protected void addRecipes(Consumer<FinishedRecipe> consumer)
	{
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GMUTItems.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT)//
				.pattern("ACA").pattern("ABA").pattern("PPP")//
				.define('A', MekanismTags.Items.ALLOYS_REINFORCED)//
				.define('C', MekanismTags.Items.CIRCUITS_ULTIMATE)//
				.define('B', MekanismItems.MODULE_BASE)//
				.define('P', MekanismTags.Items.PELLETS_POLONIUM)//
				.unlockedBy(getHasName(MekanismItems.MODULE_GRAVITATIONAL_MODULATING), has(MekanismItems.MODULE_GRAVITATIONAL_MODULATING))//
				.save(consumer);
	}

}
