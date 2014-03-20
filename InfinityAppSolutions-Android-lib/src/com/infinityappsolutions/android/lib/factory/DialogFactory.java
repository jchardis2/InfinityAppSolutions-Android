package com.infinityappsolutions.android.lib.factory;

import android.app.AlertDialog;
import android.content.Context;

public class DialogFactory {

	public DialogFactory() {
	}

	public AlertDialog.Builder getMessageDialogBuilder(Context context,
			int stringID) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(stringID);
		return builder;
	}

	public AlertDialog create(AlertDialog.Builder builder) {
		return builder.create();
	}
}
