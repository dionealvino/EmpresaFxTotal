package empresafxtotal.controller.classes;

import empresafxtotal.model.VendaDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Barbara
 */
public class Venda {

    private int pkVenda;

    private int numero;
    private Date data;
    
    private Cliente cliente;
    private Funcionario vendedor;
    private ArrayList<VendaItem> itens;


    public Venda() {
    }

    public Venda(int numero, Date data, Cliente cliente, Funcionario vendedor) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public Venda(int numero, Date data, Cliente cliente, Funcionario vendedor, ArrayList<VendaItem> itens) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.itens = itens;
    }

    public Venda(int numero, Date data, Funcionario vendedor, ArrayList<VendaItem> itens, int pkVenda) {
        this.numero = numero;
        this.data = data;
        this.vendedor = vendedor;
        this.itens = itens;
        this.pkVenda = pkVenda;
    }

    public void addItem(VendaItem vi) {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        itens.add(vi);
    }

    public int getNumero() {
        return numero;
    }

    public Date getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public ArrayList<VendaItem> getItens() {
        return itens;
    }

    public int getPkVenda() {
        return pkVenda;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public void setItens(ArrayList<VendaItem> itens) {
        this.itens = itens;
    }

    public void setPkVenda(int pkVenda) {
        this.pkVenda = pkVenda;
    }

    @Override
    public String toString() {
        return "Venda{" + "numero=" + numero + ", data=" + data + ", cliente=" + cliente + ", vendedor=" + vendedor + ", itens=" + itens + ", pkVenda=" + pkVenda + '}';
    }
    
        public void save() throws SQLException{
            VendaDAO.create(this);
    }
  
    public void update() throws SQLException{
       VendaDAO.update(this);
    }

}
