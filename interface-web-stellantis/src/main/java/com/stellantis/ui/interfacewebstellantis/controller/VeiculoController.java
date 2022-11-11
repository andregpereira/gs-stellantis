package com.stellantis.ui.interfacewebstellantis.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stellantis.ui.interfacewebstellantis.model.Veiculo;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	@GetMapping("/lista")
	public ModelAndView lista() {
		ModelAndView modelView = new ModelAndView("/veiculo/lista");

		try {
			URL url = new URL("http://localhost:8080/veiculo/lista");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			String json = "";
			Scanner scanner = new Scanner(url.openStream());

			while (scanner.hasNext()) {
				json += scanner.nextLine();
			}

			scanner.close();

			ObjectMapper mapper = new ObjectMapper();

			List<Veiculo> listaVeiculos = Arrays.asList(mapper.readValue(json, Veiculo[].class));

			modelView.addObject("listaVeiculos", listaVeiculos);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return modelView;
	}

}
