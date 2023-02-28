package BackendSpringBoot.PicPay18.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackendSpringBoot.PicPay18.view.dto.TransacaoDTO;
import BackendSpringBoot.PicPay18.view.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> listarTransacoes() {
        List<TransacaoDTO> transacoes = transacaoService.listarTransacoes();
        return ResponseEntity.ok().body(transacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> buscarTransacaoPorId(@PathVariable Long id) {
        TransacaoDTO transacao = transacaoService.buscarTransacaoPorId(id);
        if (transacao != null) {
            return ResponseEntity.ok().body(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> criarTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        TransacaoDTO transacaoCriada = transacaoService.criarTransacao(transacaoDTO);
        return ResponseEntity.created(URI.create("/transacao/" + transacaoCriada.getPayerId())).body(transacaoCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoDTO> atualizarTransacao(@PathVariable Long id, @RequestBody TransacaoDTO transacaoDTO) {
        TransacaoDTO transacaoAtualizada = transacaoService.atualizarTransacao(id, transacaoDTO);
        if (transacaoAtualizada != null) {
            return ResponseEntity.ok().body(transacaoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTransacao(@PathVariable Long id) {
        boolean sucesso = transacaoService.excluirTransacao(id);
        if (sucesso) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


