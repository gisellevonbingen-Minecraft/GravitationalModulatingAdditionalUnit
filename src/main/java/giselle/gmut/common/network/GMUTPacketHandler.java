package giselle.gmut.common.network;

import giselle.gmut.common.network.to_server.PacketSwitchVerticalSpeedPacket;
import mekanism.common.lib.Version;
import mekanism.common.network.BasePacketHandler;
import net.neoforged.bus.api.IEventBus;

public class GMUTPacketHandler extends BasePacketHandler
{
	public GMUTPacketHandler(IEventBus modEventBus, Version version)
	{
		super(modEventBus, version);
	}

	@Override
	protected void registerClientToServer(PacketRegistrar registrar)
	{
		registrar.play(PacketSwitchVerticalSpeedPacket.TYPE, PacketSwitchVerticalSpeedPacket.STREAM_CODEC);
	}

	@Override
	protected void registerServerToClient(PacketRegistrar registrar)
	{

	}

}
