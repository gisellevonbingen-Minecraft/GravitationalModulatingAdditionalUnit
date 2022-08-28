package giselle.gmut.common.datagen;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.resources.ResourcePackType;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseRecipeProvider extends RecipeProvider
{
	private final ExistingFileHelper existingFileHelper;
	private final String modid;

	public BaseRecipeProvider(DataGenerator gen, ExistingFileHelper existingFileHelper, String modid)
	{
		super(gen);
		this.existingFileHelper = existingFileHelper;
		this.modid = modid;
	}

	@Override
	protected final void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
	{
		Consumer<IFinishedRecipe> trackingConsumer = consumer.andThen(recipe -> this.existingFileHelper.trackGenerated(recipe.getId(), ResourcePackType.SERVER_DATA, ".json", "recipes"));
		this.addRecipes(trackingConsumer);
	}

	protected abstract void addRecipes(Consumer<IFinishedRecipe> consumer);

	public ExistingFileHelper getExistingFileHelper()
	{
		return this.existingFileHelper;
	}

	public String getModid()
	{
		return this.modid;
	}

	@Override
	public String getName()
	{
		return super.getName() + ": " + this.modid;
	}

}
