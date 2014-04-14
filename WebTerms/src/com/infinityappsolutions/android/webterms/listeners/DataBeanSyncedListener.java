package com.infinityappsolutions.android.webterms.listeners;

import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public interface DataBeanSyncedListener {

	public void onDataBeanSynced(DataBean<Term> db);

	public void onDataBeanSyncFailure();
}
