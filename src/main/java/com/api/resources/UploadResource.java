package com.api.resources;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/upload")
@MultipartConfig
public class UploadResource {

	@Autowired
	private ServletContext servletContext;


	@PostMapping
	public ResponseEntity<Object> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part filePart;
		ResponseEntity<Object> resp = null;

//		try {
		// obtendo o arquivo, semelhante ao getParameter
		filePart = request.getPart("file");

		// obtendo o arquivo, semelhante ao estabelecimento
		String estabelecimento = request.getPart("estabelecimento").getSubmittedFileName();
		return resp;
		
	}


		
	private void uploadImage(Part filePart, String estabelecimento) throws IOException {
		String path = servletContext.getRealPath("/");
		String pasta = "/upload/" + estabelecimento + "/images/";

		// salvando no computador
		File diretorio = new File(path + "/upload");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento);
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		diretorio = new File(path + "/upload/" + estabelecimento + "/images");
		if (!diretorio.isDirectory()) {
			diretorio.mkdir();
		}

		filePart.write(path + pasta + filePart.getSubmittedFileName());
	}

	private void delArquivo(String pathNome) {
		File file = new File(pathNome);
		if (file.exists()) {
			file.delete();
		}
	}

}
