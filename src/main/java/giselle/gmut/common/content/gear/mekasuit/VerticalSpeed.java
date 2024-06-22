package giselle.gmut.common.content.gear.mekasuit;

import java.util.function.IntFunction;

import javax.annotation.Nonnull;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import mekanism.api.IIncrementalEnum;
import mekanism.api.math.MathUtils;
import mekanism.api.text.IHasTextComponent;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

public enum VerticalSpeed implements IHasTextComponent, IIncrementalEnum<VerticalSpeed>, StringRepresentable
{
	OFF(1.0F),
	LOW(1.5F),
	MEDIUM(2.0F),
	HIGH(2.5F),
	ULTRA(3.0F);

	public static final Codec<VerticalSpeed> CODEC = StringRepresentable.fromEnum(VerticalSpeed::values);
	public static final IntFunction<VerticalSpeed> BY_ID = ByIdMap.continuous(VerticalSpeed::ordinal, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
	public static final StreamCodec<ByteBuf, VerticalSpeed> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, VerticalSpeed::ordinal);

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

	@Override
	public String getSerializedName()
	{
		return this.name();
	}

}
