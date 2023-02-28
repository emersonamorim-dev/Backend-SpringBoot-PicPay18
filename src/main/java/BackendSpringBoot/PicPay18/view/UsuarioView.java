package BackendSpringBoot.PicPay18.view;

import java.math.BigDecimal;

public class UsuarioView {

    private String id;
    private String nome;
    private String login;
    private String senha;
    private String cartaoCredito;
    private BigDecimal saldo;
    private Double wallet;
    private String cpf;


    public UsuarioView() {}

    public UsuarioView(String id, String nome, String login, String senha, String cartaoCredito, BigDecimal saldo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cartaoCredito = cartaoCredito;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }
    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
}
