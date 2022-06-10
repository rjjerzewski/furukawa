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
public class Tcliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Tcliente
     */
    public Tcliente() {
        initComponents();
        conexao = ModuloConexao.conectar();
    }

    private void adicionar() {
        String sql = "insert into tbclientes (nomecliente,cpf,rg,endereco,fonecli,email,datacad,razaosocial,bairro,cidade,estado,pais ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliRazao.getText());
            pst.setString(2, txtCliCpf.getText());
            pst.setString(3, txtCliIe.getText());
            pst.setString(4, txtCliEndereco.getText());
            pst.setString(5, txtCliTelefone.getText());
            pst.setString(6, txtCliEmail.getText());
            pst.setString(7, txtClidta.getText());
            pst.setString(8, txtCliFantasia.getText());
            pst.setString(9, txtCliBairro.getText());
            pst.setString(10, txtCliCidade.getText());
            pst.setString(11, txtCliEstado.getText());
            pst.setString(12, txtCliPais.getText());

            //validacao dos campos obrigatorios
            if ((txtCliRazao.getText().isEmpty()) || (txtCliCpf.getText().isEmpty()) || (txtCliIe.getText().isEmpty()) || (txtCliFantasia.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //atualiza a tabela usuario com dados do form
                // estrutura abaixo confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    txtCliRazao.setText(null);
                    txtCliCpf.setText(null);
                    txtCliIe.setText(null);
                    txtCliFantasia.setText(null);
                    txtCliTelefone.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliBairro.setText(null);
                    txtCliCidade.setText(null);
                    txtCliEstado.setText(null);
                    txtCliPais.setText(null);
                    txtCliEmail.setText(null);
                    txtClidta.setText(null);
                    
                    //txtUsuPerfil.setSelectedItem(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    //Metodo pesquisar clientes pelo nome com filtro
    private void pesquisar_cliente(){
        String sql="select * from tbclientes where nomecliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao % que é a continuação da String SQL
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs=pst.executeQuery();
            //Usa recurso da biblioteca RS2XML.JAR para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Metodo para setar os campos do form com o conteudo da tabela(grid)
    public void setar_campos(){
        int setar = tblClientes.getSelectedRow();
        txtCliRazao.setText(tblClientes.getModel().getValueAt(setar,1).toString());
        txtCliCpf.setText(tblClientes.getModel().getValueAt(setar,2).toString());
        txtCliIe.setText(tblClientes.getModel().getValueAt(setar,3).toString());
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar,4).toString());
        txtCliTelefone.setText(tblClientes.getModel().getValueAt(setar,5).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar,6).toString());
        txtClidta.setText(tblClientes.getModel().getValueAt(setar,7).toString());
        txtCliFantasia.setText(tblClientes.getModel().getValueAt(setar,8).toString());
        txtCliBairro.setText(tblClientes.getModel().getValueAt(setar,9).toString());
        txtCliCidade.setText(tblClientes.getModel().getValueAt(setar,10).toString());
        txtCliEstado.setText(tblClientes.getModel().getValueAt(setar,11).toString());
        txtCliPais.setText(tblClientes.getModel().getValueAt(setar,12).toString());
        
    }
    
    
    //metodo editar
    private void alterar() {
        String sql = "update tbclientes set nomecliente=?, cpf=?,rg=?,endereco=?,fonecli=?,email=?,datacad=?,razaosocial=?,bairro=?,cidade=?,estado=?,pais=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliRazao.getText());
            pst.setString(2, txtCliCpf.getText());
            pst.setString(3, txtCliIe.getText());
            pst.setString(4, txtCliEndereco.getText());
            pst.setString(5, txtCliTelefone.getText());
            pst.setString(6, txtCliEmail.getText());
            pst.setString(7, txtClidta.getText());
            pst.setString(8, txtCliFantasia.getText());
            pst.setString(9, txtCliBairro.getText());
            pst.setString(10, txtCliCidade.getText());
            pst.setString(11, txtCliEstado.getText());
            pst.setString(12, txtCliPais.getText());
            if ((txtCliRazao.getText().isEmpty()) || (txtCliCpf.getText().isEmpty()) || (txtCliIe.getText().isEmpty()) || (txtCliFantasia.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //atualiza a tabela usuario com dados do form
                // estrutura abaixo confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
                    txtCliRazao.setText(null);
                    txtCliCpf.setText(null);
                    txtCliIe.setText(null);
                    txtCliFantasia.setText(null);
                    txtCliTelefone.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliBairro.setText(null);
                    txtCliCidade.setText(null);
                    txtCliEstado.setText(null);
                    txtCliPais.setText(null);
                    txtCliEmail.setText(null);
                    txtClidta.setText(null);
                    //txtUsuPerfil.setSelectedItem(null);
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
            String sql = "delete from tbclientes where nomecliente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliRazao.getText());
                int apagado  = pst.executeUpdate();
                if (apagado >0 ) {
                    JOptionPane.showMessageDialog(null,"Usuario excluido com sucesso!");
                      txtCliRazao.setText(null);
                    txtCliCpf.setText(null);
                    txtCliIe.setText(null);
                    txtCliFantasia.setText(null);
                    txtCliTelefone.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliBairro.setText(null);
                    txtCliCidade.setText(null);
                    txtCliEstado.setText(null);
                    txtCliPais.setText(null);
                    txtCliEmail.setText(null);
                    txtClidta.setText(null);
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
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCliRazao = new javax.swing.JTextField();
        txtCliCpf = new javax.swing.JTextField();
        txtCliIe = new javax.swing.JTextField();
        txtClidta = new javax.swing.JTextField();
        txtCliFantasia = new javax.swing.JTextField();
        txtCliTelefone = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliBairro = new javax.swing.JTextField();
        txtCliCidade = new javax.swing.JTextField();
        txtCliEstado = new javax.swing.JTextField();
        txtCliPais = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        btnincluir = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Clientes");
        setPreferredSize(new java.awt.Dimension(720, 555));

        jLabel2.setText("*Nome / Razão Social");

        jLabel3.setText("*CPF / CNPJ");

        jLabel4.setText("*RG / IE");

        jLabel5.setText("Endereço");

        jLabel6.setText("Telefone");

        jLabel7.setText("E-mail");

        jLabel8.setText("Data de Cadastro");

        jLabel9.setText("*Nome Fantasia");

        jLabel10.setText("Bairro");

        jLabel11.setText("Cidade");

        jLabel12.setText("Estado");

        jLabel13.setText("Pais");

        txtCliCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliCpfActionPerformed(evt);
            }
        });

        btnincluir.setText("Incluir");
        btnincluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnincluirActionPerformed(evt);
            }
        });

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Excluir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/furukawa/icones/search_client_16px.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("*Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtClidta, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCliIe, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(137, 137, 137)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtCliFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel10)
                                .addGap(142, 142, 142)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnincluir)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliPais, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtCliRazao, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtCliCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClidta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliIe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnincluir)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliCpfActionPerformed

    private void btnincluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnincluirActionPerformed
        // cHAMADNO FUNÇÃO ADICIONAR
        adicionar();
    }//GEN-LAST:event_btnincluirActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // Chama o metodo pesqusiar clientes
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // Evento para setar os campos da tabela clicando
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here
        alterar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnincluir;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliBairro;
    private javax.swing.JTextField txtCliCidade;
    private javax.swing.JTextField txtCliCpf;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliEstado;
    private javax.swing.JTextField txtCliFantasia;
    private javax.swing.JTextField txtCliIe;
    private javax.swing.JTextField txtCliPais;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliRazao;
    private javax.swing.JTextField txtCliTelefone;
    private javax.swing.JTextField txtClidta;
    // End of variables declaration//GEN-END:variables
}
