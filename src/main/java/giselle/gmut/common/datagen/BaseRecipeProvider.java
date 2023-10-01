package giselle.gmut.common.datagen;

import java.util.function.Consumer;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseRecipeProvider extends RecipeProvider
{
	private final ExistingFileHelper existingFileHelper;
	private final String modid;

	public BaseRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper, String modid)
	{
		super(output);
		this.existingFileHelper = existingFileHelper;
		this.modid = modid;
	}

	@Override
	protected final void buildRecipes(Consumer<FinishedRecipe> consumer)
	{
		Consumer<FinishedRecipe> trackingConsumer = consumer.andThen(recipe -> this.existingFileHelper.trackGenerated(recipe.getId(), PackType.SERVER_DATA, ".json", "recipes"));
		this.addRecipes(trackingConsumer);
	}

	protected abstract void addRecipes(Consumer<FinishedRecipe> consumer);

	public ExistingFileHelper getExistingFileHelper()
	{
		return this.existingFileHelper;
	}

	public String getModid()
	{
		return this.modid;
	}

}
