package giselle.gmut.client.datagen;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import giselle.gmut.common.GMUTLang;
import giselle.gmut.common.registries.GMUTModules;
import net.minecraft.data.DataGenerator;

public class GMUTLangProvider extends BaseLangProvider
{
	public GMUTLangProvider(DataGenerator gen)
	{
		super(gen, GravitationalModulatingUnitTweaks.MODID);
	}

	@Override
	protected void addTranslations()
	{
		this.addMisc();
	}

	private void addMisc()
	{
		this.add(GMUTLang.MODULE_FLY_ALWAYS, "Defy Gravity Always");
		this.add(GMUTLang.MODULE_STOP_IMMEDIATELY, "Stop Immediately");
		this.add(GMUTLang.MODULE_FIX_FOV, "Fix FOV");

		this.add(GMUTModules.GRAVITATIONAL_MODULATING_ADDITIONAL_UNIT, "Gravitational Modulating Additional Unit", "Require 'Gravitational Modulating Unit', Provides additional features");
	}

}
