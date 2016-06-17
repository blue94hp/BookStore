package bookstore.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="http://localhost:9000")
@RequestMapping(value="/api")
public class Image {

	@RequestMapping(value = "/image/{imageUrl}", method = RequestMethod.GET, produces = "image/jpg")
	public @ResponseBody byte[] getImage(@PathVariable String imageUrl) {
		try {
			File file = new File("/home/anhtuan/Desktop/image/"+imageUrl);
			BufferedImage bufferedImage = ImageIO.read(file);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (IOException ie) {
			throw new RuntimeException(ie);
		}
	}
	
	@RequestMapping(value="/image",method= RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file){
		try{
			String filename = file.getOriginalFilename();
			File f = new File("/home/anhtuan/"+filename);
			byte[] bytes = file.getBytes();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(f));
			bufferedOutputStream.write(bytes);
			bufferedOutputStream.close();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(IOException ie){
			throw new RuntimeException(ie);
		}
	}
	
}
