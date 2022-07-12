package org.generation.ecommerce.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.*;



@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuario(Long id){
		return usuarioRepository.findById(id).orElseThrow(
				()-> new IllegalStateException("El usuario con el id [ " + 
		id + " ] no existe.") 
				);
		}
	
	public Usuario deleteUsuario(Long id) {
		Usuario tmpUsuario = null;
		if(usuarioRepository.existsById(id)){
			tmpUsuario = usuarioRepository.findById(id).get();
			usuarioRepository.deleteById(id);
		}
		return tmpUsuario;
	}
	
	public Usuario addUsuario(Usuario usuario) {
		Usuario tmpUsuario = null;
		Optional<Usuario>userByUsername = usuarioRepository.findByUsername(usuario.getUsername());
		if(userByUsername.isPresent()) {
			throw new IllegalStateException("El usuario con el nombre [ " +
		usuario.getUsername() + " ] Ya existe");
			
		}else {
			usuarioRepository.save(usuario);
			tmpUsuario = usuario;
		}
		
		return tmpUsuario;
	}
	
	public Usuario updateUsuario(Long id, String password, String newPassword) {
		Usuario tmpUsuario = null ;
		if(usuarioRepository.existsById(id)) {
			tmpUsuario = usuarioRepository.findById(id).get();
			if(tmpUsuario.getPassword().equals(password)){
				tmpUsuario.setPassword(newPassword);
				usuarioRepository.save(tmpUsuario);
			}else {
				System.out.println("Error al comparar las contraseñas");
			}
		}
		
		return tmpUsuario;
	}

	public boolean validateUsuario(Usuario usuario) {
		boolean res = false;
		Optional<Usuario> userByUsername = usuarioRepository.findByUsername(usuario.getUsername());
		if(userByUsername.isPresent()){
			Usuario u  = userByUsername.get();
			if(u.getPassword().equals(usuario.getPassword())){
				res = true;
			}
		}
		return res; 
	}

}
