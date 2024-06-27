package br.com.desafiodb.apirest_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.desafiodb.apirest_biblioteca.dto.livro.LivroListaGoogleApiDto;

@Service
public class LivroGoogleApiService {

    private static String URL_GOOGLE_API = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Método responsável por consumir API do Google.
     * Dentre todos os valores, escolhi buscar somente o resumo/description.
     * @autor AnaCagliari
     * @param isbn
     * @return
     */
    public String buscaResumoLivro(String isbn) {

        String url = URL_GOOGLE_API + isbn;

        LivroListaGoogleApiDto response = restTemplate.getForObject(url, LivroListaGoogleApiDto.class);

        if (response.getTotalItems() > 1 || response.getTotalItems() == 0) {
            return "";
        }

        return response.getItems().get(0).getVolumeInfo().getDescription();
    }
}
