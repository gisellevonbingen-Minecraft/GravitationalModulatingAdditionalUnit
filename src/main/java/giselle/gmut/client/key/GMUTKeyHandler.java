package giselle.gmut.client.key;

import org.lwjgl.glfw.GLFW;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.GMUTLang;
import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import giselle.gmut.common.network.to_server.PacketSwitchVerticalSpeedPacket;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.key.MekKeyBindingBuilder;
import mekanism.client.sound.SoundHandler;
import mekanism.common.content.gear.Module;
import mekanism.common.registries.MekanismSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;

public class GMUTKeyHandler
{
	public static final KeyBinding moduleVerticalSpeedSwitchKey = new MekKeyBindingBuilder().category(GMUTLang.KEY_CATAGORY).description(GMUTLang.KEY_VERTICAL_SPEED).conflictInGame().keyCode(GLFW.GLFW_KEY_UNKNOWN).onKeyDown((kb, isRepeat) -> handleModuleVerticalSpeedSwitch()).build();

	public static void registerKeybindings()
	{
		ClientRegistrationUtil.registerKeyBindings(moduleVerticalSpeedSwitchKey);
	}

	private static void handleModuleVerticalSpeedSwitch()
	{
		Minecraft minecraft = Minecraft.getInstance();
		PlayerEntity player = minecraft.player;

		if (player != null)
		{
			Module<ModuleGravitationalModulatingAdditionalUnit> module = EntityModuleHelper.findArmorEnabledModule(player, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT.get());

			if (module != null)
			{
				GravitationalModulatingUnitTweaks.packetHandler.sendToServer(new PacketSwitchVerticalSpeedPacket(player.isShiftKeyDown() ? -1 : +1));
				SoundHandler.playSound(MekanismSounds.HYDRAULIC);
			}

		}

	}

}
