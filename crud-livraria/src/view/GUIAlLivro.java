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
import model.LivroVO;
import service.LivroService;
import service.ServiceFactory;

/**
 *
 * @author windows
 */
public class GUIAlLivro extends javax.swing.JInternalFrame {

    DefaultTableModel dtm = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"idlivro", "titulo", "subtitulo", "edicao", "editora", "isbn", "id_autor", "data_lancamento", "preco_venda"});
    /**
     * Creates new form GUIAlLivro
     */
    public GUIAlLivro() {
        initComponents();
        preencherTabela();
        preencherCombo();
    }
    
    private void preencherTabela(){
        try {
            LivroService ls = ServiceFactory.getLivroService();
            ArrayList<LivroVO> lli = new ArrayList<>();
           
            lli = ls.buscarLivro();
            
            for(int i = 0; i < lli.size(); i++){
                    dtm.addRow(new String[]{
                    String.valueOf(lli.get(i).getIdlivro()),
                    String.valueOf(lli.get(i).getTitulo()),
                    String.valueOf(lli.get(i).getSubtitulo()),
                    String.valueOf(lli.get(i).getEdicao()),
                    String.valueOf(lli.get(i).getEditora()),
                    String.valueOf(lli.get(i).getISBN()),
                    String.valueOf(lli.get(i).getAutor()),
                    String.valueOf(lli.get(i).getData_lancamento()),
                    String.valueOf(lli.get(i).getValor_venda()),
                });
            }
            jTableLivro.setModel(dtm);      
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
                
                if(filtro.equalsIgnoreCase("TITULO")){
                    query = " WHERE titulo like '%"+jtPesquisa.getText()+"%' ";
                }else if(filtro.equalsIgnoreCase("SUBTITULO")){
                    query = " WHERE subtitulo  like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("EDITORA")){
                    query = " WHERE editora like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("AUTOR")){
                    query = " WHERE nome  like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("ISBN")){
                    query = " WHERE isbn like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("ANO LANÇAMENTO")){
                    query = " WHERE data_lancamento  like '%"+jtPesquisa.getText()+"%'";
                }else if(filtro.equalsIgnoreCase("VALOR")){
                    query = " WHERE preco_venda like '%"+jtPesquisa.getText()+"%'";
                }
                
                LivroService ls = ServiceFactory.getLivroService();
                ArrayList<LivroVO> lli = ls.filtra(query);
                
                for(int i = 0; i < lli.size(); i++){
                dtm.addRow(new String[]{
                    String.valueOf(lli.get(i).getIdlivro()),
                    String.valueOf(lli.get(i).getTitulo()),
                    String.valueOf(lli.get(i).getSubtitulo()),
                    String.valueOf(lli.get(i).getEdicao()),
                    String.valueOf(lli.get(i).getEditora()),
                    String.valueOf(lli.get(i).getISBN()),
                    String.valueOf(lli.get(i).getAutor()),
                    String.valueOf(lli.get(i).getData_lancamento()),
                    String.valueOf(lli.get(i).getValor_venda()),
                    });
                }
                jTableLivro.setModel(dtm);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,
                    "Erro!" + e.getMessage());
        }
    }
    
    private void alterar(){
        int linha = jTableLivro.getSelectedRow();
        
        if(linha != -1){
            jtId.setText((String) jTableLivro.getValueAt(linha, 0));
            jtTitulo.setText((String) jTableLivro.getValueAt(linha, 1));
            jtSubtitulo.setText((String) jTableLivro.getValueAt(linha, 2));
            jtEdicao.setText((String) jTableLivro.getValueAt(linha, 3));
            jtEitora.setText((String) jTableLivro.getValueAt(linha, 4));
            jtAnoLancamento.setText((String) jTableLivro.getValueAt(linha, 7));
            jtISBN.setText((String) jTableLivro.getValueAt(linha, 5));
            jComboAutor.setSelectedItem((String) jTableLivro.getValueAt(linha, 6));
            jtValorVenda.setText((String) jTableLivro.getValueAt(linha, 8));            
        }else{
            
            JOptionPane.showMessageDialog(this, 
                    "Você não selecionou nenhuma linha!!");
        }
    }
    
    private void confirmarAlteracao(){
        
        int linha = jTableLivro.getSelectedRow();
        
        if(linha != -1){
            try {

                LivroVO lVO = new LivroVO();
                lVO.setIdlivro(Long.parseLong(jtId.getText()));

                if(!jtTitulo.getText().isEmpty()){
                    lVO.setTitulo(jtTitulo.getText());
                }else{
                    throw new NumberFormatException("Preencha o campo TITULO");
                }

                if(!jtSubtitulo.getText().isEmpty()){
                    lVO.setSubtitulo(jtSubtitulo.getText());
                }else{
                    throw new NumberFormatException("Preencha o campo SUBTITULO");
                }

                if(!jtEdicao.getText().isEmpty()){
                    lVO.setEdicao(Integer.parseInt(jtEdicao.getText()));
                }else{
                    throw new NumberFormatException("Preencha o campo EDIÇÃO");
                }

                if(!jtEitora.getText().isEmpty()){
                    lVO.setEditora(jtEitora.getText());
                }else{
                    throw new NumberFormatException("Preencha o campo EDITORA");
                }

                if(!jtISBN.getText().isEmpty()){
                    lVO.setISBN(Long.parseLong(jtISBN.getText()));
                }else{
                    throw new NumberFormatException("Preencha o campo ISBN");
                }

                if(jComboAutor.getSelectedItem() != null){
                    lVO.setS((AutorVO)jComboAutor.getSelectedItem());
                }else{
                    throw new NumberFormatException("Escolha um AUTOR");
                }

                if(!jtAnoLancamento.getText().isEmpty()){
                    lVO.setData_lancamento(jtAnoLancamento.getText());
                }else{
                    throw new NumberFormatException("Preencha o campo DATA DE LANCAMENTO");
                }

                LivroService ls = service.ServiceFactory.getLivroService();
                ls.alterarLivro(lVO);

                JOptionPane.showMessageDialog(this,
                        "Livro alterado com sucesso!!");

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
            int linha = jTableLivro.getSelectedRow();
            if(linha == -1){
                JOptionPane.showMessageDialog(rootPane, 
                        "Você não selecionou nunhuma linha !");
            }else{
                LivroService ls = ServiceFactory.getLivroService();
                
                String idLivro = (String) jTableLivro.getValueAt(linha, 0);
                ls.deletarLivro(Long.parseLong(idLivro));
                
                JOptionPane.showMessageDialog(rootPane,
                        "Livro excluido com sucesso!!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, 
                    "Erro ao deletar!" + e.getMessage());
        }
    }
    
    public void preencherCombo(){
        try {
          
            service.AutorService as = ServiceFactory.getAutorService();
            ArrayList<AutorVO> a = as.buscarAutores();
            
            for(int i= 0; i < a.size(); i++){
                jComboAutor.addItem(a.get(i));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro! "+e.getMessage());
        }
    }
    
    private void limparTabela() {      
        dtm.setNumRows(0);
    }
    
    private void limpar(){
        jtId.setText(null);
        jtTitulo.setText(null);
        jtSubtitulo.setText(null);
        jtEdicao.setText(null);
        jtEitora.setText(null);
        jtAnoLancamento.setText(null);
        jtISBN.setText(null);
        jtValorVenda.setText(null);
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
        jbAtualizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLivro = new javax.swing.JTable();
        jlTitulo = new javax.swing.JLabel();
        jtTitulo = new javax.swing.JTextField();
        jlSubTitulo = new javax.swing.JLabel();
        jtSubtitulo = new javax.swing.JTextField();
        jlEdicao = new javax.swing.JLabel();
        jtEdicao = new javax.swing.JTextField();
        jlEditora = new javax.swing.JLabel();
        jtEitora = new javax.swing.JTextField();
        jlDataLancamento = new javax.swing.JLabel();
        jlISBN = new javax.swing.JLabel();
        jtISBN = new javax.swing.JTextField();
        jlAutor = new javax.swing.JLabel();
        jComboAutor = new javax.swing.JComboBox();
        jtValorVenda = new javax.swing.JTextField();
        jlId = new javax.swing.JLabel();
        jtId = new javax.swing.JTextField();
        JbCancelar = new javax.swing.JButton();
        jbAlterar = new javax.swing.JButton();
        jlPreco = new javax.swing.JLabel();
        jtAnoLancamento = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);

        jtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtPesquisaKeyReleased(evt);
            }
        });

        jComboPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TITULO", "EDITORA", "AUTOR", "ISBN", "ANO LANÇAMENTO", "VALOR", " " }));
        jComboPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboPesquisaItemStateChanged(evt);
            }
        });

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

        jTableLivro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TITULO", "SUBTITULO", "EDICAO", "EDITORA", "ISBN", "AUTOR", "ANO LANCAMENTO", "VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLivro.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableLivro);
        if (jTableLivro.getColumnModel().getColumnCount() > 0) {
            jTableLivro.getColumnModel().getColumn(0).setResizable(false);
            jTableLivro.getColumnModel().getColumn(1).setResizable(false);
            jTableLivro.getColumnModel().getColumn(2).setResizable(false);
            jTableLivro.getColumnModel().getColumn(3).setResizable(false);
            jTableLivro.getColumnModel().getColumn(4).setResizable(false);
            jTableLivro.getColumnModel().getColumn(5).setResizable(false);
            jTableLivro.getColumnModel().getColumn(6).setResizable(false);
            jTableLivro.getColumnModel().getColumn(7).setResizable(false);
            jTableLivro.getColumnModel().getColumn(8).setResizable(false);
        }

        jlTitulo.setText("TITULO");

        jtTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtTituloKeyPressed(evt);
            }
        });

        jlSubTitulo.setText("SUBTITULO");

        jtSubtitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtSubtituloKeyPressed(evt);
            }
        });

        jlEdicao.setText("EDIÇÃO");

        jtEdicao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtEdicaoKeyPressed(evt);
            }
        });

        jlEditora.setText("EDITORA");

        jtEitora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtEitoraKeyPressed(evt);
            }
        });

        jlDataLancamento.setText("ANO DE LANÇAMENTO");

        jlISBN.setText("ISBN");
        jlISBN.setToolTipText("");

        jtISBN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtISBNKeyPressed(evt);
            }
        });

        jlAutor.setText("AUTOR");

        jComboAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboAutorKeyPressed(evt);
            }
        });

        jtValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtValorVendaKeyPressed(evt);
            }
        });

        jlId.setText("ID");

        jtId.setEditable(false);

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

        jlPreco.setText("VALOR VENDA");

        jtAnoLancamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtAnoLancamentoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtAnoLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboAutor, javax.swing.GroupLayout.Alignment.LEADING, 0, 243, Short.MAX_VALUE)
                                .addComponent(jlEdicao, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtEdicao, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jlEditora)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtValorVenda, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtISBN, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtEitora)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlPreco)
                                            .addComponent(jlISBN))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addComponent(jtTitulo)
                    .addComponent(jtSubtitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlTitulo)
                                    .addComponent(jlSubTitulo)
                                    .addComponent(jlDataLancamento)
                                    .addComponent(jlAutor))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jlId)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JbCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAlterar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jlTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlId))
                                .addGap(17, 17, 17)))
                        .addComponent(jtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlSubTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEdicao)
                            .addComponent(jlEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtEitora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDataLancamento)
                            .addComponent(jlISBN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtAnoLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlAutor)
                            .addComponent(jlPreco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbAlterar)
                            .addComponent(JbCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jbAtualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        deletar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbCancelarActionPerformed
        // TODO add your handling code here:
        limparTabela();
        limpar();
        preencherTabela();
    }//GEN-LAST:event_JbCancelarActionPerformed

    private void jbAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAlterarActionPerformed
        // TODO add your handling code here:
        confirmarAlteracao();
        limpar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbAlterarActionPerformed

    private void jtTituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTituloKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtSubtitulo.grabFocus();
        }
    }//GEN-LAST:event_jtTituloKeyPressed

    private void jtSubtituloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtSubtituloKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtEdicao.grabFocus();
        }
    }//GEN-LAST:event_jtSubtituloKeyPressed

    private void jtEdicaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEdicaoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtEitora.grabFocus();
        }
    }//GEN-LAST:event_jtEdicaoKeyPressed

    private void jtEitoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEitoraKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtAnoLancamento.grabFocus();
        }
    }//GEN-LAST:event_jtEitoraKeyPressed

    private void jtAnoLancamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtAnoLancamentoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtISBN.grabFocus();
        }
    }//GEN-LAST:event_jtAnoLancamentoKeyPressed

    private void jtISBNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtISBNKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jComboAutor.grabFocus();
        }
    }//GEN-LAST:event_jtISBNKeyPressed

    private void jComboAutorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboAutorKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jtValorVenda.grabFocus();
        }
    }//GEN-LAST:event_jComboAutorKeyPressed

    private void jtValorVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtValorVendaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            confirmarAlteracao();
            limpar();
            limparTabela();
            preencherTabela();
        }
    }//GEN-LAST:event_jtValorVendaKeyPressed

    private void JbCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JbCancelarKeyPressed
        // TODO add your handling code here:
        limparTabela();
        limpar();
        preencherTabela();
    }//GEN-LAST:event_JbCancelarKeyPressed

    private void jbAlterarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbAlterarKeyPressed
        // TODO add your handling code here:
        confirmarAlteracao();
        limpar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_jbAlterarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboAutor;
    private javax.swing.JComboBox<String> jComboPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLivro;
    private javax.swing.JButton jbAlterar;
    private javax.swing.JButton jbAtualizar;
    private javax.swing.JLabel jlAutor;
    private javax.swing.JLabel jlDataLancamento;
    private javax.swing.JLabel jlEdicao;
    private javax.swing.JLabel jlEditora;
    private javax.swing.JLabel jlISBN;
    private javax.swing.JLabel jlId;
    private javax.swing.JLabel jlPreco;
    private javax.swing.JLabel jlSubTitulo;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTextField jtAnoLancamento;
    private javax.swing.JTextField jtEdicao;
    private javax.swing.JTextField jtEitora;
    private javax.swing.JTextField jtISBN;
    private javax.swing.JTextField jtId;
    private javax.swing.JTextField jtPesquisa;
    private javax.swing.JTextField jtSubtitulo;
    private javax.swing.JTextField jtTitulo;
    private javax.swing.JTextField jtValorVenda;
    // End of variables declaration//GEN-END:variables
}
