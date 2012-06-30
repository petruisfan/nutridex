package com.mysite.nutridex.db.tables;

import com.mysite.nutridex.R;
import com.mysite.nutridex.db.AbstractTable;
import com.mysite.nutridex.util.ResourceUtil;

public class GrainsAndPasta extends AbstractTable {

	String LABEL_NAME = ResourceUtil.getStringResource(R.string.grains);
	
	public String getLabelName() { return LABEL_NAME; }
}
