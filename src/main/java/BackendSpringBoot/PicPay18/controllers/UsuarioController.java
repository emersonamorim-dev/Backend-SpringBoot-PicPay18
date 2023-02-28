package BackendSpringBoot.PicPay18.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendSpringBoot.PicPay18.model.Usuario;
import BackendSpringBoot.PicPay18.view.service.UsuarioService;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // MÉTODO GET
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // MÉTODO GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // MÉTODO POST
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    // MÉTODO PUT - ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario existingUsuario = optionalUsuario.get();
            existingUsuario.setNome(usuario.getNome());
            existingUsuario.setEmail(usuario.getEmail());
            existingUsuario.setSenha(usuario.getSenha());
            Usuario savedUsuario = usuarioService.save(existingUsuario);
            return ResponseEntity.ok(savedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //MÉTODO DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

