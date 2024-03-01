package giselle.gmut.common.network.to_server;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.gear.IModule;
import mekanism.common.network.IMekanismPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record PacketSwitchVerticalSpeedPacket(int shift) implements IMekanismPacket<PlayPayloadContext>
{
	public static final ResourceLocation ID = GravitationalModulatingUnitTweaks.rl("switch_vertical_speed");

	@Override
	public void handle(PlayPayloadContext context)
	{
		context.player().ifPresent(player ->
		{
			IModule<ModuleGravitationalModulatingAdditionalUnit> module = EntityModuleHelper.findArmorEnabledModule(player, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.get());

			if (module != null)
			{
				module.getCustomInstance().changeMode(module, player, module.getContainerStack(), this.shift, true);
			}

		});

	}

	@Override
	public void write(FriendlyByteBuf buffer)
	{
		buffer.writeInt(this.shift);
	}

	public static PacketSwitchVerticalSpeedPacket decode(FriendlyByteBuf buffer)
	{
		return new PacketSwitchVerticalSpeedPacket(buffer.readInt());
	}

	@Override
	public ResourceLocation id()
	{
		return ID;
	}

}
