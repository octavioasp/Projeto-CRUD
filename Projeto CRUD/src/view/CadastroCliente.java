package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.beans.Cliente;
import model.dao.ClienteDAO;
import util.Conexao;

public class CadastroCliente extends JDialog {

	private JPanel telaCadastro;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JTextField txtEmail;
	private MaskFormatter fmtCPFormatter;
	private MaskFormatter fmtCEPFormatter;
	private MaskFormatter fmtTelFormatter;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JComboBox cbUF;
	private JTextField ftxtCPF;
	private JTextField ftxtCEP;
	private JTextField ftxtTelefone;
	private JTextField txtBairro;
	private JButton btnSalvar;

	Cliente cliente;
	ClienteDAO clienteDAO;
	Connection connection;

	private int editar = -1;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente();
					frame.setVisible(true);
					frame.setModal(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroCliente() {
		janela();

	}

	public CadastroCliente(int codigo) {
		janela();
		editar = codigo;
		mostrarDadosFormulario(codigo);
	}

	public void janela() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 372);
		telaCadastro = new JPanel();
		telaCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(telaCadastro);
		telaCadastro.setLayout(null);

		JLabel lblCadastroDeCliente = new JLabel("Cadastro de Cliente");
		lblCadastroDeCliente.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblCadastroDeCliente.setBounds(143, 13, 205, 40);
		telaCadastro.add(lblCadastroDeCliente);

		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(22, 62, 46, 14);
		telaCadastro.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(89, 59, 96, 20);
		telaCadastro.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(22, 93, 46, 14);
		telaCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(89, 90, 272, 20);
		telaCadastro.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(22, 127, 46, 14);
		telaCadastro.add(lblCpf);

		try {
			fmtCPFormatter = new MaskFormatter("###.###.###-##");
			fmtCEPFormatter = new MaskFormatter("#####-###");
			fmtTelFormatter = new MaskFormatter("(##)# ####-####");
		} catch (ParseException e) {

			e.printStackTrace();
		}

		ftxtCPF = new JFormattedTextField(fmtCPFormatter);
		ftxtCPF.setBounds(89, 121, 96, 20);
		telaCadastro.add(ftxtCPF);

		ftxtCEP = new JFormattedTextField(fmtCEPFormatter);
		ftxtCEP.setBounds(397, 186, 88, 20);
		telaCadastro.add(ftxtCEP);

		ftxtTelefone = new JFormattedTextField(fmtTelFormatter);
		ftxtTelefone.setBounds(337, 220, 147, 20);
		telaCadastro.add(ftxtTelefone);

		rbMasculino = new JRadioButton("Masculino");
		rbMasculino.setSelected(true);
		rbMasculino.setBounds(206, 119, 88, 23);
		telaCadastro.add(rbMasculino);

		rbFeminino = new JRadioButton("Feminino");
		rbFeminino.setBounds(296, 119, 77, 23);
		telaCadastro.add(rbFeminino);

		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rbMasculino);
		bgSexo.add(rbFeminino);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(22, 157, 57, 14);
		telaCadastro.add(lblEndereo);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(89, 152, 272, 20);
		telaCadastro.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(22, 189, 46, 14);
		telaCadastro.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(89, 186, 173, 20);
		telaCadastro.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(284, 189, 26, 14);
		telaCadastro.add(lblUf);

		cbUF = new JComboBox();
		cbUF.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cbUF.setBounds(308, 184, 53, 20);
		telaCadastro.add(cbUF);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(375, 189, 26, 14);
		telaCadastro.add(lblCep);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(22, 223, 46, 14);
		telaCadastro.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(89, 220, 173, 20);
		telaCadastro.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(284, 223, 64, 14);
		telaCadastro.add(lblTelefone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 255, 46, 14);
		telaCadastro.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(89, 251, 396, 20);
		telaCadastro.add(txtEmail);
		txtEmail.setColumns(10);

		btnSalvar = new JButton("SALVAR");
		btnSalvar.setBounds(128, 282, 106, 40);
		telaCadastro.add(btnSalvar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(259, 282, 106, 40);
		telaCadastro.add(btnCancelar);

		btnSalvar.addActionListener(new btnSalvarListener());
		btnCancelar.addActionListener(new btnCancelarListener());

	}

	public class btnSalvarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			cliente = new Cliente();
			cliente.setNome(txtNome.getText());
			cliente.setCpf(ftxtCPF.getText());

			if (rbMasculino.isSelected())
				cliente.setSexo("M");
			else if (rbFeminino.isSelected())
				cliente.setSexo("F");

			cliente.setEndereco(txtEndereco.getText());
			cliente.setCidade(txtCidade.getText());
			cliente.setCep(ftxtCEP.getText());
			cliente.setBairro(txtBairro.getText());
			cliente.setUf("" + cbUF.getSelectedItem());
			cliente.setTelefone(ftxtTelefone.getText());
			cliente.setEmail(txtEmail.getText());
			cliente.setNome(txtNome.getText());

			clienteDAO = new ClienteDAO();
			if (editar > 0) {
				cliente.setCodigo(editar);
				clienteDAO.alterar(cliente);
			} else
				clienteDAO.salvar(cliente);

			setVisible(false);
			dispose();

		}

	}

	public class btnCancelarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CadastroCliente.this.dispose();
			

		}

	}

	public void mostrarDadosFormulario(int codigo) {
		Cliente cliente = new ClienteDAO().pesquisarId(codigo);

		txtCodigo.setText("" + cliente.getCodigo());
		txtNome.setText(cliente.getNome());
		txtEndereco.setText(cliente.getEndereco());
		txtCidade.setText(cliente.getCidade());
		ftxtCEP.setText(cliente.getCep());
		txtBairro.setText(cliente.getBairro());
		ftxtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());
		ftxtCPF.setText(cliente.getCpf());
		cbUF.setSelectedItem(cliente.getUf());
		if (cliente.getSexo().equals("M"))
			rbMasculino.setSelected(true);
		else if (cliente.getSexo().equals("F"))
			rbFeminino.setSelected(true);

	}

	public void impedeEdicao() {
		txtCodigo.setEnabled(false);
		txtNome.setEnabled(false);
		txtEndereco.setEnabled(false);
		txtCidade.setEnabled(false);
		ftxtCEP.setEnabled(false);
		txtBairro.setEnabled(false);
		ftxtTelefone.setEnabled(false);
		txtEmail.setEnabled(false);
		ftxtCPF.setEnabled(false);
		cbUF.setEnabled(false);
		rbFeminino.setEnabled(false);
		rbMasculino.setEnabled(false);
		btnSalvar.setEnabled(false);

	}
}
