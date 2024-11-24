public class Puerto {

    private Queue barcosEnEspera;
    private Stack sinAlimentos; // Muelle 1
    private Stack alimentosNoPerecederos; // Muelle 2
    private Stack alimentosPerecederos; // Muelle 3

    public Puerto(Queue barcosEnEspera, Stack sinAlimentos, Stack alimentosNoPerecederos, Stack alimentosPerecederos) {
        this.barcosEnEspera = barcosEnEspera;
        this.sinAlimentos = sinAlimentos;
        this.alimentosNoPerecederos = alimentosNoPerecederos;
        this.alimentosPerecederos = alimentosPerecederos;
    }

    public Puerto() {
    }

    public Queue getBarcosEnEspera() {
        return barcosEnEspera;
    }

    public void setBarcosEnEspera(Queue barcosEnEspera) {
        this.barcosEnEspera = barcosEnEspera;
    }

    public Stack getSinAlimentos() {
        return sinAlimentos;
    }

    public void setSinAlimentos(Stack sinAlimentos) {
        this.sinAlimentos = sinAlimentos;
    }

    public Stack getAlimentosNoPerecederos() {
        return alimentosNoPerecederos;
    }

    public void setAlimentosNoPerecederos(Stack alimentosNoPerecederos) {
        this.alimentosNoPerecederos = alimentosNoPerecederos;
    }

    public Stack getAlimentosPerecederos() {
        return alimentosPerecederos;
    }

    public void setAlimentosPerecederos(Stack alimentosPerecederos) {
        this.alimentosPerecederos = alimentosPerecederos;
    }
}