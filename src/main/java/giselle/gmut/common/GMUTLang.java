package giselle.gmut.common;

import giselle.gmut.GravitationalModulatingUnitTweaks;
import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

public enum GMUTLang implements ILangEntry
{
	// Constants
	MOD_NAME("constants", "mod_name"),
	KEY_CATAGORY("constants", "key_category"),

	// Key
	KEY_VERTICAL_SPEED("key", "vertical_speed"),

	// Modules
	MODULE_ENABLED("module", "enabled"),
	MODULE_FLY_ALWAYS("module", "fly_always"),
	MODULE_STOP_IMMEDIATELY("module", "stop_immediately"),
	MODULE_FIX_FOV("module", "fix_fov"),
	MODULE_VERTICAL_SPEED("module", "vertical_speed"),
	MODULE_MUTE_MODULATING_SOUND("module", "mute_modulating_sound"),
	// EOL
	;

	private final String key;

	private GMUTLang(String key)
	{
		this.key = key;
	}

	private GMUTLang(String type, String path)
	{
		this(Util.makeDescriptionId(type, GravitationalModulatingUnitTweaks.rl(path)));
	}

	@Override
	public String getTranslationKey()
	{
		return this.key;
	}

}
