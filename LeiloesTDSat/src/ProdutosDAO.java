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
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

