package br.com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gvendas.gestaovendas.entity.Cliente;
import br.com.gvendas.gestaovendas.exception.RegraNegocioException;
import br.com.gvendas.gestaovendas.repository.ClienteRepository;

@Service
public class ClienteService  {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> buscarPorCodigo(Long codigo) {
		return clienteRepository.findById(codigo);
	}
	
	public Cliente salvar(Cliente cliente) {
	validarDuplicado(cliente);
	return clienteRepository.save(cliente);
	}
		
	public Cliente atualizar(Long codigo, Cliente cliente) {
	Cliente clienteNovo = validarSeExiste(codigo);
	validarDuplicado(cliente);
	BeanUtils.copyProperties(cliente, clienteNovo, "codigo");
	return clienteRepository.save(clienteNovo);
	}
	
	public void deletar(Long codigo) {
		clienteRepository.deleteById(codigo); // sem verificar se existe pra economizar recursos 
	}


//	TODO: Validar duplicado por CPF 
	private void validarDuplicado(Cliente cliente) {
		Cliente est = clienteRepository.findByNome(cliente.getNome());
		if(est != null&&est.getCodigo()!=cliente.getCodigo()) {
			throw new RegraNegocioException("Cliente já cadastrado!");
		}
	}
	
	private Cliente validarSeExiste(Long codigo) {
	Optional<Cliente> c = clienteRepository.findById(codigo);
		if(c.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return c.get();
	}
	
	
	
	
}
