package com.stellantis.ui.interfacewebstellantis.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return modelView;
	}

	@GetMapping("/criar")
	public ModelAndView criar() {
		ModelAndView modelView = new ModelAndView("/veiculo/criar");
		return modelView;
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") String id) {
		ModelAndView modelView = new ModelAndView("/veiculo/alterar");

		try {
			URL url = new URL("http://localhost:8080/veiculo/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Basic U3RlbGxhbnRpczpTdGVsbGFudGlz");
			conn.setRequestMethod("GET");
			conn.connect();

			String json = "";
			Scanner scanner = new Scanner(conn.getInputStream());

			while (scanner.hasNext()) {
				json += scanner.nextLine();
			}

			scanner.close();

			ObjectMapper mapper = new ObjectMapper();

			Veiculo veiculo = mapper.readValue(json, Veiculo.class);

			modelView.addObject(veiculo);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return modelView;
	}

}
