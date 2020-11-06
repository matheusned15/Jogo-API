package br.com.jogo.api.luta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LutaRepository extends JpaRepository<Luta, Long> {

}
