
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.*;


public class QRCode {

    public static void main(String[] args) throws WriterException, IOException, NotFoundException
    {

        // The data that the QR code will contain
        String data = "www.geeksforgeeks.org";

        // The path where the image will get saved
//        String path = "/Users/zhangxin/pic/qrcode2.jpg";
//        String path = "/Users/zhangxin/data_md/barcodes/JT00001_0.jpg";
        String path = "/Users/zhangxin/data_md/barcodes/JT00007.jpg";
        System.out.println(path);
        // Encoding charset
        String charset = "UTF-8";

        File filePath = new File(path);
        String decodedText = decodeQRCode(filePath);
//        System.out.println(decodedText);

    }

    private static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println(result.toString());
            System.out.println(result.getText());
            ResultPoint[] pts = result.getResultPoints();
            for (int i = 0; i < pts.length; i++) {
                System.out.println(i + " " + pts[i]);
            }
//            System.out.println(result.getResultPoints().toString());
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }

}
