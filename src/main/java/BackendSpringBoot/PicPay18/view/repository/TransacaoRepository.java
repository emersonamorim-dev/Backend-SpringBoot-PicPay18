package BackendSpringBoot.PicPay18.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BackendSpringBoot.PicPay18.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {

}
