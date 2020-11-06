package br.com.jogo.api.luta;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lutas")
@Api(tags = "API - LUTAS")
public class LutaController {

    @Autowired
    LutaService lutaService;

    @GetMapping
    public List<Luta> buscaLutas() {
        return lutaService.buscaTodasLutas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Luta> buscaLuta(@PathVariable Long codigo) {
        return lutaService.buscaLuta(codigo);
    }

    @GetMapping("/aleatorio")
    public ResponseEntity<LutaDTO> buscaLutaAleatoria() {
        return lutaService.buscaLutaAleatoria();
    }

    @PostMapping
    public Luta criaLuta(@RequestBody @Valid LutaDTO lutaDTO) {
        return lutaService.resultadoLuta(lutaDTO);
    }


}
