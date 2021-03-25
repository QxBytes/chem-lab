package com.qxbytes.chemlab;

public class TestMain {
	public static void main (String[] args) {
		try {
			ExcelHandler.testHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
