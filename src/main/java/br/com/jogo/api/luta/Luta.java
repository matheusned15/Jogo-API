package br.com.jogo.api.luta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "LUTA")
public class Luta {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO")
    private Long codigo;

    @NotNull
    @CreationTimestamp
    @Column(name = "DATA")
    private LocalDateTime data;

    @NotNull
    @Column(name = "NOME_VENCEDOR")
    private String nome_vencedor;

    @NotNull
    @Column(name = "LEVEL_VENCEDOR")
    private Integer level_vencedor;

    @NotNull
    @Column(name = "IMAGEM_VENCEDOR")
    private String imagem_vencedor;

    @NotNull
    @Column(name = "NOME_PERDEDOR")
    private  String nome_perdedor;

    @NotNull
    @Column(name = "LEVEL_PERDEDOR")
    private Integer level_perdedor;

    @NotNull
    @Column(name = "IMAGEM_PERDEDOR")
    private String imagem_perdedor;

    @NotNull
    @Column(name = "TIME_VENCEDOR")
    private String time_vencedor;

    @NotNull
    @Column(name = "TIME_PERDEDOR")
    private String time_perdedor;

}
