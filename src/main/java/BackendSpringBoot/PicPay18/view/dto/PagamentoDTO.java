package BackendSpringBoot.PicPay18.view.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import BackendSpringBoot.PicPay18.model.Pagamento;

public class PagamentoDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private Optional<PagamentoDTO> pagamento;

    public PagamentoDTO() {
        // Construtor padrão vazio
    }

    public PagamentoDTO(Long id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "PagamentoDTO [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";

    }
    private Optional<PagamentoDTO> encontrarPagamentoPendente(List<PagamentoDTO> pagamentos) {
        return pagamentos.stream()
            .filter(pagamento -> pagamento.getStatus().equals("pendente"))
            .findFirst();
    }
    

    private String getStatus() {
        return "Ativo";
    }

    public static boolean removeIf(Object object) {
        return false;
    }
    
}
