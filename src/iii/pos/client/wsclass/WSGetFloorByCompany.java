package iii.pos.client.wsclass;

import iii.pos.client.activity.MainPosActivity;
import iii.pos.client.model.Floor;
import iii.pos.client.server.ConfigurationServer;
import iii.pos.client.server.ConfigurationWS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class WSGetFloorByCompany extends AsyncTask<Void, Void, ArrayList<Floor>> {

	private ConfigurationWS mWs;
	private ProgressDialog mProgress;
	private String companyCode;
	
	public WSGetFloorByCompany(Context mContext, String companyCode) {
		super();
		this.mWs = new ConfigurationWS(mContext);
		this.companyCode = companyCode;
		mProgress = new ProgressDialog(mContext);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgress.setMessage("Loading...");
		mProgress.show();
	}

	@Override
	protected ArrayList<Floor> doInBackground(Void... params) {
		ArrayList<Floor> lstFloor = new ArrayList<Floor>();
		try {
			String URLGetAllFloor = ConfigurationServer.getURLServer() + "wsclient_get_floor_by_company.php";
			JSONObject json = new JSONObject();
			json.put("user_id", MainPosActivity.user.getUser_id());
			json.put("company_code", companyCode);
			
			JSONArray arrITable = mWs.connectWSPut_Get_Data(URLGetAllFloor, json, "floor");

			for (int i = 0; i < arrITable.length(); i++) {
				JSONObject results = arrITable.getJSONObject(i);
				Floor floor = new Floor();
				floor.setId(results.getInt("id"));
				floor.setCode(results.getString("code"));
				floor.setName(results.getString("name"));
				floor.setStatus(results.getInt("status"));
				lstFloor.add(floor);
			}
		} catch (Exception e) {
			Floor floor = new Floor();
			floor.setId(0);
			floor.setCode("null");
			floor.setName("null");
			floor.setStatus(0);
			lstFloor.add(floor);
			Log.e("ERROR", "Lỗi trong quá trình lấy danh sách Floor");
		}

		return lstFloor;
	}

	@Override
	protected void onPostExecute(ArrayList<Floor> result) {
		if( mProgress.isShowing()) 
			mProgress.dismiss();
	}
}
