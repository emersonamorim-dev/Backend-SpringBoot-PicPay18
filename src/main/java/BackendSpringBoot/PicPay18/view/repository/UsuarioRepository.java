package BackendSpringBoot.PicPay18.view.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSpringBoot.PicPay18.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);

    void deleteById(Long id);

    Optional<Usuario> findById(Long id);

}
