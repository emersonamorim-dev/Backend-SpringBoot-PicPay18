package BackendSpringBoot.PicPay18.model;

import java.util.Date;
import java.util.Optional;

public class Pagamento {

    private Date dataAprovacao;

    public Usuario getUsuario() {
        return null;
    }

    public double getValor() {
        return 0;
    }

    public void setStatus(String aprovado) {
    }

    public void setDataAprovacao(Date date) {
        this.dataAprovacao = date;
    }

    public Object getCartaoCredito() {
        return null;
    }

    public static Optional<BackendSpringBoot.PicPay18.view.dto.PagamentoDTO> stream() {
        return null;
    }

    public static boolean removeIf(Object object) {
        return false;
    }

    
}
