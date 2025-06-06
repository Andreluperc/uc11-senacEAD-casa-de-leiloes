/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    public static boolean cadastrarProduto(ProdutosDTO produto) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            conectaDAO conn = new conectaDAO();
            conn.connectDB();

            PreparedStatement ps = conn.getConn().prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());

            int inserirTabela = ps.executeUpdate();
            System.out.println("Produto cadastrado com sucesso");
            conn.desconectar();
            return inserirTabela > 0;

        } catch (SQLException se) {

            System.err.println("erro ao cadastrar produto: " + se.getMessage());
            return false;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() throws ClassNotFoundException {

        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {

            conectaDAO conn = new conectaDAO();
            conn.connectDB();

            String sql = "SELECT id, nome, valor, status FROM produtos";

            PreparedStatement ps = conn.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProdutosDTO produtos = new ProdutosDTO();

                produtos.setId(rs.getInt("Id"));
                produtos.setNome(rs.getString("Nome"));
                produtos.setValor(rs.getInt("Valor"));
                produtos.setStatus(rs.getString("Status"));

                listagem.add(produtos);

            }

            conn.desconectar();

        } catch (SQLException se) {

            System.err.println("Erro ao listar produtos: " + se.getMessage());

        }

        return listagem;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws ClassNotFoundException {

        ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();

        try {

            conectaDAO conn = new conectaDAO();
            conn.connectDB();

            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

            PreparedStatement ps = conn.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProdutosDTO produtos = new ProdutosDTO();

                produtos.setId(rs.getInt("Id"));
                produtos.setNome(rs.getString("Nome"));
                produtos.setValor(rs.getInt("Valor"));
                produtos.setStatus(rs.getString("Status"));

                listagemVendidos.add(produtos);

            }

            conn.desconectar();

        } catch (SQLException se) {

            System.err.println("Erro ao listar produtos: " + se.getMessage());

        }

        return listagemVendidos;
    }
    
    public static boolean venderProduto(int id) throws ClassNotFoundException, SQLException{
        
        try {

            conectaDAO conn = new conectaDAO();
            conn.connectDB();

            String sql = "UPDATE produtos SET status = ? WHERE id = ?";

            PreparedStatement ps = conn.getConn().prepareStatement(sql);
            ps.setString(1,"Vendido");
            ps.setInt(2, id);

            int linhasAlteradas = ps.executeUpdate();
            System.out.println("Produto vendido com sucesso. Linhas afetadas: " + linhasAlteradas);
            conn.desconectar();

            return linhasAlteradas > 0; //Retorna true se pelo menos uma linha foi afetada
        } catch (SQLException se) {
            System.err.println("Erro ao vender o produto: " + se.getMessage());
            return false;
        }
    }

}
