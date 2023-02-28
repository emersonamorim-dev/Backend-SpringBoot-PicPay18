
package BackendSpringBoot.PicPay18.view.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BackendSpringBoot.PicPay18.model.Transacao;
import BackendSpringBoot.PicPay18.model.Usuario;
import BackendSpringBoot.PicPay18.view.dto.TransacaoDTO;
import BackendSpringBoot.PicPay18.view.repository.TransacaoRepository;
import BackendSpringBoot.PicPay18.view.repository.UsuarioRepository;

@Service
public class TransacaoService {

    @Autowired
    private EmailNotificacaoService emailNotificacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private KafkaTemplate<String, TransacaoDTO> kafkaTemplate;

    private List<TransacaoDTO> transacoes;

    public boolean authorizeTransaction() {
        // Lógica para autorizar a transação
        return true;
    }

    @Transactional(rollbackFor = TransactionException.class)
    public void transferMoney(Long payerId, Long payeeId, Double value) throws TransactionException {
        Usuario payer = usuarioRepository.findById(payerId.toString())
                .orElseThrow(() -> new TransactionException("Pagador não encontrado"));
        Usuario payee = usuarioRepository.findById(payeeId.toString())
                .orElseThrow(() -> new TransactionException("Receptor não encontrado"));

        if (payer.getWallet() < value) {
            throw new TransactionException("Saldo insuficiente");
        }

        if (payer.getId().equals(payee.getId())) {
            throw new TransactionException("Não é permitido transferir para si mesmo");
        }

        payer.setWallet(payer.getWallet() - value);
        payee.setWallet(payee.getWallet() + value);

        usuarioRepository.save(payer);
        usuarioRepository.save(payee);

        Transacao transacao = new Transacao(payerId, payeeId, value, null);
        transacaoRepository.save(transacao);

        TransacaoDTO transacaoDTO = new TransacaoDTO(payer.getEmail(), payee.getEmail(), value);
        kafkaTemplate.send("transacoes", transacaoDTO);

        if (payee.isMerchant()) {
            emailNotificacaoService.sendNotification(payee.getEmail(), "Recebimento de pagamento",
                    String.format("Você recebeu um pagamento de %s", payer.getNome()));
        } else {
            emailNotificacaoService.sendNotification(payee.getEmail(), "Recebimento de pagamento",
                    String.format("Você recebeu um pagamento de %s", payer.getNome()));
            emailNotificacaoService.sendNotification(payer.getEmail(), "Envio de pagamento",
                    String.format("Você enviou um pagamento para %s", payee.getNome()));
        }
    }

    public class TransactionException extends RuntimeException {
        public TransactionException(String message) {
            super(message);
        }
    }

    @Transactional(rollbackFor = TransactionException.class)
    public void realizarTransacao(TransacaoDTO transacaoDTO) throws TransactionException {

        Long payerId = transacaoDTO.getPayerId();
        Long payeeId = transacaoDTO.getPayeeId();
        Double value = transacaoDTO.getValue();

        if (!authorizeTransaction()) {
            throw new TransactionException("Transação não autorizada");
        }

        transferMoney(payerId, payeeId, value);

        Transacao transacao = new Transacao(transacaoDTO.getPayerId(), transacaoDTO.getPayeeId(), transacaoDTO.getValue(), LocalDateTime.now());

        
        transacaoRepository.save(transacao);
    }

    public List<TransacaoDTO> listarTransacoes() {
        return new ArrayList<>();
    }
    

    public TransacaoDTO buscarTransacaoPorId(Long id) {
        return new TransacaoDTO();
    }
    

    public TransacaoDTO criarTransacao(TransacaoDTO transacaoDTO) {
        transacoes.add(transacaoDTO);
        return transacaoDTO;
    }

    public TransacaoDTO atualizarTransacao(Long id, TransacaoDTO transacaoDTO) {
        for (int i = 0; i < transacoes.size(); i++) {
            if (transacoes.get(i).getPayerId().equals(id)) {
                transacoes.set(i, transacaoDTO);
                return transacaoDTO;
            }
        }
        return null;
    }

    public boolean excluirTransacao(Long id) {
        return false;
    }

}
