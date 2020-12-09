package br.com.zup.cartao.proposta.carteiras;

public class CarteiraDigitalResponse {
    private String email;
    private String carteira;
    private String cartaoAssociado;


    public CarteiraDigitalResponse() {

    }

    public CarteiraDigitalResponse(String email, String carteira, String cartaoAssociado) {
        this.email = email;
        this.carteira = carteira;
        this.cartaoAssociado = cartaoAssociado;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public String getCartaoAssociado() {
        return cartaoAssociado;
    }
}
