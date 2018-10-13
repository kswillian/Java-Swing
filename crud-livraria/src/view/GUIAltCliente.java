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
import model.ClienteVO;
import service.ClienteService;
import service.ServiceFactory;

/**
 *
 * @author willian.santos
 */
public class GUIAltCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form GUIAltCliente
     */
    
    DefaultTableModel dtm = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"idcliente", "nome", "sobrenome", "sexo", "idade", "cidade", "bairro", "rua", "numero", "email", "telefone1", "telefone2"});
    
    public GUIAltCliente() {
        initComponents();
        preencherTabela();
    }
    
    private void preencherTabela(){
        try {
            //Buscando objeto ProdutoServicos
            ClienteService cs = ServiceFactory.getClienteService();
            
            /* Criando um ArrayList<ClienteVO> vazio
             para receber o ArrayList com os dados */
            ArrayList<ClienteVO> cli = new ArrayList<>();
            
            //Recebendo o ArrayList cheio em produtos
            cli = cs.buscarCliente();
            
            for(int i = 0; i < cli.size(); i++){
                    dtm.addRow(new String[]{
                    String.valueOf(cli.get(i).getIdcliente()),
                    String.valueOf(cli.get(i).getNome()),
                    String.valueOf(cli.get(i).getSobrenome()),
                    String.valueOf(cli.get(i).getSexo()),
                    String.valueOf(cli.get(i).getIdade()),
                    String.valueOf(cli.get(i).getCidade()),
                    String.valueOf(cli.get(i).getBairro()),
                    String.valueOf(cli.get(i).getRua()),
                    String.valueOf(cli.get(i).getNumero()),
                    String.valueOf(cli.get(i).getEmail()),
                    String.valueOf(cli.get(i).getTelefone1()),
                    String.valueOf(cli.get(i).getTelefone2())
                });
            }
            /* Adicionando o modelo de tabela 
             com os dados na tabela produto */
            jTableCliente.setModel(dtm);
            
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
                }else if(filtro.equalsIgnoreCase("SOBRENOME")){
                    query = "WHERE sobrenome like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("CIDADE")){
                    query = "WHERE cidade like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("BAIRRO")){
                    query = "WHERE bairro like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("TELEFONE 1")){
                    query = "WHERE telefone1 like '%"+jtPesquisa.getText()+"%'";
                }
                
                ClienteService cs = ServiceFactory.getClienteService();
                ArrayList<ClienteVO> cli = cs.filtra(query);
                
                for(int i = 0; i < cli.size(); i++){
                dtm.addRow(new String[]{
                    String.valueOf(cli.get(i).getIdcliente()),
                    String.valueOf(cli.get(i).getNome()),
                    String.valueOf(cli.get(i).getSobrenome()),
                    String.valueOf(cli.get(i).getSexo()),
                    String.valueOf(cli.get(i).getIdade()),
                    String.valueOf(cli.get(i).getCidade()),
                    String.valueOf(cli.get(i).getBairro()),
                    String.valueOf(cli.get(i).getRua()),
                    String.valueOf(cli.get(i).getNumero()),
                    String.valueOf(cli.get(i).getEmail()),
                    String.valueOf(cli.get(i).getTelefone1()),
                    String.valueOf(cli.get(i).getTelefone2())
                    });
                }
                
                jTableCliente.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,
                    "Erro!" + e.getMessage());
        }
    }
    
    private void alterar(){
        int linha = jTableCliente.getSelectedRow();
        
        if(linha != -1){
            jtId.setText((String) jTableCliente.getValueAt(linha, 0));
            jtNome.setText((String) jTableCliente.getValueAt(linha, 1));
            jtSobrenome.setText((String) jTableCliente.getValueAt(linha, 2));
            
            if(jTableCliente.getValueAt(linha, 3).equals("Masculino")){
                jrbMasculino.setSelected(true);
            }else{
    
                jrbFeminino.setSelected(true);
            }
            jtIdade.setText((String) jTableCliente.getValueAt(linha, 4));
            jtCidade.setText((String) jTableCliente.getValueAt(linha, 5));
            jtBairro.setText((String) jTableCliente.getValueAt(linha, 6));
            jtRua.setText((String) jTableCliente.getValueAt(linha, 7));
            JtNumero.setText((String) jTableCliente.getValueAt(linha, 8));
            jtEmail.setText((String) jTableCliente.getValueAt(linha, 9));
            jtTelefone1.setText((String) jTableCliente.getValueAt(linha, 10));
            jtTelefone2.setText((String) jTableCliente.getValueAt(linha, 11));
            
        }else{
            
            JOptionPane.showMessageDialog(this, 
                    "Você não selecionou nenhuma linha!!");
        }
    }
    
    private void confirmarAlteracao(){
        int linha = jTableCliente.getSelectedRow();
        
        if(linha != -1){
            try {
            
            ClienteVO cVO = new ClienteVO();
            cVO.setIdcliente(Long.parseLong(jtId.getText()));
            
            
            if(!jtNome.getText().isEmpty()){
                cVO.setNome(jtNome.getText());
            }else{
                throw new NumberFormatException("Preencha o campo NOME");
            }
            if(!jtSobrenome.getText().isEmpty()){
                cVO.setSobrenome(jtSobrenome.getText());
            }else{
                throw new NumberFormatException("Preencha o campo SOBRENOME");
            }
            if(!jtIdade.getText().isEmpty()){
                cVO.setIdade(Integer.parseInt(jtIdade.getText()));
            }else{
                throw new NumberFormatException("Preencha o campo IDADE");
            }
            if(bgSexo.getSelection() != null){
                cVO.setSexo(bgSexo.getSelection().getActionCommand());
            }else{
                throw new NumberFormatException("Escolha a opção SEXO");
            }
            if(!jtIdade.getText().isEmpty()){
                cVO.setIdade(Integer.parseInt(jtIdade.getText()));
            }else{
                throw new NumberFormatException("Preencha o campo IDADE");
            }
            if(!jtCidade.getText().isEmpty()){
                cVO.setCidade(jtCidade.getText());
            }else{
                throw new NumberFormatException("Preencha o campo CIDADE");
            }
            if(!jtBairro.getText().isEmpty()){
                 cVO.setBairro(jtBairro.getText());
            }else{
                throw new NumberFormatException("Preencha o campo BAIRRO");
            }
            if(!jtRua.getText().isEmpty()){
                 cVO.setRua(jtRua.getText());
            }else{
                throw new NumberFormatException("Preencha o campo RUA");
            }
            if(!JtNumero.getText().isEmpty()){
                 cVO.setNumero(Integer.parseInt(JtNumero.getText()));
            }else{
                throw new NumberFormatException("Preencha o campo NUMERO");
            }
            if(!jtEmail.getText().isEmpty()){
                 cVO.setEmail(jtEmail.getText());
            }else{
                throw new NumberFormatException("Preencha o campo EMAIL");
            }
            if(!jtTelefone1.getText().isEmpty()){
                 cVO.setTelefone1(Integer.parseInt(jtTelefone1.getText()));
            }else{
                throw new NumberFormatException("Preencha o campo TELEFONE1");
            }
            if(!jtTelefone2.getText().isEmpty()){
                 cVO.setTelefone2(Integer.parseInt(jtTelefone2.getText()));
            }else{
                throw new NumberFormatException("Preencha o campo TELEFONE2");
            }
            
           //Enviar o objeto ClienteVO para a base de dados
            ClienteService cs = service.ServiceFactory.getClienteService();
            //Chamando o método
            cs.alterarCliente(cVO);
            limpar();
             
            JOptionPane.showMessageDialog(this,
                    "Cliente alterado com sucesso!!");
            
        } catch (Exception e){
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
            int linha = jTableCliente.getSelectedRow();
            if(linha == -1){
                JOptionPane.showMessageDialog(rootPane, 
                        "Você não selecionou nunhuma linha !");
            }else{
                ClienteService as = ServiceFactory.getClienteService();
                
                String idCliente = (String) jTableCliente.getValueAt(linha, 0);
                as.deletarCliente(Long.parseLong(idCliente));
                
                JOptionPane.showMessageDialog(rootPane,
                        "Cliente excluido com sucesso!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, 
                    "Erro ao deletar!" + e.getMessage());
        }
    }
    
    private void limparTabela() {       
        dtm.setNumRows(0);
    }
    
    private void limpar(){
        jtId.setText(null);
        jtNome.setText(null);
        jtSobrenome.setText(null);
        jtIdade.setText(null);
        bgSexo.clearSelection();
        jtCidade.setText(null);
        jtBairro.setText(null);
        jtRua.setText(null);
        JtNumero.setText(null);
        jtEmail.setText(null);
        jtTelefone1.setText(null);
        jtTelefone2.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSexo = new javax.swing.ButtonGroup();
        jlNome = new javax.swing.JLabel();
        jtNome = new javax.swing.JTextField();
        jlSobrenome = new javax.swing.JLabel();
        jtSobrenome = new javax.swing.JTextField();
        jtIdade = new javax.swing.JTextField();
        jlIdade = new javax.swing.JLabel();
        jrbMasculino = new javax.swing.JRadioButton();
        jrbFeminino = new javax.swing.JRadioButton();
        jlSexo = new javax.swing.JLabel();
        jtOutros = new javax.swing.JTabbedPane();
        jpEndereco = new javax.swing.JPanel();
        jlCidade = new javax.swing.JLabel();
        jtCidade = new javax.swing.JTextField();
        ljBairro = new javax.swing.JLabel();
        jtBairro = new javax.swing.JTextField();
        jlRua = new javax.swing.JLabel();
        jtRua = new javax.swing.JTextField();
        jlNumero = new javax.swing.JLabel();
        JtNumero = new javax.swing.JTextField();
        jpContato = new javax.swing.JPanel();
        jlEmail = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jlTelefone1 = new javax.swing.JLabel();
        jtTelefone1 = new javax.swing.JTextField();
        jlTelefone2 = new javax.swing.JLabel();
        jtTelefone2 = new javax.swing.JTextField();
        jbAlterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jtPesquisa = new javax.swing.JTextField();
        jComboPesquisa = new javax.swing.JComboBox<>();
        jlId = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        jbAtualizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        JbCancelar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);

        jlNome.setText("NOME");

        jtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtNomeKeyPressed(evt);
            }
        });

        jlSobrenome.setText("SOBRENOME");

        jtSobrenome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtSobrenomeKeyPressed(evt);
            }
        });

        jtIdade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtIdadeKeyPressed(evt);
            }
        });

        jlIdade.setText("IDADE");

        bgSexo.add(jrbMasculino);
        jrbMasculino.setText("Masculino");
        jrbMasculino.setActionCommand("Masculino");
        jrbMasculino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jrbMasculinoKeyPressed(evt);
            }
        });

        bgSexo.add(jrbFeminino);
        jrbFeminino.setText("Feminino");
        jrbFeminino.setActionCommand("Feminino");
        jrbFeminino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jrbFemininoKeyPressed(evt);
            }
        });

        jlSexo.setText("SEXO");

        jlCidade.setText("CIDADE");

        jtCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCidadeKeyPressed(evt);
            }
        });

        ljBairro.setText("BAIRRO");

        jtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtBairroKeyPressed(evt);
            }
        });

        jlRua.setText("RUA");

        jtRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtRuaKeyPressed(evt);
            }
        });

        jlNumero.setText("NÚMERO");

        JtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JtNumeroKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpEnderecoLayout = new javax.swing.GroupLayout(jpEndereco);
        jpEndereco.setLayout(jpEnderecoLayout);
        jpEnderecoLayout.setHorizontalGroup(
            jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlRua, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlCidade))
                .addGap(18, 18, 18)
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpEnderecoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(ljBairro))
                    .addComponent(jlNumero)
                    .addComponent(JtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jtBairro))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jpEnderecoLayout.setVerticalGroup(
            jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEnderecoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCidade)
                    .addComponent(ljBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRua)
                    .addComponent(jlNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jtOutros.addTab("ENDEREÇO", jpEndereco);

        jlEmail.setText("EMAIL");

        jtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtEmailKeyPressed(evt);
            }
        });

        jlTelefone1.setText("TELEFONE 1");

        jtTelefone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtTelefone1KeyPressed(evt);
            }
        });

        jlTelefone2.setText("TELEFONE 2");

        jtTelefone2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtTelefone2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpContatoLayout = new javax.swing.GroupLayout(jpContato);
        jpContato.setLayout(jpContatoLayout);
        jpContatoLayout.setHorizontalGroup(
            jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtEmail)
                    .addComponent(jtTelefone1)
                    .addGroup(jpContatoLayout.createSequentialGroup()
                        .addGroup(jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlEmail)
                            .addComponent(jlTelefone1)
                            .addComponent(jlTelefone2))
                        .addGap(0, 265, Short.MAX_VALUE))
                    .addComponent(jtTelefone2))
                .addContainerGap())
        );
        jpContatoLayout.setVerticalGroup(
            jpContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTelefone1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTelefone2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jtOutros.addTab("CONTATO", jpContato);

        jbAlterar.setText("ALTERAR");
        jbAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAlterarActionPerformed(evt);
            }
        });
        jbAlterar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbAlterarKeyPressed(evt);
            }
        });

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "SOBRENOME", "SEXO", "IDADE", "CIDADE", "BAIRRO", "RUA", "NUMERO", "EMAIL", "TELEFONE1", "TELEFONE2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCliente.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableCliente);
        if (jTableCliente.getColumnModel().getColumnCount() > 0) {
            jTableCliente.getColumnModel().getColumn(1).setResizable(false);
            jTableCliente.getColumnModel().getColumn(2).setResizable(false);
            jTableCliente.getColumnModel().getColumn(3).setResizable(false);
            jTableCliente.getColumnModel().getColumn(4).setResizable(false);
            jTableCliente.getColumnModel().getColumn(5).setResizable(false);
            jTableCliente.getColumnModel().getColumn(6).setResizable(false);
            jTableCliente.getColumnModel().getColumn(7).setResizable(false);
            jTableCliente.getColumnModel().getColumn(8).setResizable(false);
            jTableCliente.getColumnModel().getColumn(10).setResizable(false);
            jTableCliente.getColumnModel().getColumn(11).setResizable(false);
        }

        jtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtPesquisaKeyReleased(evt);
            }
        });

        jComboPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOME", "SOBRENOME", "CIDADE", "BAIRRO", "TELEFONE" }));
        jComboPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboPesquisaItemStateChanged(evt);
            }
        });

        jlId.setText("ID");

        jtId.setEditable(false);

        jbAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reload (1).png"))); // NOI18N
        jbAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAtualizarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/garbage (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/right-arrow (2).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JbCancelar.setText("CANCELAR");
        JbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbCancelarActionPerformed(evt);
            }
        });
        JbCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JbCancelarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 190, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlSobrenome)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jlNome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlId)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtNome, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jtSobrenome, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlIdade)
                                .addComponent(jtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlSexo)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jrbMasculino)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jrbFeminino))))
                        .addComponent(jtOutros, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(JbCancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jbAlterar))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlId)
                            .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlNome)
                        .addGap(32, 32, 32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlSobrenome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIdade)
                    .addComponent(jlSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbMasculino)
                    .addComponent(jrbFeminino)
                    .addComponent(jtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jtOutros, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAlterar)
                    .addComponent(JbCancelar))
                .addGap(13, 13, 13))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtPesquisa)
                    .addComponent(jComboPesquisa)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAlterarActionPerformed
        // TODO add your handling code here:
       confirmarAlteracao();
       limpar();
       limparTabela();
       preencherTabela();
    }//GEN-LAST:event_jbAlterarActionPerformed

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

    private void JbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbCancelarActionPerformed
        // TODO add your handling code here:
        limparTabela();
        limpar();
        preencherTabela();
    }//GEN-LAST:event_JbCancelarActionPerformed

    private void jtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtSobrenome.grabFocus();
        }
    }//GEN-LAST:event_jtNomeKeyPressed

    private void jtSobrenomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtSobrenomeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtIdade.grabFocus();
        }
    }//GEN-LAST:event_jtSobrenomeKeyPressed

    private void jtIdadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtIdadeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jrbMasculino.grabFocus();
        }
    }//GEN-LAST:event_jtIdadeKeyPressed

    private void jrbMasculinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrbMasculinoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtCidade.grabFocus();
        }
    }//GEN-LAST:event_jrbMasculinoKeyPressed

    private void jrbFemininoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jrbFemininoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtCidade.grabFocus();
        }
    }//GEN-LAST:event_jrbFemininoKeyPressed

    private void jtCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCidadeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtBairro.grabFocus();
        }
    }//GEN-LAST:event_jtCidadeKeyPressed

    private void jtBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtBairroKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtRua.grabFocus();
        }
    }//GEN-LAST:event_jtBairroKeyPressed

    private void jtRuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtRuaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            JtNumero.grabFocus();
        }
    }//GEN-LAST:event_jtRuaKeyPressed

    private void JtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtNumeroKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtEmail.grabFocus();
        }
    }//GEN-LAST:event_JtNumeroKeyPressed

    private void jtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEmailKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtTelefone1.grabFocus();
        }
    }//GEN-LAST:event_jtEmailKeyPressed

    private void jtTelefone1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTelefone1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtTelefone2.grabFocus();
        }
    }//GEN-LAST:event_jtTelefone1KeyPressed

    private void jtTelefone2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTelefone2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jbAlterar.grabFocus();
        }
    }//GEN-LAST:event_jtTelefone2KeyPressed

    private void jbAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbAlterarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            confirmarAlteracao();
            limpar();
            limparTabela();
            preencherTabela();
        }
    }//GEN-LAST:event_jbAlterarKeyPressed

    private void JbCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JbCancelarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            limparTabela();
            limpar();
            preencherTabela();
        }
        
    }//GEN-LAST:event_JbCancelarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbCancelar;
    private javax.swing.JTextField JtNumero;
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JButton jbAlterar;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JLabel jlCidade;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlId;
    private javax.swing.JLabel jlIdade;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlNumero;
    private javax.swing.JLabel jlRua;
    private javax.swing.JLabel jlSexo;
    private javax.swing.JLabel jlSobrenome;
    private javax.swing.JLabel jlTelefone1;
    private javax.swing.JLabel jlTelefone2;
    private javax.swing.JPanel jpContato;
    private javax.swing.JPanel jpEndereco;
    private javax.swing.JRadioButton jrbFeminino;
    private javax.swing.JRadioButton jrbMasculino;
    private javax.swing.JTextField jtBairro;
    private javax.swing.JTextField jtCidade;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtIdade;
    private javax.swing.JTextField jtNome;
    private javax.swing.JTabbedPane jtOutros;
    private javax.swing.JTextField jtPesquisa;
    private javax.swing.JTextField jtRua;
    private javax.swing.JTextField jtSobrenome;
    private javax.swing.JTextField jtTelefone1;
    private javax.swing.JTextField jtTelefone2;
    private javax.swing.JLabel ljBairro;
    // End of variables declaration//GEN-END:variables
}
