package iii.pos.client.fragment;

import iii.pos.client.R;
import iii.pos.client.adapter.GridViewWineAdapter;
import iii.pos.client.model.Items;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

//-----this class to slide pages-----------//
public class GridWineFragment extends Fragment {
	int mNumWine;
	int mFirstImageWine = 0;
	int mImageCount = -1;
	ArrayList<Items> lstItemWine;

	// -------create display here---------------//
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		mNumWine = ((args != null) ? args.getInt("numWine") : 0);

		if (args != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Items> serializable = (ArrayList<Items>) args
					.getSerializable("topicListWine");
			lstItemWine = serializable;
			mFirstImageWine = args.getInt("firstImageWine");
		}
		int numTopicsPerPage = 2 * 3;
		mImageCount = numTopicsPerPage;
		mFirstImageWine = (mFirstImageWine / numTopicsPerPage)
				* numTopicsPerPage;

	}

	// --------------create activity--------------//
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Activity a = getActivity();
		View rootView = getView();
		GridView gridview = (GridView) rootView.findViewById(R.id.gridviewwine);
		DisplayMetrics metrics = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		if (gridview == null)
			Log.d("DEBUG", "Unable to locate the gridview.");
		else {
			gridview.setAdapter(new GridViewWineAdapter(a, lstItemWine,
					mFirstImageWine, mImageCount, 80, 80));

		}
	}

	// -------------------------------------------------//
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.demo_pager_grid_wine, container,
				false);
		GridView gridview = (GridView) view.findViewById(R.id.gridviewwine);
		gridview.setTag(new Integer(mNumWine));
		return view;
	}
}
