package com.procesos.negocio.jhon.controllers;

import com.procesos.negocio.jhon.models.Usuario;
import com.procesos.negocio.jhon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
//        Usuario usuario = new Usuario();
//        usuario.setNombre("Jhon");
//        usuario.setApellidos("Rivas");
//        usuario.setDocumento("1193223062");
//        usuario.setDireccion("clle 1 # 4-32");
//        usuario.setFechaNacimiento(new Date(2001,11,24));
//        usuario.setTelefono("3167863081");
        Optional<Usuario> usuario= UsuarioRepository.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario (@RequestBody Usuario usuario){
        try{
            UsuarioRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios(){
        List<Usuario> usuarios = UsuarioRepository.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listarPorNombreApellido(@PathVariable String nombre){
        List<Usuario> usuarios = UsuarioRepository.findAllByNombre(nombre);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuario/apellidos/{apellidos}")
    public ResponseEntity listarPorApellidos(@PathVariable String apellidos){
        List<Usuario> usuarios = UsuarioRepository.findAllByApellidos(apellidos);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellido(@PathVariable String nombre,@PathVariable String apellidos){
        List<Usuario> usuarios = UsuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioBD = UsuarioRepository.findById(id);
        if (usuarioBD.isPresent()){
            try {
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                UsuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD,HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBD = UsuarioRepository.findById(id);
        if (usuarioBD.isPresent()){
            UsuarioRepository.delete(usuarioBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
