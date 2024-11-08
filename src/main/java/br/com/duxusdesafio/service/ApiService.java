package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * @author carlosau
 */
@Service
public class ApiService {


    public List<String> timeDaData(LocalDate data, List<Time> todosOsTimes) {
        /**
         *Inicia com uma lista vazia,depois um for each e verifica a data do time é igual a passada.
         */
        List<String> nomes = new ArrayList<>();
        for (Time time : todosOsTimes) {
            if (time.getData().equals(data)) {
                //Percorre e adiciona o nome do Integrante.
                for (ComposicaoTime composicao : time.getComposicaoTime()) {
                    nomes.add(composicao.getIntegrante().getNome());
                }
                return nomes;
            }
        }
        return Collections.emptyList();
    }

    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        /**
         * Cria um map(Map)contando as reincidência de cada jogador,
         * filtra(filter) os times pelas datas passadas como parametro
         *mostra a formação do time, puxa os integrantes e conta os integrantes.
         * e no return encontra o jogador mais usado na escalação de todos os times.
         */
        Map<Integrante, Long> contagemIntegrantes = todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream()).map(ComposicaoTime::getIntegrante)
                .collect(Collectors.groupingBy(integrante -> integrante, Collectors.counting()));

        return contagemIntegrantes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        /**
         * Neste metodo deu para reutilizar parte da lógica de filtragem de datas.
         * Inicia novamente com o map(Map) contando cada time dentro do periodo passado pela datai de inicio e de fim
         * encontra o time com mais reincidencia no periodo
         * no return devolve a lista de nomes dos jogadores mais comum, ou retorna null se nao for encontrado
         */

        Map<Time, Long>contagemTimes = todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial))&&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .collect(Collectors.groupingBy(time -> time, Collectors.counting()));

        Time timeMaisComum = contagemTimes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return (timeMaisComum != null)
                ? timeMaisComum.getComposicaoTime().stream()
                .map(composicao -> composicao.getIntegrante().getNome())
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        /**
         * Neste método consegui dar ctrl+C, ctrl+V no  método integranteMaisUsado
         * substituindo só o item de busca que neste caso é a função.
         * O metodo filtra a data passada como parametro e inicia a busca da funcao mais comum.
         * Ele percorre a composicaoTime e obtem a funcao de cada composicao
         * e no return identifica a funcao mais comum.
         */
        Map<String, Long> contagemFuncoes = todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getFuncao()) // Obtém a função do integrante
                .collect(Collectors.groupingBy(funcao -> funcao, Collectors.counting()));

        return contagemFuncoes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        /**
         * Neste método a lógica é a mesma do anterior mudando apenas que quero achar a franquia mais famosa dentre os times
         * O método inicia filtrando o time no tempo passado e depois inicia a busca da franquia mais famosa.
         * Percorrendo a composicaoTime e tendo acesso a fraquia de cada jogador.
         * e no return passa a quantidade de jogadores que tm determinada franquia.
         */
        Map<String, Long> contagemFranquias = todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getFranquia()) // Acessa a franquia do integrante
                .collect(Collectors.groupingBy(franquia -> franquia, Collectors.counting()));

        return contagemFranquias.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        /**
         * Nesse método a lógica de filtrar data percorrer a composicaoTime é a mesma dos anteriores, e nesse ele busca a franquia mais comum.
         * Este método retorna um Map com String = franquia e um Long com a quantdade de jogadores.
         */
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getFranquia())
                .collect(Collectors.groupingBy(franquia -> franquia, Collectors.counting()));
    }

    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        /**
         * Este método é igual ao anterior com a diferença que ele faz uma contagem por função, tendo a mesa lógica geral do passado.
         * E tendo como reutilizar parte do código da funcaoMaisComum.
         */
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicao -> composicao.getIntegrante().getFuncao())
                .collect(Collectors.groupingBy(funcao -> funcao, Collectors.counting()));
    }

}
