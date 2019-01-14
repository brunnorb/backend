package com.brunno.desafio.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunno.desafio.event.CriarEvento;

@Component
public class CriarEventoListener implements ApplicationListener<CriarEvento> {

	@Override
	public void onApplicationEvent(CriarEvento obj) {
		HttpServletResponse response = obj.getResponse();
		Long id = obj.getId();
		addHeaderLocation(response, id);
	}
	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());

	}
		
	
	

}
