package giselle.gmut.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import mekanism.client.sound.GravitationalModulationSound;
import mekanism.common.content.gear.Module;
import net.minecraft.world.entity.player.Player;

@Mixin(value = GravitationalModulationSound.class, remap = false)
public class GravitationalModulationSoundMixin
{
	@Inject(method = "shouldPlaySound(Lnet/minecraft/world/entity/player/Player;)Z", remap = false, at = @At(value = "HEAD"), cancellable = true)
	private void shouldPlaySound(Player player, CallbackInfoReturnable<Boolean> cir)
	{
		Module<ModuleGravitationalModulatingAdditionalUnit> module = EntityModuleHelper.getEnabledGMAUModule(player);

		if (module != null && module.getCustomInstance().getMuteModulatingSound().get())
		{
			cir.setReturnValue(false);
		}

	}

}
