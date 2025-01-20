package com.qa.openkart.utils;

public class TimeUtils {

	
	public final static int DEFAULT_TIME = 500;
	public final static int DEFAULT_SHORT_TIME = 2;
	public final static int DEFAULT_MEDIUM_TIME = 5;
	public final static int DEFAULT_LONG_TIME = 10;
	public final static int MAX_TIME = 15;

//////////////////////////CUSTOM THREAD.SLEEP\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public static void threadDefaultTime() {
		try {
			Thread.sleep(DEFAULT_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void threadShortTime() {
		try {
			Thread.sleep(DEFAULT_SHORT_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void threadMediumTime() {
		try {
			Thread.sleep(DEFAULT_MEDIUM_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void threadLongTime() {
		try {
			Thread.sleep(DEFAULT_LONG_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void threadVeryLongTime() {
		try {
			Thread.sleep(MAX_TIME*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void threadSleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
