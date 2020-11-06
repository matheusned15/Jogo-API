package br.com.jogo.api.luta;

import br.com.jogo.api.heroi.Heroi;
import br.com.jogo.api.heroi.HeroiService;
import br.com.jogo.api.vilao.Vilao;
import br.com.jogo.api.vilao.VilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LutaService {

    @Autowired
    private LutaRepository lutaRepository;

    @Autowired
    private VilaoService vilaoService;

    @Autowired
    private HeroiService heroiService;

    private final Random random = new Random();

    public List<Luta> buscaTodasLutas() {
        return lutaRepository.findAll();
    }

    public ResponseEntity<Luta> buscaLuta(Long codigo) {
        Optional<Luta> buscaLuta = lutaRepository.findById(codigo);
        return buscaLuta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    public Luta resultadoLuta(LutaDTO lutaDTO) {
        Luta luta;
        int ajusteHeroi = random.nextInt(20);
        int ajusteVilao = random.nextInt(20);

        if ((lutaDTO.getHeroi().getLevel() + ajusteHeroi) > (lutaDTO.getVilao().getLevel() + ajusteVilao)) {
            luta = heroiVencedor(lutaDTO);
        } else if (lutaDTO.getHeroi().getLevel() < lutaDTO.getVilao().getLevel()) {
            luta = vilaoVencedor(lutaDTO);
        } else {
            luta = random.nextBoolean() ? heroiVencedor(lutaDTO) : vilaoVencedor(lutaDTO);
        }

        luta.setData(LocalDateTime.now());
        return lutaRepository.save(luta);
    }

    private Luta heroiVencedor(LutaDTO lutaDTO) {
        Luta luta = new Luta();
        luta.setNomeVencedor(lutaDTO.getHeroi().getNome());
        luta.setImagemVencedor(lutaDTO.getHeroi().getImagem());
        luta.setLevelVencedor(lutaDTO.getHeroi().getLevel());

        luta.setNomePerdedor(lutaDTO.getVilao().getNome());
        luta.setImagemPerdedor(lutaDTO.getVilao().getImagem());
        luta.setLevelPerdedor(lutaDTO.getVilao().getLevel());
        luta.setTimeVencedor("Herois");
        luta.setTimePerdedor("Viloes");

        return luta;
    }

    private Luta vilaoVencedor(LutaDTO lutaDTO) {
        Luta luta = new Luta();
        luta.setNomeVencedor(lutaDTO.getVilao().getNome());
        luta.setImagemVencedor(lutaDTO.getVilao().getImagem());
        luta.setLevelVencedor(lutaDTO.getVilao().getLevel());

        luta.setNomePerdedor(lutaDTO.getHeroi().getNome());
        luta.setImagemPerdedor(lutaDTO.getHeroi().getImagem());
        luta.setLevelPerdedor(lutaDTO.getHeroi().getLevel());
        luta.setTimeVencedor("Viloes");
        luta.setTimePerdedor("Herois");
        return luta;
    }

    public ResponseEntity<LutaDTO> buscaLutaAleatoria() {
        Heroi heroi = buscaHeroiAleatorio();
        Vilao vilao = buscaVilaoAleatorio();
        LutaDTO lutaDTO = new LutaDTO();
        lutaDTO.setHeroi(heroi);
        lutaDTO.setVilao(vilao);
        return ResponseEntity.ok(lutaDTO);
    }

    public Heroi buscaHeroiAleatorio() {
        return heroiService.buscaHeroiAleatorio();
    }

    public Vilao buscaVilaoAleatorio() {
        return vilaoService.buscaVilaoAleatorio();
    }


}
