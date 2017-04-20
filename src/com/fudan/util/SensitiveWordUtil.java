package com.fudan.util;

import java.util.Set;

import com.fudan.util.sensitiveWord.SensitivewordFilter;

public class SensitiveWordUtil {
	
	/**
	 * 实现敏感词过滤功能。敏感词文件的地址在SensitiveWordInit中设置
	 * 存在敏感词返回true
	 * 2015/07/29
	 * @param str
	 * @return
	 */
	public boolean hasSensitiveWord(String str) {
		SensitivewordFilter filter = new SensitivewordFilter();
		Set<String> set = filter.getSensitiveWord(str, 1);
		return set.size() > 0;
	}

}
