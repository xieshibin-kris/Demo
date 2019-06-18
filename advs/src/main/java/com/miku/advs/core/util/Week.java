package com.miku.advs.core.util;

public enum Week {
	UNKNOW,
	SUNDAY,
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY;
	
	public static Week valueOf(int week){
		switch(week){
		case 1:
			return SUNDAY;
		case 2:
			return MONDAY;
		case 3:
			return TUESDAY;
		case 4:
			return WEDNESDAY;
		case 5:
			return THURSDAY;
		case 6:
			return FRIDAY;
		case 7:
			return SATURDAY;
		default:
			return UNKNOW;
		}
	}
}
