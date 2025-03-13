package giselle.gmut.common.content.gear.mekasuit;

import javax.annotation.ParametersAreNonnullByDefault;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.GMUTCommonPlayerTickHandler;
import giselle.gmut.common.GMUTLang;
import giselle.gmut.common.registries.GMUTModules;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.IModuleContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

@ParametersAreNonnullByDefault
public class ModuleGravitationalModulatingAdditionalUnit implements ICustomModule<ModuleGravitationalModulatingAdditionalUnit>
{
	public static final ResourceLocation FLY_ALWAYS = GravitationalModulatingUnitTweaks.rl("fly_always");
	public static final ResourceLocation STOP_IMMEDIATELY = GravitationalModulatingUnitTweaks.rl("stop_immediately");
	public static final ResourceLocation FIX_FOV = GravitationalModulatingUnitTweaks.rl("fix_fov");
	public static final ResourceLocation VERTICAL_SPEED = GravitationalModulatingUnitTweaks.rl("vertical_speed");

	private boolean flyAlways;
	private boolean stopImmediately;
	private boolean fixFOV;
	private VerticalSpeed verticalSpeed;

	public ModuleGravitationalModulatingAdditionalUnit(IModule<ModuleGravitationalModulatingAdditionalUnit> module)
	{
		this.flyAlways = module.getBooleanConfigOrFalse(FLY_ALWAYS);
		this.stopImmediately = module.getBooleanConfigOrFalse(STOP_IMMEDIATELY);
		this.fixFOV = module.getBooleanConfigOrFalse(FIX_FOV);
		this.verticalSpeed = module.<VerticalSpeed> getConfigOrThrow(VERTICAL_SPEED).get();
	}

	@Override
	public void tickServer(IModule<ModuleGravitationalModulatingAdditionalUnit> module, IModuleContainer moduleContainer, ItemStack stack, Player player)
	{
		boolean hasGravitationalModulator = GMUTCommonPlayerTickHandler.isGravitationalModulationReady(stack);

		if (hasGravitationalModulator)
		{
			if (this.flyAlways)
			{
				if (!player.isShiftKeyDown() && !player.getAbilities().flying)
				{
					player.getAbilities().flying = true;
					player.onUpdateAbilities();
				}

			}

		}

	}

	@Override
	public void tickClient(IModule<ModuleGravitationalModulatingAdditionalUnit> module, IModuleContainer moduleContainer, ItemStack stack, Player player)
	{
		boolean hasGravitationalModulator = GMUTCommonPlayerTickHandler.isGravitationalModulationReady(stack);

		if (hasGravitationalModulator)
		{
			if (this.flyAlways)
			{
				if (!player.isShiftKeyDown() && !player.getAbilities().flying)
				{
					player.getAbilities().flying = true;
					player.onUpdateAbilities();
				}

			}

			if (this.stopImmediately)
			{
				if (player.getAbilities().flying && player.zza == 0.0F && player.xxa == 0.0F)
				{
					Vec3 deltaMovement = player.getDeltaMovement();
					player.setDeltaMovement(deltaMovement.multiply(0.0D, 1.0D, 0.0D));
				}

			}

			if (player instanceof LocalPlayer clientPlayer)
			{
				if (clientPlayer.getAbilities().flying && Minecraft.getInstance().getCameraEntity() == clientPlayer)
				{
					float j = 0.0F;

					if (clientPlayer.input.shiftKeyDown)
					{
						j--;
					}

					if (clientPlayer.input.jumping)
					{
						j++;
					}

					if (j != 0)
					{
						j *= (this.getVerticalSpeed().getSpeed() - 1.0F);
						Vec3 deltaMovement = clientPlayer.getDeltaMovement();
						clientPlayer.setDeltaMovement(deltaMovement.add(0.0D, j * clientPlayer.getAbilities().getFlyingSpeed() * 3.0F, 0.0D));
					}

				}

			}

		}

	}

	@Override
	public void changeMode(IModule<ModuleGravitationalModulatingAdditionalUnit> module, Player player, IModuleContainer moduleContainer, ItemStack stack, int shift, boolean displayChangeMessage)
	{
		if (module.isEnabled())
		{
			VerticalSpeed prevSpeed = this.getVerticalSpeed();
			VerticalSpeed nextSpeed = prevSpeed.adjust(shift);

			if (prevSpeed != nextSpeed)
			{
				moduleContainer.replaceModuleConfig(player.level().registryAccess(), stack, GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT, module.<VerticalSpeed> getConfigOrThrow(VERTICAL_SPEED).with(nextSpeed));

				if (displayChangeMessage)
				{
					module.displayModeChange(player, GMUTLang.MODULE_VERTICAL_SPEED.translate(), nextSpeed);
				}

			}

		}

	}

	public boolean getFlyAlways()
	{
		return this.flyAlways;
	}

	public boolean getStopImmediately()
	{
		return this.stopImmediately;
	}

	public boolean getFixFOV()
	{
		return this.fixFOV;
	}

	public VerticalSpeed getVerticalSpeed()
	{
		return this.verticalSpeed;
	}

}
