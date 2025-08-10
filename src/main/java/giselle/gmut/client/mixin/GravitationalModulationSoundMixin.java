package giselle.gmut.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import giselle.gmut.common.content.gear.mekasuit.EntityModuleHelper;
import giselle.gmut.common.content.gear.mekasuit.ModuleGravitationalModulatingAdditionalUnit;
import mekanism.api.gear.IModule;
import mekanism.client.sound.GravitationalModulationSound;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

@Mixin(value = GravitationalModulationSound.class, remap = false)
public class GravitationalModulationSoundMixin
{
	@Inject(method = "shouldPlaySound(Lnet/minecraft/world/entity/player/Player;)Z", remap = false, at = @At(value = "HEAD"), cancellable = true)
	private void shouldPlaySound(Player player, CallbackInfoReturnable<Boolean> cir)
	{
		Tuple<EquipmentSlot, IModule<ModuleGravitationalModulatingAdditionalUnit>> pair = EntityModuleHelper.getEnabledGMAUModule(player);

		if (pair != null && pair.getB().getCustomInstance().getMuteModulatingSound())
		{
			cir.setReturnValue(false);
		}

	}

}
