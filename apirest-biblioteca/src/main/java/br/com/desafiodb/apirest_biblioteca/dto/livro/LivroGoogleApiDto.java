package br.com.desafiodb.apirest_biblioteca.dto.livro;

public class LivroGoogleApiDto {
    
    private LivroDetalhesGoogleApiDto volumeInfo;

    public LivroDetalhesGoogleApiDto getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(LivroDetalhesGoogleApiDto volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

}
