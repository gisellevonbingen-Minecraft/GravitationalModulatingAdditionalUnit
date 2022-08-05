package giselle.gmut.common.content.gear.mekasuit;

import javax.annotation.ParametersAreNonnullByDefault;

import giselle.gmut.common.GMUTLang;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.config.IModuleConfigItem;
import mekanism.api.gear.config.ModuleBooleanData;
import mekanism.api.gear.config.ModuleConfigItemCreator;
import mekanism.common.CommonPlayerTickHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

@ParametersAreNonnullByDefault
public class ModuleGravitationalModulatingAdditionalUnit implements ICustomModule<ModuleGravitationalModulatingAdditionalUnit>
{
	private IModuleConfigItem<Boolean> flyAlways;
	private IModuleConfigItem<Boolean> stopImmediately;

	@Override
	public void init(IModule<ModuleGravitationalModulatingAdditionalUnit> module, ModuleConfigItemCreator configItemCreator)
	{
		this.flyAlways = configItemCreator.createConfigItem("fly_always", GMUTLang.MODULE_FLY_ALWAYS, new ModuleBooleanData(false));
		this.stopImmediately = configItemCreator.createConfigItem("stop_immediately", GMUTLang.MODULE_STOP_IMMEDIATELY, new ModuleBooleanData(true));
	}

	@Override
	public void tickClient(IModule<ModuleGravitationalModulatingAdditionalUnit> module, Player player)
	{
		boolean hasGravitationalModulator = CommonPlayerTickHandler.isGravitationalModulationReady(player);

		if (hasGravitationalModulator == true)
		{
			if (this.flyAlways.get() == true)
			{
				player.getAbilities().flying = true;
			}

			if (this.stopImmediately.get() == true)
			{
				if (player.getAbilities().flying == true && player.zza == 0.0F && player.xxa == 0.0F)
				{
					Vec3 deltaMovement = player.getDeltaMovement();
					player.setDeltaMovement(deltaMovement.multiply(0.0D, 1.0D, 0.0D));
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

}
