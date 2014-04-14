package com.infinityappsolutions.android.webterms.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.infinityappsolutions.android.webterms.R;
import com.infinityappsolutions.android.webterms.TermListAdapter;
import com.infinityappsolutions.lib.beans.DataBean;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class TermFragment extends ListFragment {

	public static final String ARG_TERM_NUMBER = "term_number";

	public TermFragment() {

	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_terms, container,
	// false);
	// int i = getArguments().getInt(ARG_TERM_NUMBER);
	// String title = getResources().getStringArray(
	// R.array.home_drawer_strings)[i];
	//
	// return rootView;
	// }

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DataBean<Term> db = (DataBean<Term>) getActivity().getIntent()
				.getExtras().get("DataBean");
		// ArrayAdapter<Term> adapter = new ArrayAdapter<Term>(this,
		// android.R.layout.simple_expandable_list_item_1,
		
		TermListAdapter adapter = new TermListAdapter(getActivity(),
				android.R.layout.simple_list_item_1, db.getTerms());
		setListAdapter(adapter);
		setListShown(true);
		getListView().invalidate();
		registerForContextMenu(getListView());
	}
}
