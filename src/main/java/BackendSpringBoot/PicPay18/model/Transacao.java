package BackendSpringBoot.PicPay18.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transacoes")
public class Transacao {

@Id
private String id;
private Double value;
private Usuario payer;
private Usuario payee;

public Transacao(Long long1, Long long2, Double double1, LocalDateTime localDateTime) {
}

public Transacao(Double value, Usuario payer, Usuario payee) {
    this.value = value;
    this.payer = payer;
    this.payee = payee;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public Double getValue() {
    return value;
}

public void setValue(Double value) {
    this.value = value;
}

public Usuario getPayer() {
    return payer;
}

public void setPayer(Usuario payer) {
    this.payer = payer;
}

public Usuario getPayee() {
    return payee;
}

public void setPayee(Usuario payee) {
    this.payee = payee;
}
}