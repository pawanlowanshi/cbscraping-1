package com.ibaseit.scraping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CbTimerTask extends TimerTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		CbTimerTask cbTask = new CbTimerTask();
		cbTask.initilization();
		
		Timer  timer = new Timer(true);
		timer.scheduleAtFixedRate(cbTask, 0, 60*1000);
		
		
		 try {
       Thread.sleep(180*1000);
   } catch (InterruptedException e) {
       e.printStackTrace();
   }
   timer.cancel();
   System.out.println("TimerTask cancelled");
		
	}
	
	private void initilization(){
		try {
			ClientDetail.extractGetWay();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void completeTask(){
		try {
			ClientDetail.executeGetWay();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		completeTask();
	}

}
