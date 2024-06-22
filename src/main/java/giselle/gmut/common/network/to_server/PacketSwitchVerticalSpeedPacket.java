package giselle.gmut.common.network.to_server;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.gear.IModule;
import mekanism.api.gear.IModuleContainer;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.network.IMekanismPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PacketSwitchVerticalSpeedPacket(int shift) implements IMekanismPacket
{
	public static final CustomPacketPayload.Type<PacketSwitchVerticalSpeedPacket> TYPE = new CustomPacketPayload.Type<>(GravitationalModulatingUnitTweaks.rl("switch_vertical_speed"));
	public static final StreamCodec<FriendlyByteBuf, PacketSwitchVerticalSpeedPacket> STREAM_CODEC = StreamCodec.composite(//
			ByteBufCodecs.INT, PacketSwitchVerticalSpeedPacket::shift, //
			PacketSwitchVerticalSpeedPacket::new);

	@Override
	public void handle(IPayloadContext context)
	{
		Player player = context.player();
		Tuple<EquipmentSlot, IModule<ModuleGravitationalModulatingAdditionalUnit>> pair = EntityModuleHelper.findArmorEnabledModule(player, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.get());

		if (pair == null)
		{
			return;
		}

		ItemStack stack = player.getItemBySlot(pair.getA());

		if (stack.getItem() instanceof IModuleContainerItem moduleContainerItem)
		{
			IModuleContainer moduleContainer = moduleContainerItem.moduleContainer(stack);
			IModule<ModuleGravitationalModulatingAdditionalUnit> module = pair.getB();
			module.getCustomInstance().changeMode(module, player, moduleContainer, stack, this.shift(), true);
		}

	}

	@Override
	public Type<? extends CustomPacketPayload> type()
	{
		return TYPE;
	}

}
