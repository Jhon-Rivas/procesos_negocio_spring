package com.procesos.negocio.jhon.controllers;

import com.procesos.negocio.jhon.models.Usuario;
import com.procesos.negocio.jhon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
//        Usuario usuario = new Usuario();
//        usuario.setNombre("Jhon");
//        usuario.setApellidos("Rivas");
//        usuario.setDocumento("1193223062");
//        usuario.setDireccion("clle 1 # 4-32");
//        usuario.setFechaNacimiento(new Date(2001,11,24));
//        usuario.setTelefono("3167863081");
        Optional<Usuario> usuario= UsuarioRepository.findById(id);
        return usuario;
    }

    @PostMapping("/usuario")
    public Usuario crearUsuario (@RequestBody Usuario usuario){
        UsuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios(){
        return UsuarioRepository.findAll();
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public List<Usuario> listarPorNombreApellido(@PathVariable String nombre){
        return UsuarioRepository.findAllByNombre(nombre);
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public List<Usuario> listarPorApellidos(@PathVariable String apellidos){
        return UsuarioRepository.findAllByApellidos(apellidos);
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreApellido(@PathVariable String nombre,@PathVariable String apellidos){
        return UsuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioBD = UsuarioRepository.findById(id).get();
        try{
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            UsuarioRepository.save(usuarioBD);
            return usuarioBD;
        }catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD = UsuarioRepository.findById(id).get();
        try{
           UsuarioRepository.delete(usuarioBD);
           return usuarioBD;
        }catch (Exception e){
            return null;
        }
    }
}
