package com.mysite.nutridex.db.tables;

import com.mysite.nutridex.R;
import com.mysite.nutridex.db.AbstractTable;
import com.mysite.nutridex.util.ResourceUtil;

public class Meat extends AbstractTable {

	String LABEL_NAME = ResourceUtil.getStringResource(R.string.meat);
	
	public String getLabelName() { return LABEL_NAME; }
}
