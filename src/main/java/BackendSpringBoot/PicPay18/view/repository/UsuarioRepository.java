package BackendSpringBoot.PicPay18.view.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import BackendSpringBoot.PicPay18.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
Usuario findByEmail(String email);

void deleteById(Long id);

Optional<Usuario> findById(Long id);


}
