package br.com.jogo.api.luta;

import br.com.jogo.api.heroi.Heroi;
import br.com.jogo.api.vilao.Vilao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LutaDTO {

    @NotNull
    private Heroi heroi;

    @NotNull
    private Vilao vilao;
}
