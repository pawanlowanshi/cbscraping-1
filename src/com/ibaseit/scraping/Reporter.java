package com.ibaseit.scraping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class Reporter {
    
    public void generateReport(HttpResponse response)throws IllegalStateException, IOException {

	String date=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
	String timestamp=new SimpleDateFormat("HH_mm_ss").format(Calendar.getInstance().getTime());
	String resultPath = "C:\\Users\\pawanl\\Desktop\\"+date+timestamp;
	Files.createDirectories(Paths.get(resultPath));

	HttpEntity entity1 = response.getEntity();
	if (entity1 != null) {
	    System.out.println("Entity isn't null");

	    InputStream is = entity1.getContent();
	    String filePath = resultPath+"\\Report.xls";
	    FileOutputStream fos = new FileOutputStream(new File(filePath));

	    byte[] buffer = new byte[5600];
	    int inByte;
	    while ((inByte = is.read(buffer)) > 0)
		fos.write(buffer, 0, inByte);

	    is.close();
	    fos.close();
	}
	System.out.println("Excel Received..");
    }

}
