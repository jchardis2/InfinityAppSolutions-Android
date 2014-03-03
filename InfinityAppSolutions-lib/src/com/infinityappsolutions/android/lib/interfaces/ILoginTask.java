package com.infinityappsolutions.android.lib.interfaces;

/**
 * Interface for a LoginAsyncTask to report if the login was successful or
 * failed.
 * 
 * @author jchardis
 * 
 */
public interface ILoginTask extends IWebTask {

	public void loginSuccess(String pageContents);

	public void loginFailed();
}
