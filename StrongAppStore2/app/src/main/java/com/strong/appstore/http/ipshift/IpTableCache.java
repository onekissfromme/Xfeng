package com.strong.appstore.http.ipshift;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.strong.appstore.common.Constants;
import com.strong.appstore.utils.SPUtils;

import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IpTableCache {

	private static Gson mGson = new Gson();
	private SPUtils mSPUtils ;

	public static void saveIptables(HashMap<String, ArrayList<String>> map) {
		Set set = map.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			ArrayList<String> ips = map.get(key);
			ArrayList<IpEntry> ipList = new ArrayList<IpEntry>();
			for (String ip : ips) {
				IpEntry ipEntry = new IpEntry();
				ipEntry.ipValue = ip;
				ipList.add(ipEntry);
			}
			String json = mGson.toJson(ipList);
			SPUtils.getInstance().put(key, json);
		}
	}

	public static void clearIptables() {
		SPUtils.getInstance().put(Constants.KEY_BALANCE_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_CLASS_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_ONLINECLASS_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_TUTOR_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_HOMEWORK_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_FILELEKE_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_GW_HOST, "");
		SPUtils.getInstance().put(Constants.KEY_API_LEKE_HOST, "");
	}
	
	public static void dnsParseForNative() {
		ArrayList<String> domainList = new ArrayList<String>();
		// domainList.add(Constants.KEY_APPCNSTRONG_HOST);
		domainList.add(Constants.KEY_BALANCE_HOST);
		domainList.add(Constants.KEY_CLASS_HOST);
		domainList.add(Constants.KEY_ONLINECLASS_HOST);
		domainList.add(Constants.KEY_TUTOR_HOST);
		domainList.add(Constants.KEY_HOMEWORK_HOST);
		domainList.add(Constants.KEY_FILELEKE_HOST);
		domainList.add(Constants.KEY_GW_HOST);
		domainList.add(Constants.KEY_API_LEKE_HOST);
		Executor executor = Executors.newFixedThreadPool(6);
		for (String domain : domainList) {
			parseDnsForDomain(domain, executor);
		}
	}
	
	private static void parseDnsForDomain(final String domain, Executor executor) {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				String dnsparseIp = getInetAddress(domain);
				String json = (String) SPUtils.getInstance().get(domain, "");
				ArrayList<IpEntry> ips = null;
				int containsPosition = -1;
				try {
					ips = mGson.fromJson(json, new TypeToken<ArrayList<IpEntry>>() {
					}.getType());
					if (ips != null && ips.size() > 0)
						for (int i = 0; i < ips.size(); i++) {
							IpEntry ipEntry = ips.get(i);
							if (ipEntry.ipValue.equals(dnsparseIp)) {
								containsPosition = i;
								break;
							}
						}

				} catch (Exception e) {
					e.printStackTrace();
				}
				if (containsPosition != -1) {
					ips.get(containsPosition).repeat++;
				} else {
					IpEntry ipEntry = new IpEntry();
					ipEntry.ipValue = dnsparseIp;
					if (ips == null) {
						ips = new ArrayList<IpEntry>();
					}
					ips.add(ipEntry);
				}
				String jsonNew = mGson.toJson(ips);
				SPUtils.getInstance().put(domain, jsonNew);
			}
		});
	}

	public static String getIpByDomain(String domain) {
		String json = (String) SPUtils.getInstance().get(domain, "");
		if (!TextUtils.isEmpty(json) && !JSONObject.NULL.equals(json)) {
			try {
				ArrayList<IpEntry> ips = mGson.fromJson(json,
						new TypeToken<ArrayList<IpEntry>>() {
						}.getType());
				if (ips.size() > 0) {
					sortByRepeat(ips);
					sortByFailedCount(ips);
					return ips.get(0).ipValue;
				} else {
					return domain;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return domain;
		}
		return domain;
	}

	public static void failedSave(String domain, String ip) {
		String json = (String) SPUtils.getInstance().get(domain, "");
		try {
			ArrayList<IpEntry> ips = mGson.fromJson(json,
					new TypeToken<ArrayList<IpEntry>>() {
					}.getType());
			int removePosition = -1;
			for (int i = 0; i < ips.size(); i++) {
				IpEntry ipEntry = ips.get(i);
				if (ipEntry.ipValue.equals(ip)) {
					ipEntry.failedCount++;
					if (ipEntry.failedCount == 3) {
						removePosition = i;
					}
					break;
				}
			}
			if (removePosition != -1) {
				ips.remove(removePosition);
			}
			String saveJson = mGson.toJson(ips);
			SPUtils.getInstance().put(domain, saveJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sortByRepeat(ArrayList<IpEntry> list) {
		Comparator<IpEntry> comparator = new Comparator<IpEntry>() {

			@Override
			public int compare(IpEntry arg0, IpEntry arg1) {
				if (arg0.getRepeat() > arg1.getRepeat()) {
					return -1;
				} else {
					return 1;
				}
			}
		};
		Collections.sort(list, comparator);
	}

	public static void sortByFailedCount(ArrayList<IpEntry> list) {
		Comparator<IpEntry> comparator = new Comparator<IpEntry>() {

			@Override
			public int compare(IpEntry arg0, IpEntry arg1) {
				if (arg0.getFailedCount() > arg1.getFailedCount()) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		Collections.sort(list, comparator);
	}

	public static String getInetAddress(String host) {
		String address = "";
		try {
			InetAddress inetAddress = InetAddress.getByName(host);
			if (inetAddress != null) {
				address = inetAddress.getHostAddress();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return address;
	}

}
