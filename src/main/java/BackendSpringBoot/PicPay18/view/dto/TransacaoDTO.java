
package BackendSpringBoot.PicPay18.view.dto;

public class TransacaoDTO {
    private Long payerId;
    private Long payeeId;
    private Double value;

    public TransacaoDTO() {
    }

    public TransacaoDTO(String email, String email2, Double value2) {
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

