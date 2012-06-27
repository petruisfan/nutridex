package com.mysite.nutridex.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.res.Resources;

public class FileUtil {

	private static Resources resources;
	
	
	public static void setResources(Resources res) {
		resources = res;
	}
	
	public static BufferedReader getBufferedReaderForAsset(String name) throws IOException {
		InputStream iS = resources.getAssets().open(name);
		BufferedReader result = new BufferedReader(new InputStreamReader(iS));
		return result;
	}
}
