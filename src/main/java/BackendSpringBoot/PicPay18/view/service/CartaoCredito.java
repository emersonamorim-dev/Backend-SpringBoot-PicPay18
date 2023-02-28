package BackendSpringBoot.PicPay18.view.service;
import java.util.Date;

public class CartaoCredito {
    private String numero;
    private Date dataValidade;
    private String codigoSeguranca;
    private boolean ativo;

    public CartaoCredito(String numero, Date dataValidade, String codigoSeguranca, boolean ativo) {
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.codigoSeguranca = codigoSeguranca;
        this.ativo = ativo;
    }

    public String getNumero() {
        return numero;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

