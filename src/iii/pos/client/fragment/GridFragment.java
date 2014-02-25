package iii.pos.client.fragment;
import iii.pos.client.R;
import iii.pos.client.adapter.GridViewItemAdapter;
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
public class GridFragment extends Fragment {

	int mNum; 
	int mFirstImage = 0;
	int mImageCount = -1;
	ArrayList<Items> lstItem; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		mNum = ((args != null) ? args.getInt("num") : 0);

		if (args != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Items> serializable = (ArrayList<Items>) args.getSerializable("topicList");
			lstItem = serializable;
			mFirstImage = args.getInt("firstImage");
		}
		int numTopicsPerPage = 2 * 3;
		mImageCount = numTopicsPerPage;
		mFirstImage = (mFirstImage / numTopicsPerPage) * numTopicsPerPage;

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Activity a = getActivity();
		View rootView = getView();
		GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
		DisplayMetrics metrics = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(metrics);
   
		if (gridview == null)
			Log.d("DEBUG", "Unable to locate the gridview.");
		else {
			gridview.setAdapter(new GridViewItemAdapter(a, lstItem,
					mFirstImage, mImageCount, 80, 80));

		}
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.demo_pager_grid, container, false);
		GridView gridview = (GridView) view.findViewById(R.id.gridview);
		gridview.setTag(new Integer(mNum));
		return view;
	}
} 
