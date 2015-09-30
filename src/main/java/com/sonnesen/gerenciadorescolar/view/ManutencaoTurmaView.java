/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonnesen.gerenciadorescolar.view;

import com.sonnesen.gerenciadorescolar.dao.TurmaDAO;
import com.sonnesen.gerenciadorescolar.entity.Curso;
import com.sonnesen.gerenciadorescolar.entity.PeriodoEnum;
import com.sonnesen.gerenciadorescolar.entity.Turma;
import com.sonnesen.gerenciadorescolar.exception.BusinessException;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
public class ManutencaoTurmaView extends javax.swing.JDialog {

    private List<Turma> turmaList = Collections.emptyList();
    private BindingGroup bindingGroup;
    private Turma turmaSelecionada;

    /**
     * Creates new form ManutencaoTurmaView
     * @param parent
     * @param modal
     */
    public ManutencaoTurmaView(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        myInitComponets();
    }

    private void myInitComponets() {
        bindingGroup = new BindingGroup();

        TurmaDAO dao = new TurmaDAO();
        turmaList = ObservableCollections.observableList(dao.findAll());
        masterTable.setModel(new TurmaTableModel(turmaList));

        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, turmaList, masterTable);
        JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Código");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${dataInicio}"));
        columnBinding.setColumnName("Data Início");
        columnBinding.setColumnClass(Date.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${dataFim}"));
        columnBinding.setColumnName("Data Fim");
        columnBinding.setColumnClass(Date.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${periodo}"));
        columnBinding.setColumnName("Período");
        columnBinding.setColumnClass(PeriodoEnum.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${vagas}"));
        columnBinding.setColumnName("Vagas");
        columnBinding.setColumnClass(Short.class);
        columnBinding = jTableBinding.addColumnBinding(ELProperty.create("${curso}"));
        columnBinding.setColumnName("Curso");
        columnBinding.setColumnClass(Curso.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();

        Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement.codigo}"), codigoField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.dataInicio}"), dataInicioField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), dataInicioField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.dataFim}"), dataFimField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), dataFimField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.periodo}"), periodoField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), periodoField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.vagas}"), vagasField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), vagasField, BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, masterTable, ELProperty.create("${selectedElement.curso}"), cursoField, BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, masterTable, ELProperty.create("${selectedElement != null}"), cursoField, BeanProperty.create("enabled"));
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
        dataInicioField = new javax.swing.JTextField();
        dataFimField = new javax.swing.JTextField();
        periodoField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        vagasField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cursoField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manutenção de Usuários");
        setModal(true);

        masterTable.setModel(new TurmaTableModel());
        masterTable.setColumnSelectionAllowed(true);
        masterScrollPane.setViewportView(masterTable);
        masterTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        masterTable.getSelectionModel().addListSelectionListener(new TurmaMasterTableListSelectionListener());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Código:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Data Início:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Data Fim:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Período:");

        codigoField.setEnabled(false);

        dataInicioField.setEnabled(false);

        dataFimField.setEnabled(false);

        periodoField.setEnabled(false);

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

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Vagas:");

        vagasField.setEnabled(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Curso:");

        cursoField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataFimField)
                            .addComponent(codigoField)
                            .addComponent(periodoField)
                            .addComponent(dataInicioField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vagasField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cursoField)))
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
                    .addComponent(dataInicioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataFimField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(periodoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vagasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cursoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                    .addComponent(deleteButton)
                    .addComponent(newButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        int row = masterTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        turmaList.remove(row);
        List<Turma> temp = new ArrayList<>(turmaList);
        turmaList.clear();
        turmaList.addAll(temp);

        if (turmaSelecionada != null && turmaSelecionada.getCodigo() != null && turmaSelecionada.getCodigo() > 0) {
            turmaList.add(row, turmaSelecionada);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
        Turma turma = new Turma();
        turmaList.add(turma);
        int row = turmaList.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro selecionado?", "Confirmação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.OK_OPTION) {
            Thread t = new Thread(() -> {
                int selected = masterTable.getSelectedRow();
                Turma turma = turmaList.get(selected);
                TurmaDAO dao = new TurmaDAO();
                try {
                    dao.remove(turma);
                } catch (BusinessException ex) {
                    Logger.getLogger(ManutencaoTurmaView.class.getName()).log(Level.SEVERE, null, ex);
                }
                turmaList.remove(selected);
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
                TurmaDAO dao = new TurmaDAO();
                Turma turma = new Turma();

                Long codigo = 0L;
                try {
                    codigo = new Long(codigoField.getText());
                } catch (NumberFormatException e) {
                }

                turma.setCodigo(codigo);
                try {
                    //                turma.setLogin(dataFimField.getText());
//                turma.setNome(dataInicioField.getText());
//                turma.setSenha(periodoField.getText());
                    turma = dao.save(turma);
                } catch (BusinessException ex) {
                    Logger.getLogger(ManutencaoTurmaView.class.getName()).log(Level.SEVERE, null, ex);
                }
                turmaList.add(turma);
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
            java.util.logging.Logger.getLogger(ManutencaoTurmaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManutencaoTurmaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManutencaoTurmaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManutencaoTurmaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManutencaoTurmaView dialog = new ManutencaoTurmaView(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField cursoField;
    private javax.swing.JTextField dataFimField;
    private javax.swing.JTextField dataInicioField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
    private javax.swing.JTextField periodoField;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField vagasField;
    // End of variables declaration//GEN-END:variables

    private class TurmaTableModel extends AbstractTableModel {

        private List<Turma> turmas;
        private final int COLUMN_COUNT = 6;
        private final String[] columnNames = {"Código", "Data Início", "Data Fim", "Período", "Vagas", "Curso"};

        public TurmaTableModel() {
            turmas = new ArrayList();
        }

        public TurmaTableModel(List<Turma> turmas) {
            this();
            this.turmas.addAll(turmas);
        }

        @Override
        public int getRowCount() {
            return turmas.size();
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
            Turma turma = turmas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return turma.getCodigo();
                case 1:
                    return turma.getDataInicio();
                case 2:
                    return turma.getDataFim();
                case 3:
                    return turma.getPeriodo();
                case 4:
                    return turma.getVagas();
                case 5:
                    return turma.getCurso();
                default:
                    return "";
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Turma turma = turmas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    turma.setCodigo(Long.parseLong(aValue.toString()));
                    break;
                case 1:
                    turma.setDataInicio((Date) aValue);
                    break;
                case 2:
                    turma.setDataFim((Date) aValue);
                    break;
                case 3:
                    turma.setPeriodo((PeriodoEnum) aValue);
                    break;
                case 4:
                    turma.setVagas(Short.parseShort(aValue.toString()));
                    break;
                case 5:
                    turma.setCurso((Curso) aValue);
                    break;
            }
            fireTableDataChanged();
        }
    }

    private class TurmaMasterTableListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int row = masterTable.getSelectedRow();
            if (row >= 0) {
                Turma turma = turmaList.get(row);
                turmaSelecionada = new Turma(turma.getCodigo());
                turmaSelecionada.setDataInicio(turma.getDataInicio());
                turmaSelecionada.setDataFim(turma.getDataFim());
                turmaSelecionada.setPeriodo(turma.getPeriodo());
                turmaSelecionada.setVagas(turma.getVagas());
                turmaSelecionada.setCurso(turma.getCurso());
            }
        }
    }
}
