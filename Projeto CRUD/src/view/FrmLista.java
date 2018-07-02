package view;

import model.dao.ClienteDAO;
import util.ModeloGrade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class FrmLista extends JFrame {

    private static final long serialVersionUID = 1L;
    private static FrmLista frame;
    Object[][] dados = {{"Octavio Augusto", "0310487253", "44-987742236"},
            {"Felix Ramos", "054712313256", "55-32773255"}, {"Ana Paula", "10545120965", "44-991323265"},

    };
    String[] colunas = {"Nome", "CPF", "Telefone"};
    private JPanel contentPane;
    private JTextField txtValor;
    private JTable tabela;
    private JPanel pnTabela;
    private JComboBox cbCampo;
    private Connection connection;

    public FrmLista() {
        janela();
        tabela();
        atualizarGrade();
        populaCombo();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new FrmLista();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void janela() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 584, 364);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(176, 224, 230));
        panel.setBounds(10, 11, 548, 57);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblCampo = new JLabel("Campo");
        lblCampo.setBounds(10, 8, 46, 14);
        panel.add(lblCampo);

        cbCampo = new JComboBox();
        cbCampo.setBounds(10, 26, 132, 20);
        panel.add(cbCampo);

        JLabel lblValor = new JLabel("Valor");
        lblValor.setBounds(157, 8, 46, 14);
        panel.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(159, 26, 171, 20);
        panel.add(txtValor);
        txtValor.setColumns(10);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pesquisa();
            }
        });
        btnPesquisar.setBounds(356, 25, 117, 23);
        panel.add(btnPesquisar);

        pnTabela = new JPanel();
        pnTabela.setBounds(10, 72, 548, 198);
        contentPane.add(pnTabela);
        pnTabela.setLayout(new GridLayout(1, 1));

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 275, 548, 50);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.setBounds(10, 11, 89, 23);
        panel_2.add(btnInserir);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(109, 11, 89, 23);
        panel_2.add(btnAlterar);

        JButton btnExcliuir = new JButton("Excluir");
        btnExcliuir.setBounds(212, 11, 89, 23);
        panel_2.add(btnExcliuir);

        JButton btnVisualizar = new JButton("Visualizar");
        btnVisualizar.setBounds(321, 11, 89, 23);
        panel_2.add(btnVisualizar);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBounds(427, 11, 89, 23);
        panel_2.add(btnFechar);

        btnInserir.addActionListener(new btnInserirListener());
        btnAlterar.addActionListener(new btnAlterarListener());
        btnExcliuir.addActionListener(new btnExcluirListener());
        btnVisualizar.addActionListener(new btnVisualizarListener());
        btnFechar.addActionListener(new btnFecharListener());
    }

    public void tabela() {
        tabela = new JTable(new ModeloGrade());
        tabela.setToolTipText("Clique em uma linha para selecionar");
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JScrollPane rolagem = new JScrollPane(tabela);
        pnTabela.add(rolagem);
    }

    public void atualizarGrade() {
        ResultSet resultSet = new ClienteDAO().carregarGrade();
        tabela.setModel(new ModeloGrade(resultSet, new String[]{"Codigo", "Nome"}));
        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
    }

    public void populaCombo() {
        List<String> campos = new ClienteDAO().nomeCampos();
        DefaultComboBoxModel dfcBoxModel = (DefaultComboBoxModel) cbCampo.getModel();

        for (String campo : campos)
            dfcBoxModel.addElement(campo);
    }

    public void pesquisa() {

        ResultSet resultSet = new ClienteDAO().pesquisa("" + cbCampo.getSelectedItem(), txtValor.getText());
        tabela.setModel(new ModeloGrade(resultSet, new String[]{"Codigo", "Nome"}));
        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
    }

    public class btnInserirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            CadastroCliente cliente = new CadastroCliente();
            cliente.setVisible(true);


            cliente.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    super.windowClosed(e);
                    atualizarGrade();

                }
            });
        }

    }

    public class btnAlterarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada >= 0) {
                int codigoCliente = (int) tabela.getValueAt(linhaSelecionada, 0);
                CadastroCliente cliente = new CadastroCliente(codigoCliente);
                cliente.setVisible(true);

                cliente.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        atualizarGrade();

                    }
                });

            } else
                JOptionPane.showMessageDialog(null, "Selecione um registro");

        }

    }

    public class btnExcluirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] opcoes = {"Sim", "N�o"};
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada >= 0) {
                int codigoCliente = (int) tabela.getValueAt(linhaSelecionada, 0);

                int opcao = JOptionPane.showOptionDialog(rootPane, "Deseja realmente excluir?", "Confirma��o",
                        JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);
                if (JOptionPane.OK_OPTION == opcao) {
                    new ClienteDAO().excluir(codigoCliente);
                    atualizarGrade();
                }
            } else
                JOptionPane.showMessageDialog(null, "Selecione um registro");

        }

    }

    public class btnVisualizarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setEnabled(false);
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada >= 0) {
                int codigoCliente = (int) tabela.getValueAt(linhaSelecionada, 0);
                CadastroCliente cliente = new CadastroCliente(codigoCliente);
                cliente.setVisible(true);
                cliente.impedeEdicao();

            } else
                JOptionPane.showMessageDialog(null, "Selecione um registro");

        }

    }

    public class btnFecharListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }

    }
}
