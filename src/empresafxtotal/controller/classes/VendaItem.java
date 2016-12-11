package empresafxtotal.controller.classes;

/**
 *
 * @author Barbara
 */
public class VendaItem {
    private int qtd;
    private double valorUnitario;
    private Produto produto;
    
    private int fkVenda;
    private int pkVendaItem;

    public VendaItem() {
    }

    public VendaItem(int qtd, float valorUnitario, Produto produto) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
    }

    public VendaItem(int qtd, float valorUnitario, Produto produto, int fkVenda, int pkVendaItem) {
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
        this.produto = produto;
        this.fkVenda = fkVenda;
        this.pkVendaItem = pkVendaItem;
    }

    public int getQtd() {
        return qtd;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getFkVenda() {
        return fkVenda;
    }

    public int getPkVendaItem() {
        return pkVendaItem;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setFkVenda(int fkVenda) {
        this.fkVenda = fkVenda;
    }

    public void setPkVendaItem(int pkVendaItem) {
        this.pkVendaItem = pkVendaItem;
    }

    @Override
    public String toString() {
        return "VendaItem{" + "qtd=" + qtd + ", valorUnitario=" + valorUnitario + ", produto=" + produto + ", fkVenda=" + fkVenda + ", pkVendaItem=" + pkVendaItem + '}';
    }
    
    
    
}
