package BackendSpringBoot.PicPay18.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendSpringBoot.PicPay18.model.Pagamento;
import BackendSpringBoot.PicPay18.view.dto.PagamentoDTO;
import BackendSpringBoot.PicPay18.view.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PgamentoController {

    private List<PagamentoDTO> pagamentos = new ArrayList<>();
    private Long proximoId = 1L;

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public List<PagamentoDTO> listarPagamentos() {
        return pagamentos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable Long id) {
        Optional<PagamentoDTO> optionalPagamento = pagamentos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (optionalPagamento.isPresent()) {
            return ResponseEntity.ok(optionalPagamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        pagamentoDTO.setId(proximoId++);
        pagamentos.add(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        Optional<PagamentoDTO> optionalPagamento = Pagamento.stream()
                .filter(p -> p.getId().equals(id));
        if (optionalPagamento.isPresent()) {
            PagamentoDTO pagamentoExistente = optionalPagamento.get();
            pagamentoExistente.setDescricao(pagamentoDTO.getDescricao());
            pagamentoExistente.setValor(pagamentoDTO.getValor());
            return ResponseEntity.ok(pagamentoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Long id) {
        boolean removed = pagamentoService.removeById(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

