package giselle.gmut.common.network;

import giselle.gmut.common.network.to_server.PacketSwitchVerticalSpeedPacket;
import mekanism.common.lib.Version;
import mekanism.common.network.BasePacketHandler;
import net.neoforged.bus.api.IEventBus;

public class GMUTPacketHandler extends BasePacketHandler
{
	public GMUTPacketHandler(IEventBus modEventBus, String modid, Version version)
	{
		super(modEventBus, modid, version);
	}

	@Override
	protected void registerClientToServer(PacketRegistrar registrar)
	{
		registrar.play(PacketSwitchVerticalSpeedPacket.ID, PacketSwitchVerticalSpeedPacket::decode);
	}

	@Override
	protected void registerServerToClient(PacketRegistrar registrar)
	{

	}

}
