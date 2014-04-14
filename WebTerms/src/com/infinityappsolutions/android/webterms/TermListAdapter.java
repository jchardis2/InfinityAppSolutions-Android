package com.infinityappsolutions.android.webterms;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infinityappsolutions.lib.webterms.bean.Term;

public class TermListAdapter extends ArrayAdapter<Term> {

	public TermListAdapter(Context context, int resource,
			int textViewResourceId, Term[] objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public TermListAdapter(Context context, int resource, Term[] objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	public TermListAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
		// TODO Auto-generated constructor stub
	}

	public TermListAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}

	public TermListAdapter(Context context, int resource,
			int textViewResourceId, List<Term> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	public TermListAdapter(Activity activity, int resource,
			ArrayList<Term> terms) {
		super(activity, resource, terms);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		if (view instanceof TextView) {
			((TextView) view).setText(getItem(position).getName());
		}
		return view;
	}

}
