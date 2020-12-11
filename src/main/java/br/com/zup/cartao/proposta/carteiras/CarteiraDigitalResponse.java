package br.com.zup.cartao.proposta.carteiras;

public class CarteiraDigitalResponse {
    private String email;
    private TipoCarteira carteira;
    private String cartaoAssociado;


    public CarteiraDigitalResponse() {

    }

    public CarteiraDigitalResponse(String email, TipoCarteira carteira, String cartaoAssociado) {
        this.email = email;
        this.carteira = carteira;
        this.cartaoAssociado = cartaoAssociado;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public String getCartaoAssociado() {
        return cartaoAssociado;
    }
}
