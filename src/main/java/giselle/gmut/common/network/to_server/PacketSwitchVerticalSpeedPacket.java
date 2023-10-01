package giselle.gmut.common.network.to_server;

import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.gear.IModule;
import mekanism.common.network.IMekanismPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class PacketSwitchVerticalSpeedPacket implements IMekanismPacket
{
	private final int shift;

	public PacketSwitchVerticalSpeedPacket(int shift)
	{
		this.shift = shift;
	}

	@Override
	public void handle(NetworkEvent.Context context)
	{
		ServerPlayer player = context.getSender();
		IModule<ModuleGravitationalModulatingAdditionalUnit> module = EntityModuleHelper.findArmorEnabledModule(player, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.get());

		if (module != null)
		{
			module.getCustomInstance().changeMode(module, player, module.getContainer(), shift, true);
		}

	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		buffer.writeInt(this.shift);
	}

	public static PacketSwitchVerticalSpeedPacket decode(FriendlyByteBuf buffer)
	{
		return new PacketSwitchVerticalSpeedPacket(buffer.readInt());
	}

}
