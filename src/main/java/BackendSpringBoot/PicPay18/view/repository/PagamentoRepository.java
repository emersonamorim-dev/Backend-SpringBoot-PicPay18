package BackendSpringBoot.PicPay18.view.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import BackendSpringBoot.PicPay18.model.Pagamento;
import BackendSpringBoot.PicPay18.model.Transacao;

@Repository
public interface PagamentoRepository extends MongoRepository<Transacao, String> {

    Pagamento save(Pagamento pagamento);

}