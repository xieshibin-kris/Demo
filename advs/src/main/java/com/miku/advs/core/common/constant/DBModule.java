package com.miku.advs.core.common.constant;

public enum DBModule {
	RTDBINFO_ADVS,
	RTDBINFO_SYS,
	UNKNOW;//未知
	
	
	public static DBModule valueOf(int flagType){
		switch(flagType){
		case 0:
			return RTDBINFO_ADVS;
		case 1:
			return RTDBINFO_SYS;
		default:
			return UNKNOW;
		}
	}
}
