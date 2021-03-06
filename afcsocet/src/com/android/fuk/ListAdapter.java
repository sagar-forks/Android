package com.android.fuk;

import com.android.afcsocet.R;
import com.android.rwmdl.PAyarRow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class ListAdapter extends ArrayAdapter {

	private Activity activity;
	public int FocusId = -1;
	public int LastId = 0;
	public int LastToId = 0;
	public Boolean KAYMA = true;

	public enum IslemTipi {
		PAYARLIST
	}

	private IslemTipi ISLEMTIPI = IslemTipi.PAYARLIST;

	@SuppressWarnings("unchecked")
	public ListAdapter(Activity activity, Context context,
			int textViewResourceId, Object[] objects, IslemTipi _ISLEMTIPI) {
		super(context, textViewResourceId, objects);
		ISLEMTIPI = _ISLEMTIPI;
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (KAYMA) {
			LastToId = LastId;
			LastId = position;
		} else {
			KAYMA = true;
		}
		int ResID = 0;

		if (ISLEMTIPI == IslemTipi.PAYARLIST) {
			ResID = R.layout.listrow_payar;
		}

		LinearLayout view = (LinearLayout) convertView;
		if (convertView != null)
			view = (LinearLayout) convertView;
		else
			view = ((LinearLayout) LayoutInflater.from(activity).inflate(ResID,
					parent, false));

		if (position == FocusId) {
			view.setBackgroundResource(R.drawable.listerowfocusstyle);
		} else {
			if (position % 2 == 0) {
				view.setBackgroundResource(R.drawable.listerowevenstyle);
			} else {
				view.setBackgroundResource(R.drawable.listerowoddstyle);
			}
		}

		if (ISLEMTIPI == IslemTipi.PAYARLIST) {

			PAyarRow item = (PAyarRow) this.getItem(position);
			((TextView) view.findViewById(R.id.TxtId)).setText(Integer
					.toString(item.ID));
			((TextView) view.findViewById(R.id.TxtSubeAdi))
					.setText(item.SubeAdi);
			((TextView) view.findViewById(R.id.Txticip)).setText(item.IcIp);
			((TextView) view.findViewById(R.id.Txtdisip)).setText((item.DisIp));
			((TextView) view.findViewById(R.id.Txtportnumarasi))
					.setText((item.Port));
		}

		return view;
	}
}
