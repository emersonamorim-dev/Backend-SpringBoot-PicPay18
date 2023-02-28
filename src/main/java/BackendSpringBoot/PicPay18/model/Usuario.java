package BackendSpringBoot.PicPay18.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private Double wallet;
    private String cartaocredito;
    private boolean isMerchant;
    private List<String> seguidores;

    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String email, Double wallet, boolean isMerchant) {
        this.nome = nome;
        this.email = email;
        this.wallet = wallet;
        this.isMerchant = isMerchant;
        

    }

    // Getters e setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartaocredito() {
        return cartaocredito;
    }

    public void setCartaocredito(String cartaocredito) {
        this.cartaocredito = cartaocredito;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public boolean isMerchant() {
        return isMerchant;
    }

    public void setMerchant(boolean isMerchant) {
        this.isMerchant = isMerchant;
    }

    public List<String> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<String> seguidores) {
        this.seguidores = seguidores;
    }

    // Métodos adicionais
    public void adicionarSeguidor(String idSeguidor) {
        this.seguidores.add(idSeguidor);
    }

    public String getCartaoCredito() {
        return "1234 5678 9012 3456";
    }

    // código para atribuir o valor do cartão de crédito ao usuário
    public void setCartaoCredito(String cartaoCredito) {
    }

    // código para atribuir o valor do CVV do cartão de crédito ao usuário
    public void setCartaoCvv(int cartaoCvv) {
        Usuario usuario = new Usuario();
        usuario.setCartaoCvv(123);
    }

    // código para atribuir a data de expiração do cartão de crédito ao usuário
    public void setCartaoExpiracao(String cartaoExpiracao) {
        Usuario usuario = new Usuario();
        usuario.setCartaoExpiracao("12/28");
    }

    // código para definir o nome impresso no cartão de crédito do usuário
    public void setCartaoNome(String nome) {
        Usuario usuario = new Usuario();
        usuario.setCartaoNome("Emerson Amorim");
    }
    
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
                + ", wallet=" + wallet + ", cartaocredito=" + cartaocredito + ", isMerchant=" + isMerchant
                + ", seguidores=" + seguidores + '}';
                
    }

    public String getNomeDeUsuario() {
        return getNomeDeUsuario();
    }

    public double getSaldo() {
        return getSaldo();
    }

}
