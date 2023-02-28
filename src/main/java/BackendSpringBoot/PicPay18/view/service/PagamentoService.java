package BackendSpringBoot.PicPay18.view.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BackendSpringBoot.PicPay18.model.Pagamento;
import BackendSpringBoot.PicPay18.model.Usuario;
import BackendSpringBoot.PicPay18.view.repository.PagamentoRepository;

@Service
@Transactional
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(@Autowired PagamentoRepository pagamentoRepository) {
        Objects.requireNonNull(pagamentoRepository, "Pagamento não pode ser nulo");
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento efetuarPagamento(Pagamento pagamento) {
        Objects.requireNonNull(pagamento, "Pagamento não pode ser nulo");

        if (pagamento.getValor() <= 0 || pagamento.getCartaoCredito() == null) {
            throw new IllegalArgumentException("Pagamento inválido");
        }

        if (!validarCartaoCredito(pagamento.getCartaoCredito())) {
            throw new IllegalArgumentException("Cartão de crédito inválido");
        }

        if (!usuarioAutenticado(pagamento.getUsuario())) {
            throw new IllegalStateException("Usuário não autenticado");
        }

        if (!verificarFundosSuficientes(pagamento.getUsuario(), pagamento.getValor())) {
            throw new IllegalStateException("Fundos insuficientes");
        }

        pagamento.setStatus(PagamentoStatus.APROVADO);
        pagamento.setDataAprovacao(new Date());

        return pagamentoRepository.save(pagamento);
    }

    private boolean validarCartaoCredito(Object cartaoCredito) {
        return false;
    }

    private boolean verificarFundosSuficientes(Usuario usuario, double valor) {
        return false;
    }

    private boolean validarCartaoCredito(CartaoCredito cartaoCredito) {
        if (cartaoCredito == null) {
            return false;
        }

        String numeroCartao = cartaoCredito.getNumero();
        if (numeroCartao == null || numeroCartao.isEmpty()) {
            return false;
        }

        Date dataValidade = cartaoCredito.getDataValidade();
        if (dataValidade == null || dataValidade.before(new Date())) {
            return false;
        }

        String codigoSeguranca = cartaoCredito.getCodigoSeguranca();
        if (codigoSeguranca == null || codigoSeguranca.length() != 3) {
            return false;
        }

        if (!cartaoCredito.isAtivo()) {
            return false;
        }

        return true;
    }
    private boolean usuarioAutenticado(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
    
        String nomeDeUsuario = usuario.getNomeDeUsuario();
        String senha = usuario.getSenha();
    
        try {
            List<Usuario> usuariosValidos = obterUsuariosValidos();
    
            return usuariosValidos.stream()
                    .anyMatch(u -> u.getNomeDeUsuario().equals(nomeDeUsuario) && u.getSenha().equals(senha));
        } catch (Exception e) {
            // tratamento de exceção
            return false;
        }
    }

    // lógica para adicionar usuários válidos à lista
    private List<Usuario> obterUsuariosValidos() {
        List<Usuario> usuariosValidos = new ArrayList<>();
        return usuariosValidos;
    }

    public boolean removeById(Long id) {
        return false;
    }
}
    