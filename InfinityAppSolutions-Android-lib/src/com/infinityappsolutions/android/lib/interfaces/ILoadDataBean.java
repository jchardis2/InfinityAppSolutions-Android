package com.infinityappsolutions.android.lib.interfaces;

import com.infinityappsolutions.lib.beans.DataBean;

public interface ILoadDataBean<T> {
	public void loadDataBeanListener(DataBean<T> db);

	public void loadFailed();
}
