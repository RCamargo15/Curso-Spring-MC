package com.rcamargo15.cursoSpringBoot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcamargo15.cursoSpringBoot.domain.Pedido;
import com.rcamargo15.cursoSpringBoot.repositories.PedidoRepository;
import com.rcamargo15.cursoSpringBoot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscarPedido(Integer cod) {
		Optional<Pedido> obj = repo.findById(cod);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + cod + ", Tipo : " + Pedido.class.getName()));
	}
}
