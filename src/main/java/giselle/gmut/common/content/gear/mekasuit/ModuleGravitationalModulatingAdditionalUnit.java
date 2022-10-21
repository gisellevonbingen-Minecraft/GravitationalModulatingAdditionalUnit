package giselle.gmut.common.content.gear.mekasuit;

import javax.annotation.ParametersAreNonnullByDefault;

import giselle.gmut.common.GMUTLang;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleBooleanData;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.api.gear.config.ModuleEnumData;
import mekanism.common.CommonPlayerTickHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

@ParametersAreNonnullByDefault
public class ModuleGravitationalModulatingAdditionalUnit implements ICustomModule<ModuleGravitationalModulatingAdditionalUnit>
{
	private IModuleConfigItem<Boolean> flyAlways;
	private IModuleConfigItem<Boolean> stopImmediately;
	private IModuleConfigItem<Boolean> fixFOV;
	private IModuleConfigItem<VerticalSpeed> verticalSpeed;

	@Override
	public void init(IModule<ModuleGravitationalModulatingAdditionalUnit> module, ModuleConfigItemCreator configItemCreator)
	{
		this.flyAlways = configItemCreator.createConfigItem("fly_always", GMUTLang.MODULE_FLY_ALWAYS, new ModuleBooleanData(false));
		this.stopImmediately = configItemCreator.createConfigItem("stop_immediately", GMUTLang.MODULE_STOP_IMMEDIATELY, new ModuleBooleanData(true));
		this.fixFOV = configItemCreator.createConfigItem("fix_fov", GMUTLang.MODULE_FIX_FOV, new ModuleBooleanData(false));
		this.verticalSpeed = configItemCreator.createConfigItem("vertical_speed", GMUTLang.MODULE_VERTICAL_SPEED, new ModuleEnumData<>(VerticalSpeed.class, VerticalSpeed.OFF));
	}

	@Override
	public void tickServer(IModule<ModuleGravitationalModulatingAdditionalUnit> module, Player player)
	{
		boolean hasGravitationalModulator = CommonPlayerTickHandler.isGravitationalModulationReady(player);

		if (hasGravitationalModulator == true)
		{
			if (this.flyAlways.get() == true)
			{
				if (player.isShiftKeyDown() == false && player.getAbilities().flying == false)
				{
					player.getAbilities().flying = true;
					player.onUpdateAbilities();
				}

			}

		}

	}

	@Override
	public void tickClient(IModule<ModuleGravitationalModulatingAdditionalUnit> module, Player player)
	{
		this.tickServer(module, player);

		boolean hasGravitationalModulator = CommonPlayerTickHandler.isGravitationalModulationReady(player);

		if (hasGravitationalModulator == true)
		{
			if (this.stopImmediately.get() == true)
			{
				if (player.getAbilities().flying == true && player.zza == 0.0F && player.xxa == 0.0F)
				{
					Vec3 deltaMovement = player.getDeltaMovement();
					player.setDeltaMovement(deltaMovement.multiply(0.0D, 1.0D, 0.0D));
				}

			}

			if (player instanceof LocalPlayer clientPlayer)
			{
				if (clientPlayer.getAbilities().flying == true && Minecraft.getInstance().getCameraEntity() == clientPlayer)
				{
					float j = 0.0F;

					if (clientPlayer.input.shiftKeyDown == true)
					{
						j--;
					}

					if (clientPlayer.input.jumping == true)
					{
						j++;
					}

					if (j != 0)
					{
						j *= (this.getVerticalSpeed().get().getSpeed() - 1.0F);
						Vec3 deltaMovement = clientPlayer.getDeltaMovement();
						clientPlayer.setDeltaMovement(deltaMovement.add(0.0D, j * clientPlayer.getAbilities().getFlyingSpeed() * 3.0F, 0.0D));
					}

				}

			}

		}

	}

	public IModuleConfigItem<Boolean> getFlyAlways()
	{
		return this.flyAlways;
	}

	public IModuleConfigItem<Boolean> getStopImmediately()
	{
		return this.stopImmediately;
	}

	public IModuleConfigItem<Boolean> getFixFOV()
	{
		return this.fixFOV;
	}

	public IModuleConfigItem<VerticalSpeed> getVerticalSpeed()
	{
		return this.verticalSpeed;
	}

}
