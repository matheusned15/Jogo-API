package br.com.jogo.api.heroi;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class HeroiService {

    @Autowired
    private HeroiRepository heroiRepository;

    public List<Heroi> buscaTodosHerois() {
        return heroiRepository.findAll();
    }

    public ResponseEntity<Heroi> buscaHeroi(Long codigo) {
        Optional<Heroi> buscaHeroi = heroiRepository.findById(codigo);
        return buscaHeroi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    public Heroi criaHeroi(@Valid Heroi heroi) {
        return heroiRepository.save(heroi);
    }

    public ResponseEntity<Heroi> atualizaHeroi(Long codigo, @Valid Heroi heroi) {
        Optional<Heroi> buscaHeroi = heroiRepository.findById(codigo);
        if (buscaHeroi.isPresent()) {
            BeanUtils.copyProperties(heroi, buscaHeroi.get(), "codigo");
            Heroi salva = heroiRepository.save(buscaHeroi.get());
            return ResponseEntity.ok(salva);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Heroi> deletaHeroi(Long codigo) {
        Optional<Heroi> buscaHeroi = heroiRepository.findById(codigo);
        if (buscaHeroi.isPresent()) {
            heroiRepository.deleteById(codigo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
