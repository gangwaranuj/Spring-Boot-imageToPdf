package com.thinksys.dbservice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.thinksys.dbservice")
public class DbServiceApplication {

	public static void main(String[] args) throws IOException, DocumentException {

		String fileType=".png";
		String filename ="APP-21748(a)";
		String dir = "/home/thinksysuser/Pictures/";
		String desPath=createDuplicateImage(filename,dir,fileType);
		desPath=writeText(desPath);
		String a=createPdf(dir,filename,desPath);
		boolean b=deletedplicateImage(desPath);


	}


	public static String createDuplicateImage(String filename,String dir,String fileType) throws IOException {

		OutputStream os = null;
		InputStream is = null;
		String desFileName=filename+"copy"+fileType;
		String desPath=dir+desFileName;
		File inputFile=new File(dir+filename+fileType);
		try {
			is = new FileInputStream(inputFile);
			os = new FileOutputStream(new File(desPath));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
		return desPath;
	}

	public static String writeText(String destinationfile) throws IOException {

		File input = new File(destinationfile);
		final BufferedImage image = ImageIO.read(input);
		Graphics g = image.getGraphics();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		g.setColor(Color.RED);
		g.drawString("Hello World!", 100, 100);
		g.dispose();
		ImageIO.write(image, "png", input);
		return destinationfile;
	}


	public static String createPdf(String dir,String filename,String desfile) throws DocumentException, MalformedURLException, IOException {

		Document document = new Document();
		String output = dir+filename+".pdf";
		FileOutputStream fos = new FileOutputStream(output);
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		writer.open();
		document.open();
		Image img=	Image.getInstance(desfile);
		img.setAbsolutePosition(0, 0);
		document.add(img);Image.getInstance(desfile);
		document.close();
		writer.close();
		return "";
	}

	public static boolean deletedplicateImage(String desFile)
	{
		File file = new File(desFile);
		if(file.delete()){
			System.out.println(file.getName() + " is deleted!");
			return true;
		}else{
			System.out.println("Delete operation is failed.");
			return false;
		}
	}
}