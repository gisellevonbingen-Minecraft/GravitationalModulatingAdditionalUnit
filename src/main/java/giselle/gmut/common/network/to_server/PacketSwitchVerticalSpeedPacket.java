package giselle.gmut.common.network.to_server;

import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.common.content.gear.Module;
import mekanism.common.network.IMekanismPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class PacketSwitchVerticalSpeedPacket implements IMekanismPacket
{
	private final int shift;

	public PacketSwitchVerticalSpeedPacket(int shift)
	{
		this.shift = shift;
	}

	@Override
	public void handle(Context context)
	{
		ServerPlayerEntity player = context.getSender();
		Module<ModuleGravitationalModulatingAdditionalUnit> module = EntityModuleHelper.findArmorEnabledModule(player, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.get());

		if (module != null)
		{
			module.changeMode(player, module.getContainer(), shift, true);
		}

	}

	@Override
	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.shift);
	}

	public static PacketSwitchVerticalSpeedPacket decode(PacketBuffer buffer)
	{
		return new PacketSwitchVerticalSpeedPacket(buffer.readInt());
	}

}
