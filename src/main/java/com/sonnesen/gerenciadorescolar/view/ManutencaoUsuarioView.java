/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.view;

import com.sonnesen.gerenciadorescolar.dao.UsuarioDAO;
import com.sonnesen.gerenciadorescolar.entity.Usuario;
import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

/**
 *
 * @author winston
 */
public class ManutencaoUsuarioView extends javax.swing.JDialog {

    private List<Usuario> usuarioList = Collections.emptyList();
    private BindingGroup bindingGroup;
    private Usuario usuarioSelecionado;

    /**
     * Creates new form ManutencaoUsuarioView
     */
    public ManutencaoUsuarioView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        myInitComponets();
    }

    private void myInitComponets() {
        bindingGroup = new BindingGroup();

        UsuarioDAO dao = new UsuarioDAO();
        usuarioList = ObservableCollections.observableList(dao.findAll());
        masterTable.setModel(new UsuarioTableModel(usuarioList));

        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, usuarioList, masterTable);
        JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Código");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${nome}"));
        columnBinding.setColumnName("Nome");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${login}"));
        columnBinding.setColumnName("Login");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${senha}"));
        columnBinding.setColumnName("Senha");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement.codigo}"), codigoField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.login}"), loginField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), loginField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.nome}"), nomeField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), nomeField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.senha}"), senhaField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), senhaField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), deleteButton, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        bindingGroup.bind();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codigoField = new javax.swing.JTextField();
        nomeField = new javax.swing.JTextField();
        loginField = new javax.swing.JTextField();
        senhaField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manutenção de Usuários");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        masterTable.setModel(new UsuarioTableModel());
        masterScrollPane.setViewportView(masterTable);
        masterTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        masterTable.getSelectionModel().addListSelectionListener(new UsuarioMasterTableListSelectionListener());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Código:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nome:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Login:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Senha:");

        codigoField.setEnabled(false);

        nomeField.setEnabled(false);

        loginField.setEnabled(false);

        senhaField.setEnabled(false);

        saveButton.setText("Salvar");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Excluir");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        newButton.setText("Novo");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginField)
                            .addComponent(codigoField)
                            .addComponent(senhaField)
                            .addComponent(nomeField, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                    .addComponent(deleteButton)
                    .addComponent(newButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowOpened

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        int row = masterTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        usuarioList.remove(row);
        List<Usuario> temp = new ArrayList<>(usuarioList);
        usuarioList.clear();
        usuarioList.addAll(temp);

        if (usuarioSelecionado != null && usuarioSelecionado.getCodigo() != null && usuarioSelecionado.getCodigo() > 0) {
            usuarioList.add(row, usuarioSelecionado);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
        Usuario u = new Usuario();
        usuarioList.add(u);
        int row = usuarioList.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro selecionado?", "Confirmação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.OK_OPTION) {
            Thread t = new Thread(() -> {
                int selected = masterTable.getSelectedRow();
                Usuario u = usuarioList.get(selected);
                UsuarioDAO dao = new UsuarioDAO();
                try {
                    dao.remove(u);
                } catch (BusinessException ex) {
                    Logger.getLogger(ManutencaoUsuarioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuarioList.remove(selected);
            });
            t.start();
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada!", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = new Usuario();

                long codigo = 0L;
                try {
                    codigo = new Long(codigoField.getText());
                } catch (NumberFormatException e) {
                }

                usuario.setCodigo(codigo);
                usuario.setLogin(loginField.getText());
                usuario.setNome(nomeField.getText());
                usuario.setSenha(senhaField.getText());
                try {
                    usuario = dao.save(usuario);
                } catch (BusinessException ex) {
                    Logger.getLogger(ManutencaoUsuarioView.class.getName()).log(Level.SEVERE, null, ex);
                }
                usuarioList.add(usuario);
                cancelButton.doClick();
                JOptionPane.showMessageDialog(null, "Operação executada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        t.start();

    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManutencaoUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManutencaoUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManutencaoUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManutencaoUsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManutencaoUsuarioView dialog = new ManutencaoUsuarioView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosed(WindowEvent e) {
                        //System.exit(0);
                    }

                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField codigoField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField loginField;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
    private javax.swing.JTextField nomeField;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField senhaField;
    // End of variables declaration//GEN-END:variables

    private class UsuarioTableModel extends AbstractTableModel {

        private List<Usuario> usuarios;
        private final int COLUMN_COUNT = 4;
        private final String[] columnNames = {"Código", "Nome", "Login", "Senha"};

        public UsuarioTableModel() {
            usuarios = new ArrayList();
        }

        public UsuarioTableModel(List<Usuario> usuarios) {
            this();
            this.usuarios.addAll(usuarios);
        }

        @Override
        public int getRowCount() {
            return usuarios.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMN_COUNT;
        }

        @Override
        public String getColumnName(int i) {
            return columnNames[i];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Usuario usuario = usuarios.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return usuario.getCodigo();
                case 1:
                    return usuario.getNome();
                case 2:
                    return usuario.getLogin();
                case 3:
                    return usuario.getSenha();
                default:
                    return "";
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Usuario usuario = usuarios.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    usuario.setCodigo(Long.parseLong(aValue.toString()));
                    break;
                case 1:
                    usuario.setNome(aValue.toString());
                    break;
                case 2:
                    usuario.setLogin(aValue.toString());
                    break;
                case 3:
                    usuario.setSenha(aValue.toString());
                    break;
            }
            fireTableDataChanged();
        }
    }

    private class UsuarioMasterTableListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int row = masterTable.getSelectedRow();
            if (row >= 0) {
                Usuario u = usuarioList.get(row);
                usuarioSelecionado = new Usuario(u.getCodigo(), u.getLogin(), u.getNome(), u.getSenha());
            }
        }
    }
}
