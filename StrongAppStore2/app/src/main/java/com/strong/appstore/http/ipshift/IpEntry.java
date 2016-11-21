package com.strong.appstore.http.ipshift;

import java.io.Serializable;

public class IpEntry implements Serializable {

	public String ipValue;
	
	public int repeat;
	
	public int failedCount;

	public String getIpValue() {
		return ipValue;
	}

	public void setIpValue(String ipValue) {
		this.ipValue = ipValue;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	
}
