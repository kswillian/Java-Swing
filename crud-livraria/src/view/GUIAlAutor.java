/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.AutorVO;
import service.AutorService;
import service.ServiceFactory;

/**
 *
 * @author windows
 */
public class GUIAlAutor extends javax.swing.JInternalFrame {

    DefaultTableModel dtm = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"idautor", "nome"});
    
    /**
     * Creates new form GUIAlAutor
     */
    public GUIAlAutor() {
        initComponents();
        preencherTabela();
    }
    
    private void preencherTabela(){
        try {
            //Buscando objeto ProdutoServicos
            AutorService as = ServiceFactory.getAutorService();
            
            /* Criando um ArrayList<ClienteVO> vazio
             para receber o ArrayList com os dados */
            ArrayList<AutorVO> ali = new ArrayList<>();
            
            //Recebendo o ArrayList cheio em produtos
            ali = as.buscarAutores();
            
            for(int i = 0; i < ali.size(); i++){
                    dtm.addRow(new String[]{
                    String.valueOf(ali.get(i).getIdautor()),
                    String.valueOf(ali.get(i).getNome()),
                });
            }
            /* Adicionando o modelo de tabela 
             com os dados na tabela produto */
            jTableAltor.setModel(dtm);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Erro !!" + e.getMessage());
        }
    }
    
    private void filtrar(){
        try{
            if(jtPesquisa.getText().isEmpty()){
                limparTabela();
                preencherTabela();  
            }else{
                String filtro = jComboPesquisa.getSelectedItem().toString();
                String query = "";
                
                if(filtro.equalsIgnoreCase("NOME")){
                    query = "WHERE nome like '%"+jtPesquisa.getText()+"%'";
                }
                
                AutorService as = ServiceFactory.getAutorService();
                ArrayList<AutorVO> fli = as.filtra(query);
                
                for(int i = 0; i < fli.size(); i++){
                dtm.addRow(new String[]{
                    String.valueOf(fli.get(i).getIdautor()),
                    String.valueOf(fli.get(i).getNome())
                    });
                }
                
                jTableAltor.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
                    "Erro!" + e.getMessage());
        }
    }
    
    private void alterar(){
        int linha = jTableAltor.getSelectedRow();
        if(linha != -1){
            jtIdAutor.setText((String) jTableAltor.getValueAt(linha, 0));
            jtNome.setText((String) jTableAltor.getValueAt(linha, 1));
        }else{
            
            JOptionPane.showMessageDialog(this, 
                    "Você não selecionou nenhuma linha!!");
        }
    }
    
    private void confirmarAlteracao(){
        
        int linha = jTableAltor.getSelectedRow();
        if(linha != -1){
            try {
            
            AutorVO aVO = new AutorVO();
            aVO.setIdautor(Long.parseLong(jtIdAutor.getText()));
            
            if(!jtNome.getText().isEmpty()){
                
                aVO.setNome(jtNome.getText());
            }else{
                throw new NumberFormatException("Preencha o campo NOME");
            }
            
            AutorService as = ServiceFactory.getAutorService();
            as.alterarAutor(aVO);
             
            JOptionPane.showMessageDialog(this,
                    "Autor alterado com sucesso!!");
            } catch (Exception e) {

                JOptionPane.showMessageDialog(this,
                        "Erro " +e.getMessage());
            }
        }else{
            
            JOptionPane.showMessageDialog(this, 
                    "Você não selecionou nenhuma linha!!");
        }
    }
    
    private void deletar(){
        try {
            int linha = jTableAltor.getSelectedRow();
            if(linha == 1){
                JOptionPane.showMessageDialog(rootPane, 
                        "Você não selecionou nunhuma linha !");
            }else{
                AutorService as = ServiceFactory.getAutorService();
                
                String idAutor = (String) jTableAltor.getValueAt(linha, 0);
                as.deletarAutor(Long.parseLong(idAutor));
                
                JOptionPane.showMessageDialog(rootPane,
                        "Autor excluido com sucesso!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, 
                    "Erro ao deletar!" + e.getMessage());
        }
    }
    
    private void limparTabela() {
    /*Para limpar a tabela temos que setar o número de
      linhas para zero no default table model */        
        dtm.setNumRows(0);
    }//fecha limparTabela
    
    private void limpar(){
        jtIdAutor.setText(null);
        jtNome.setText(null);
        jtPesquisa.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtPesquisa = new javax.swing.JTextField();
        jComboPesquisa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAltor = new javax.swing.JTable();
        jbAtualizar = new javax.swing.JButton();
        jtNome = new javax.swing.JTextField();
        jlNome = new javax.swing.JLabel();
        jbCadastrar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jlIdAutor = new javax.swing.JLabel();
        jtIdAutor = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        jtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtPesquisaKeyReleased(evt);
            }
        });

        jComboPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOME", " " }));
        jComboPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboPesquisaItemStateChanged(evt);
            }
        });

        jTableAltor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAltor.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableAltor);

        jbAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reload (1).png"))); // NOI18N
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNomeActionPerformed(evt);
            }
        });
        jtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNomeKeyPressed(evt);
            }
        });

        jlNome.setText("NOME DO AUTOR");

        jbCadastrar.setText("ALTERAR");
        jbCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/right-arrow (2).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlIdAutor.setText("ID");

        jtIdAutor.setEditable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/garbage (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(443, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlIdAutor)
                            .addComponent(jtIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbCadastrar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNome)
                            .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(249, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jlIdAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jlNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jbCadastrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jbAtualizar)
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(jtPesquisa))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPesquisaKeyReleased
        // TODO add your handling code here:
        limparTabela();
        filtrar();
    }//GEN-LAST:event_jtPesquisaKeyReleased

    private void jComboPesquisaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboPesquisaItemStateChanged
        // TODO add your handling code here:
        limparTabela();
        filtrar();
    }//GEN-LAST:event_jComboPesquisaItemStateChanged

    private void jbAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAtualizarActionPerformed
        // TODO add your handling code here:
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbAtualizarActionPerformed

    private void jbCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarActionPerformed
        // TODO add your handling code here:
        confirmarAlteracao();
        limpar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbCadastrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        deletar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            confirmarAlteracao();
            limpar();
            limparTabela();
            preencherTabela();
        }
    }//GEN-LAST:event_jtNomeKeyPressed

    private void jtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAltor;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JButton jbCadastrar;
    private javax.swing.JLabel jlIdAutor;
    private javax.swing.JLabel jlNome;
    private javax.swing.JTextField jtIdAutor;
    private javax.swing.JTextField jtNome;
    private javax.swing.JTextField jtPesquisa;
    // End of variables declaration//GEN-END:variables
}
