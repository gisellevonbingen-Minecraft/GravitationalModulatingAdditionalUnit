package giselle.gmut.common.content.gear.mekasuit;

import javax.annotation.Nonnull;

import mekanism.api.IIncrementalEnum;
import mekanism.api.math.MathUtils;
import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;

public enum VerticalSpeed implements IHasTextComponent, IIncrementalEnum<VerticalSpeed>
{
	OFF(1.0F),
	LOW(1.5F),
	MEDIUM(2.0F),
	HIGH(2.5F),
	ULTRA(3.0F);

	private static final VerticalSpeed[] MODES = values();

	private final float speed;
	private final Component label;

	VerticalSpeed(float speed)
	{
		this.speed = speed;
		this.label = TextComponentUtil.getString(Float.toString(speed));
	}

	@Nonnull
	@Override
	public VerticalSpeed byIndex(int index)
	{
		return MathUtils.getByIndexMod(MODES, index);
	}

	@Override
	public Component getTextComponent()
	{
		return this.label;
	}

	public float getSpeed()
	{
		return this.speed;
	}

}
