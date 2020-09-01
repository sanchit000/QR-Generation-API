//This class converts provided string into BitMatrix object and then returns it


package com.example.repeat4;
import java.io.IOException;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
public class QRGeneration {
	public  BitMatrix createQRImage(String encode)
			throws WriterException, IOException {
		
		
		int size = 500;
		Hashtable<EncodeHintType, Object> hashmap= new Hashtable<>();
		hashmap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		//hashmap.put(EncodeHintType.CHARACTER_SET,"ANSI");
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(encode, BarcodeFormat.QR_CODE, size, size, hashmap);
		return bitMatrix;
		
	}

}
