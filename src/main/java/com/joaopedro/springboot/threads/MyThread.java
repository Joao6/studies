package com.joaopedro.springboot.threads;

public class MyThread implements Runnable {

	private String name;
	private Integer sleepTime;

	public MyThread(String name, Integer sleepTime) {
		this.name = name;
		this.sleepTime = sleepTime;

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		try {
			for(int i = 0; i < 6; i++) {
				System.out.println(name + " contador " + i + ";");
				Thread.sleep(sleepTime);
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(name + " terminou a execução!");
	}
}
