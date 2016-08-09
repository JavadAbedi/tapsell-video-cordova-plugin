package ir.tapsell.cordova;

import ir.tapsell.cordova.utils.TCInitiator;
import ir.tapsell.cordova.utils.TCListener;
import ir.tapsell.tapsellvideosdk.developer.CheckCtaAvailabilityResponseHandler;
import ir.tapsell.tapsellvideosdk.developer.DeveloperInterface;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.util.Log;

public class TapsellVideo extends CordovaPlugin {
	private static final String LOG_TAG = "TapsellVideo";
	private static CallbackContext callbackContextKeepCallback = null;
	private static Activity mActivity = null;
	public CordovaInterface cordova = null;
	protected boolean connected;
	protected boolean available;
	protected int award;
	
	@Override
	public void initialize (CordovaInterface initCordova, CordovaWebView webView) {
		 Log.e (LOG_TAG, "initialize");
		  cordova = initCordova;
		  super.initialize (cordova, webView);
	}
	
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext CallbackContext) throws JSONException {
		if (action.equals("setUp")) {
			setUp(action, args, CallbackContext);
			return true;
		}
		else if (action.equals("showVideo")) {
	    	showVideo(action, args, CallbackContext);
		    return true;
		}
		else if (action.equals("checkCTA")) {
			checkCTA(action, args, CallbackContext);
		    return true;
		}
	    return false;
	}
	
	private void setUp(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String Key = args.getString(0);
		mActivity = cordova.getActivity();
	    TCInitiator.init(mActivity, Key);
		callbackContextKeepCallback = callbackContext;
		callbackContextKeepCallback.success();
	}
	
	private void showVideo(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final Integer minAward = args.getInt(0);
		final Integer videoType = args.getInt(1);
		callbackContextKeepCallback = callbackContext;
		TCInitiator.showAd(mActivity, minAward, videoType, new VideoListener());
	}
	
	class VideoListener implements TCListener {

		@Override
		public void MDVideo(Boolean isConnected, Boolean isAvailable, Integer award) {
			
			JSONObject result = new JSONObject();
  			try {
				result.put("Vconnected", isConnected);
				result.put("Vavailable", isAvailable);
        		result.put("award", award);
			} catch (JSONException e) {
			}
			PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
  			resultado.setKeepCallback(true);
  			TapsellVideo.callbackContextKeepCallback.sendPluginResult(resultado);
		}
	}
	
	private void checkCTA(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final int minAward = args.getInt(0);
		final int videoType = args.getInt(1);
		DeveloperInterface.getInstance(mActivity).checkCtaAvailability(mActivity, minAward,videoType, new CheckCTAListener());
		callbackContextKeepCallback = callbackContext;
	}
	
	class CheckCTAListener implements CheckCtaAvailabilityResponseHandler {

		@Override
		public void onResponse(Boolean isConnected, Boolean isAvailable) {
			JSONObject result = new JSONObject();
  			try {
				result.put("connected", isConnected);
				result.put("available", isAvailable);
				PluginResult resultado = new PluginResult(PluginResult.Status.OK, result);
	  			resultado.setKeepCallback(true);
	  			TapsellVideo.callbackContextKeepCallback.sendPluginResult(resultado);
			} catch (JSONException e) {
				PluginResult resultado = new PluginResult(PluginResult.Status.ERROR, e.toString());
	  			resultado.setKeepCallback(true);
	  			TapsellVideo.callbackContextKeepCallback.sendPluginResult(resultado);
			}
		}
	}
}
