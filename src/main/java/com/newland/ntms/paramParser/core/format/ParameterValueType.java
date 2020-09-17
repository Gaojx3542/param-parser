package com.newland.ntms.paramParser.core.format;

/**
 * 参数值的类型<p>
 * @author Gaojx
 *
 */
public enum ParameterValueType {
	/**
	 * 字符串类型
	 */
	STRING(1),
	/**
	 * bool型
	 */
	BOOLEAN(2),
	/**
	 * 数字
	 */
	NUMBER(3),
	/**
	 * 16进制
	 */
	HEX(4),
	/**
	 * 引用
	 */
	REFERENCE(5),
	/**
	 * 文件
	 */
	FILE(6);
	
	private int code;
	
	ParameterValueType(int code){
		this.code = code;
	}
	
	public static final ParameterValueType valueOf(int code) {
		switch (code) {
			case 1:
				return STRING;
			case 2:
				return BOOLEAN;
			case 3:
				return NUMBER;
			case 4:
				return HEX;
			case 5:
				return REFERENCE;
			case 6:
				return FILE;

			default:
				throw new IllegalArgumentException("not supported code:["+code+"]");
		}
	}

	public int getCode() {
		return code;
	}
	
}
