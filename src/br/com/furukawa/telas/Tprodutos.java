/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.furukawa.telas;

import java.sql.*;
import br.com.furukawa.dal.ModuloConexao;
import java.text.DateFormat;
import javax.swing.JOptionPane;
//Importa recursos da biblioteca RS2XML.JAR
import net.proteanit.sql.DbUtils;

/**
 *
 * @author usuario
 */
public class Tprodutos extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Tprodutos
     */
    public Tprodutos() {
        initComponents();
        conexao = ModuloConexao.conectar();
    }

    private void adicionar() {
        String sql = "insert into tbproduto (nome,discriminacao,codigo,tipo,unidade,departamento,precovenda,fabricante,marca,quantidade) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProdNome.getText());
            pst.setString(2, txtProdDisc.getText());
            pst.setString(3, txtProdCod.getText());
            pst.setString(4, txtProdTipo.getText());
            pst.setString(5, txtProdUn.getText());
            pst.setString(6, txtProdDep.getText());
            pst.setString(7, txtProdPreco.getText());
            pst.setString(8, txtProdFabri.getText());
            pst.setString(9, txtProdMarca.getText());
            pst.setString(10, txtProdEstoque.getText());

            //validacao dos campos obrigatorios
            if ((txtProdNome.getText().isEmpty()) || (txtProdCod.getText().isEmpty()) || (txtProdEstoque.getText().isEmpty()) || (txtProdMarca.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                // estrutura abaixo confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
                    txtProdNome.setText(null);
                    txtProdDisc.setText(null);
                    txtProdCod.setText(null);
                    txtProdTipo.setText(null);
                    txtProdUn.setText(null);
                    txtProdDep.setText(null);
                    txtProdPreco.setText(null);
                    txtProdFabri.setText(null);
                    txtProdMarca.setText(null);
                    txtProdEstoque.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo pesquisar clientes pelo nome com filtro
    private void pesquisar_cliente() {
        String sql = "select * from tbproduto where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao % que é a continuação da String SQL
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //Usa recurso da biblioteca RS2XML.JAR para preencher a tabela
            tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setar_campos() {
        int setar = tblProdutos.getSelectedRow();
        txtProdNome.setText(tblProdutos.getModel().getValueAt(setar, 1).toString());
        txtProdDisc.setText(tblProdutos.getModel().getValueAt(setar, 2).toString());
        txtProdCod.setText(tblProdutos.getModel().getValueAt(setar, 3).toString());
        txtProdTipo.setText(tblProdutos.getModel().getValueAt(setar, 4).toString());
        txtProdUn.setText(tblProdutos.getModel().getValueAt(setar, 5).toString());
        txtProdDep.setText(tblProdutos.getModel().getValueAt(setar, 6).toString());
        txtProdPreco.setText(tblProdutos.getModel().getValueAt(setar, 7).toString());
        txtProdFabri.setText(tblProdutos.getModel().getValueAt(setar, 8).toString());
        txtProdMarca.setText(tblProdutos.getModel().getValueAt(setar, 9).toString());
        txtProdEstoque.setText(tblProdutos.getModel().getValueAt(setar, 10).toString());
    }

    private void alterar() {
        String sql = "update tbclientes set nomecliente=?, cpf=?,rg=?,endereco=?,fonecli=?,email=?,datacad=?,razaosocial=?,bairro=?,cidade=?,estado=?,pais=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtProdNome.getText());
            pst.setString(2, txtProdDisc.getText());
            pst.setString(3, txtProdCod.getText());
            pst.setString(4, txtProdTipo.getText());
            pst.setString(5, txtProdUn.getText());
            pst.setString(6, txtProdDep.getText());
            pst.setString(7, txtProdPreco.getText());
            pst.setString(8, txtProdFabri.getText());
            pst.setString(9, txtProdMarca.getText());
            pst.setString(10, txtProdEstoque.getText());
            if ((txtProdNome.getText().isEmpty()) || (txtProdCod.getText().isEmpty()) || (txtProdMarca.getText().isEmpty()) || (txtProdEstoque.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //atualiza a tabela usuario com dados do form
                // estrutura abaixo confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
                    txtProdNome.setText(null);
                    txtProdDisc.setText(null);
                    txtProdCod.setText(null);
                    txtProdTipo.setText(null);
                    txtProdUn.setText(null);
                    txtProdDep.setText(null);
                    txtProdPreco.setText(null);
                    txtProdFabri.setText(null);
                    txtProdMarca.setText(null);
                    txtProdEstoque.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Criando Metodo Remover usuarios
    private void remover() {
        //a estrutura abaixo confirma a remoção do usaurio
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbproduto where nome=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtProdNome.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
                    txtProdNome.setText(null);
                    txtProdDisc.setText(null);
                    txtProdCod.setText(null);
                    txtProdTipo.setText(null);
                    txtProdUn.setText(null);
                    txtProdDep.setText(null);
                    txtProdPreco.setText(null);
                    txtProdFabri.setText(null);
                    txtProdMarca.setText(null);
                    txtProdEstoque.setText(null);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCliPesquisar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        txtProdNome = new javax.swing.JTextField();
        txtProdDisc = new javax.swing.JTextField();
        txtProdCod = new javax.swing.JTextField();
        txtProdTipo = new javax.swing.JTextField();
        txtProdUn = new javax.swing.JTextField();
        txtProdDep = new javax.swing.JTextField();
        txtProdFabri = new javax.swing.JTextField();
        txtProdMarca = new javax.swing.JTextField();
        txtProdEstoque = new javax.swing.JTextField();
        txtProdPreco = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Produtos");

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("*Campos Obrigatórios");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/furukawa/icones/search_client_16px.png"))); // NOI18N

        jLabel2.setText("Produto");

        jLabel3.setText("Discriminação");

        jLabel4.setText("Código");

        jLabel5.setText("Tipo");

        jLabel6.setText("Unidade");

        jLabel7.setText("Departamento");

        jLabel8.setText("Preço Venda");

        jLabel9.setText("Fabricante");

        jLabel10.setText("Marca");

        jLabel11.setText("Estoque");

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        txtProdCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdCodActionPerformed(evt);
            }
        });

        txtProdPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProdPrecoActionPerformed(evt);
            }
        });

        jButton1.setText("Incluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(171, 171, 171)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtProdTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtProdUn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtProdDep, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtProdNome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(98, 98, 98)
                                                .addComponent(jLabel9))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(174, 174, 174)
                                                .addComponent(txtProdFabri, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(txtProdDisc, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(txtProdCod, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtProdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtProdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProdDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProdCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProdTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProdUn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProdDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProdFabri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProdPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(43, 43, 43)
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProdCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdCodActionPerformed

    private void txtProdPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProdPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProdPrecoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblProdutosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtProdCod;
    private javax.swing.JTextField txtProdDep;
    private javax.swing.JTextField txtProdDisc;
    private javax.swing.JTextField txtProdEstoque;
    private javax.swing.JTextField txtProdFabri;
    private javax.swing.JTextField txtProdMarca;
    private javax.swing.JTextField txtProdNome;
    private javax.swing.JTextField txtProdPreco;
    private javax.swing.JTextField txtProdTipo;
    private javax.swing.JTextField txtProdUn;
    // End of variables declaration//GEN-END:variables
}
