package com.infinityappsolutions.android.lib.interfaces;

import com.infinityappsolutions.lib.beans.DataBean;

public interface ISaveDataBean<T> {
	public void saveDataBeanListener(Boolean isSaved, DataBean<T> db);
}
