package com.infinityappsolutions.android.lib.settings;

import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingsHelper {

	public static final String PREFS_NAME = "SETTING_PREFERENCES";
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;

	private SettingsHelper(Activity activity) {
		init(activity);
	}

	private void init(Activity activity) {
		settings = activity.getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
	}

	public Boolean getBoolean(String key, Boolean defaultValue)
			throws ClassCastException {
		return settings.getBoolean(key, defaultValue);
	}

	public Editor putBoolean(String key, Boolean value) {
		return editor.putBoolean(key, value);
	}

	public String getString(String key, String defaultValue)
			throws ClassCastException {
		return settings.getString(key, defaultValue);
	}

	public Editor putString(String key, String value) {
		return editor.putString(key, value);
	}

	public Float getFloat(String key, Float defaultValue)
			throws ClassCastException {
		return settings.getFloat(key, defaultValue);
	}

	public Editor putFloat(String key, Float value) {
		return editor.putFloat(key, value);
	}

	public Integer getString(String key, Integer defaultValue)
			throws ClassCastException {
		return settings.getInt(key, defaultValue);
	}

	public Editor putInteger(String key, Integer value) {
		return editor.putInt(key, value);
	}

	public Long getLong(String key, Long defaultValue)
			throws ClassCastException {
		return settings.getLong(key, defaultValue);
	}

	public Editor putLong(String key, Long value) {
		return editor.putLong(key, value);
	}

	public Set<String> getStringSet(String key, Set<String> defaultValue)
			throws ClassCastException {
		return settings.getStringSet(key, defaultValue);
	}

	public Editor putStringSet(String key, Set<String> value) {
		return editor.putStringSet(key, value);
	}

	public Boolean commit() {
		return editor.commit();
	}
}
