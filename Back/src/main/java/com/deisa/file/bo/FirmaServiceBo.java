package com.deisa.file.bo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Matrix;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class FirmaServiceBo {

	public String keyPairGenerator() throws Exception {
		try {
			Security.addProvider(new BouncyCastleProvider());

			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
			keyPairGenerator.initialize(2048); // Tamaño de clave
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			System.out.println("publicKey" + publicKey);
			System.out.println("privateKey" + privateKey);

			String publicKeyPath = "C:\\Users\\ae_iv\\Desktop\\keys\\publicKey.pem"; // Cambiar por la ruta deseada
			String privateKeyPath = "C:\\Users\\ae_iv\\Desktop\\keys\\privateKey.pem"; // Cambiar por la ruta deseada

			// Guarda las claves en archivos
			saveKeyToFile(publicKey, publicKeyPath);
			saveKeyToFile(privateKey, privateKeyPath);

		} catch (Exception e) {
			return "";
		}
		return "";
	}

	private static void saveKeyToFile(Key key, String filename) throws Exception {
		FileOutputStream fos = new FileOutputStream(filename);
		fos.write(key.getEncoded());
		fos.close();
	}

	public String digitalSignature() throws Exception {
		try {
			Security.addProvider(new BouncyCastleProvider());

			String message = "Este es el contenido a firmar.";

			// Cargar la clave privada desde algún almacén seguro
			PrivateKey privateKey = loadPrivateKeyFromPEM("C:\\Users\\ae_iv\\Desktop\\keys\\privateKey.pem");
			PublicKey publicKey = loadPublicKeyFromPEM("C:\\Users\\ae_iv\\Desktop\\keys\\publicKey.pem");

			Signature signature = Signature.getInstance("SHA256withRSA", "BC");
			signature.initSign(privateKey);

			signature.update(message.getBytes());
			byte[] digitalSignature = signature.sign();

			String signatureInBase64 = Base64.getEncoder().encodeToString(digitalSignature);
			System.out.println("Firma digital en Base64: " + signatureInBase64);
			return signatureInBase64;
		} catch (Exception e) {
			return "";
		}
	}

	private static PrivateKey loadPrivateKeyFromPEM(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(spec);
	}

	private static PublicKey loadPublicKeyFromPEM(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(spec);
	}

	public String verifySignature(String firma) throws Exception {
		try {
			Security.addProvider(new BouncyCastleProvider());

			String receivedMessage = "Este es el contenido a firmar.";
			System.out.println("Firma digital en Base64: " + firma);
			byte[] receivedSignature = Base64.getDecoder().decode(firma);

			// Cargar la clave pública desde algún almacén seguro
			PrivateKey privateKey = loadPrivateKeyFromPEM("C:\\Users\\ae_iv\\Desktop\\keys\\privateKey.pem");
			PublicKey publicKey = loadPublicKeyFromPEM("C:\\Users\\ae_iv\\Desktop\\keys\\publicKey.pem");

			Signature signature = Signature.getInstance("SHA256withRSA", "BC");
			signature.initVerify(publicKey);

			signature.update(receivedMessage.getBytes());

			boolean isValid = signature.verify(receivedSignature);
			System.out.println("La firma es válida: " + isValid);

		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public String test(String test) throws Exception {
		System.out.println("test " + test);
		try {
			return test;
		} catch (Exception e) {
			throw (new Exception("*** Class " + this.getClass().getName() + " Method test ***  Excepcion : " + e));
		}
	}

	public String singTest() throws Exception {
		System.out.println("test singTest");
		String archivoPDFExistente = "C:\\Users\\ae_iv\\Desktop\\Pruebas\\Documento_Firma.pdf";
		String imagenFirma = "C:\\Users\\ae_iv\\Desktop\\Pruebas\\Firma1.png";

		try {
			PdfDocument pdfDocument = new PdfDocument(new PdfReader(archivoPDFExistente),
					new PdfWriter("C:\\Users\\ae_iv\\Desktop\\Pruebas\\archivo_firmado.pdf"));
			Document document = new Document(pdfDocument);

			float xPosition = calculateXPositionBasedOnContent(pdfDocument);
			float yPosition = calculateYPositionBasedOnContent(pdfDocument);

			ImageData imageData = ImageDataFactory.create(imagenFirma);
			Image image = new Image(imageData);
			image.scaleToFit(200, 200);

			image.setFixedPosition(xPosition, yPosition);

			document.add(image);

			document.close();

			System.out.println("Imagen (firma) agregada al PDF existente correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagenFirma;
	}

	private static float calculateXPositionBasedOnContent(PdfDocument pdfDocument) {
		// String palabraBuscar = "<Firma>";
		//String palabraBuscar = "SASD";
		//String palabraBuscar = "POI";
		//String palabraBuscar = "WW";
		return calculatePositionBasedOnContent(pdfDocument, palabraBuscar, true);
	}

	private static float calculateYPositionBasedOnContent(PdfDocument pdfDocument) {
		// String palabraBuscar = "<Firma>";
		//String palabraBuscar = "SASD";
		//String palabraBuscar = "POI";
		//String palabraBuscar = "WW";
		return calculatePositionBasedOnContent(pdfDocument, palabraBuscar, false);
	}

	private static float calculatePositionBasedOnContent(PdfDocument pdfDocument, String palabraBuscar,
			boolean calcularX) {
		for (int pageNum = 1; pageNum <= pdfDocument.getNumberOfPages(); pageNum++) {
			PdfPage page = pdfDocument.getPage(pageNum);
			List<TextRenderInfo> textInfos = new ArrayList<>();
			PdfTextExtractor.getTextFromPage(page, new MyTextExtractionStrategy(textInfos));

			for (TextRenderInfo textInfo : textInfos) {
				String text = textInfo.getText();
				System.out.println("text " + text);
				if (palabraBuscar.contains(text)) {
					Matrix textMatrix = textInfo.getTextMatrix();
					float elementPosition = calcularX ? textMatrix.get(Matrix.I31) : textMatrix.get(Matrix.I32);
					return elementPosition;
				}
			}
		}

		return calcularX ? 1 : 1;
	}

	private static class MyTextExtractionStrategy implements ITextExtractionStrategy {
		private final List<TextRenderInfo> textInfos;

		public MyTextExtractionStrategy(List<TextRenderInfo> textInfos) {
			this.textInfos = textInfos;
		}

		@Override
		public void eventOccurred(IEventData data, EventType type) {
			if (type.equals(EventType.RENDER_TEXT)) {
				TextRenderInfo textRenderInfo = ((TextRenderInfo) data);
				System.out.println("textRenderInfo " + textRenderInfo.getText());
				textInfos.add((TextRenderInfo) data);
				((TextRenderInfo) data).preserveGraphicsState();
			}
		}

		@Override
		public String getResultantText() {
			return null;
		}

		@Override
		public Set<EventType> getSupportedEvents() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
