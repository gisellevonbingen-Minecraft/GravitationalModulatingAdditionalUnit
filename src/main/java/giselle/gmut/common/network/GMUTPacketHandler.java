package giselle.gmut.common.network;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.network.to_server.PacketSwitchVerticalSpeedPacket;
import mekanism.common.network.BasePacketHandler;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class GMUTPacketHandler extends BasePacketHandler
{
	private static final SimpleChannel netHandler = createChannel(GravitationalModulatingUnitTweaks.rl(GravitationalModulatingUnitTweaks.MODID));

	@Override
	protected SimpleChannel getChannel()
	{
		return netHandler;
	}

	@Override
	public void initialize()
	{
		registerClientToServer(PacketSwitchVerticalSpeedPacket.class, PacketSwitchVerticalSpeedPacket::decode);
	}

}
