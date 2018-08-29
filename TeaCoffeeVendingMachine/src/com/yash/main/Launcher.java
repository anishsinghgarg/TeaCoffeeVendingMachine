package com.yash.main;

import com.yash.machine.actions.RequestProcessor;

public class Launcher {
	

	public static void main(String[] args) {
		RequestProcessor requestProcessor = new RequestProcessor();
		requestProcessor.requestProcessor();
	}

}
