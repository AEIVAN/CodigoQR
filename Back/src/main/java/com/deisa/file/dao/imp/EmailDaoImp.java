package com.deisa.file.dao.imp;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.EmailDao;

@Service
public class EmailDaoImp implements EmailDao {

	@Autowired
	private JavaMailSender mail;
	
	@Override
	public String emailPassword(String[] emails, String order, String nombre, String url, String password, int descargar) {
		String rutaImagen = "";
		try {
			MimeMessage message = mail.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, CharEncoding.UTF_8);
			rutaImagen = getClass().getResource("/assets/LOGO_DEISA.jpg").getPath();
			String body = "<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "  <style>"
					+ "    .container {"
					+ "      display: flex;"
					+ "      flex-direction: column;"
					+ "      align-items: center;"
					+ "      justify-content: center;"
					+ "    }"
					+ ""
					+ "    .container img {"
					+ "      width: 100px;"
					+ "      height: 100px;"
					+ "    }"
					+ ""
					+ "    .container h1 {"
					+ "      margin-top: 10px;"
					+ "    }"
					+ "  </style>"
					+ "</head>"
					+ "<body>"
					+ "  <div class=\"container\">"
					+ "    <img src=\"cid:logoDeisa\" alt=\"Deisa\"/>"
					+ "    <h1>Desarrollo Ecológico Industrial </h1>"
					+ "  </div>"
					+ "  <hr>"
					+ "  <p>Para poder descargar su reporte "+ nombre +" de la "+ order +" siga estos pasos:</p>"
					+ "  <p>1- Acceda a la url <a href='" + url + "'> " + url + "</a> o escanee el código QR que se encuentra en su reporte.</p>"
					+ "  <p>2- Identifique el reporte quiere descargar y presione el botón de descarga que le corresponde.</p>"
					+ "  <p>3- Se abrirá una ventana en donde se deberá introducir la contraseña: <h4>" + password + "</h4> (El número de descarga está limitado a "+descargar+" por reporte)</p>"
					+ "  <p>4- Espere a que termine la descarga del reporte para poder visualizarlo.</p>"
					+ "  <h3>Si tiene alguna duda o problema, favor de comunicarse con su Asesor.</h3>"
					+ "</body>"
					+ "</html>" ; 
			// Origen
			//helper.setFrom("amesivan93@gmail.com");
			helper.setFrom("sistemadeisa@deisacv.com");
			// Destinos
			helper.setTo(emails);
			// Asunto
			helper.setSubject("Reporte " + order + " " + nombre);
			// Cuerpo
			helper.setText(body, true);
			// Adjuntos
			FileSystemResource resource = new FileSystemResource(new File(rutaImagen));
			helper.addInline("logoDeisa", resource);
			// Enviar correo
			mail.send(message);
			return body;
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
