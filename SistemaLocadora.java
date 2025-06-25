import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

// Imports de todas as classes do projeto
import Models.*;
import Enum.*;
import Utils.Persistencia;
import Utils.RegraNegocioException;

/**
 * Classe principal que orquestra todo o sistema de locadora.
 * Contém o método main, os menus de interação com o usuário e a lógica para
 * chamar as operações de CRUD (Create, Read, Update, Delete).
 * * @author Geovanna Cunha Andrade Silva
 */
public class SistemaLocadora {

    // region Listas de Dados e Constantes de Arquivos
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    private static ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
    private static ArrayList<CategoriaVeiculo> listaCategorias = new ArrayList<>();
    private static ArrayList<ContratoAluguel> listaContratos = new ArrayList<>();
    
    private static final String ARQUIVO_CLIENTES = "clientes.dat";
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.dat";
    private static final String ARQUIVO_VEICULOS = "veiculos.dat";
    private static final String ARQUIVO_CATEGORIAS = "categorias.dat";
    private static final String ARQUIVO_CONTRATOS = "contratos.dat";
    // endregion

    // TO DO: FALTA EMAIL E ENDEREÇO E TELEFONE NO CLIENTE


    public static void main(String[] args) {
        carregarDados();

        int opcao;
        do {
            String menu = "Sistema de Gestão de Locadora\n\n" +
                          "1. Gerenciar Aluguéis\n" +
                          "2. Gerenciar Veículos\n" +
                          "3. Gerenciar Clientes\n" +
                          "4. Gerenciar Funcionários\n" +
                          "5. Gerenciar Categorias de Veículos\n" +
                          "0. Sair e Salvar";
            
            opcao = solicitarOpcao(menu, "Menu Principal");

            switch (opcao) {
                case 1: gerenciarAlugueis(); break;
                case 2: gerenciarVeiculos(); break;
                case 3: gerenciarClientes(); break;
                case 4: gerenciarFuncionarios(); break;
                case 5: gerenciarCategorias(); break;
                case 0:
                    salvarDados();
                    JOptionPane.showMessageDialog(null, "Sistema finalizado. Dados salvos com sucesso!");
                    break;
                default:
                    if(opcao != -1) {
                       JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
            }
        } while (opcao != 0);
    }
    
    // region Métodos de Gerenciamento (Menus)

    public static void gerenciarAlugueis() {
        String menu = "Gerenciar Aluguéis\n\n1. Criar Novo Aluguel\n2. Finalizar Aluguel\n3. Listar Contratos\n4. Consultar Contrato\n0. Voltar";
        int opcao;
        do {
            opcao = solicitarOpcao(menu, "Aluguéis");
            switch(opcao) {
                case 1: criarContrato(); break;
                case 2: finalizarContrato(); break;
                case 3: listarContratos(); break;
                case 4: consultarContrato(); break;
            }
        } while (opcao != 0);
    }

    public static void gerenciarVeiculos() {
        String menu = "Gerenciar Veículos\n\n1. Cadastrar\n2. Listar\n3. Alterar\n4. Excluir\n5. Consultar\n0. Voltar";
        int opcao;
        do {
            opcao = solicitarOpcao(menu, "Veículos");
            switch(opcao) {
                case 1: cadastrarVeiculo(); break;
                case 2: listarVeiculos(); break;
                case 3: alterarVeiculo(); break;
                case 4: excluirVeiculo(); break;
                case 5: consultarVeiculo(); break;
            }
        } while (opcao != 0);
    }

    public static void gerenciarClientes() {
        String menu = "Gerenciar Clientes\n\n1. Cadastrar\n2. Listar\n3. Alterar\n4. Excluir\n5. Consultar\n0. Voltar";
        int opcao;
        do {
            opcao = solicitarOpcao(menu, "Clientes");
            switch(opcao) {
                case 1: cadastrarCliente(); break;
                case 2: listarClientes(); break;
                case 3: alterarCliente(); break;
                case 4: excluirCliente(); break;
                case 5: consultarCliente(); break;
            }
        } while (opcao != 0);
    }

    public static void gerenciarFuncionarios() {
        String menu = "Gerenciar Funcionários\n\n1. Cadastrar\n2. Listar\n3. Alterar\n4. Excluir\n5. Consultar\n0. Voltar";
        int opcao;
        do {
            opcao = solicitarOpcao(menu, "Funcionários");
            switch(opcao) {
                case 1: cadastrarFuncionario(); break;
                case 2: listarFuncionarios(); break;
                case 3: alterarFuncionario(); break;
                case 4: excluirFuncionario(); break;
                case 5: consultarFuncionario(); break;
            }
        } while (opcao != 0);
    }

    public static void gerenciarCategorias() {
        String menu = "Gerenciar Categorias\n\n1. Cadastrar\n2. Listar\n3. Alterar\n4. Excluir\n5. Consultar\n0. Voltar";
        int opcao;
        do {
            opcao = solicitarOpcao(menu, "Categorias");
            switch(opcao) {
                case 1: cadastrarCategoria(); break;
                case 2: listarCategorias(); break;
                case 3: alterarCategoria(); break;
                case 4: excluirCategoria(); break;
                case 5: consultarCategoria(); break;
            }
        } while (opcao != 0);
    }
    //endregion

    // region Operações CRUD de Aluguéis
    public static void criarContrato() {
        try {
            if (listaClientes.isEmpty() || listaVeiculos.isEmpty()) {
                throw new RegraNegocioException("É necessário ter ao menos um cliente e um veículo disponível cadastrado para criar um contrato.");
            }
            int idCliente = Integer.parseInt(JOptionPane.showInputDialog(listarClientesParaSelecao() + "\nDigite o ID do Cliente:"));
            Cliente cliente = buscarClientePorId(idCliente);

            int idVeiculo = Integer.parseInt(JOptionPane.showInputDialog(listarVeiculosDisponiveisParaSelecao() + "\nDigite o ID do Veículo:"));
            Veiculo veiculo = buscarVeiculoPorId(idVeiculo);

            if (veiculo.getStatus() != StatusVeiculoEnum.DISPONIVEL) {
                throw new RegraNegocioException("O veículo com ID " + idVeiculo + " não está disponível para aluguel.");
            }

            String dataRetiradaStr = JOptionPane.showInputDialog("Data de Retirada Prevista (dd/MM/yyyy):");
            String dataDevolucaoStr = JOptionPane.showInputDialog("Data de Devolução Prevista (dd/MM/yyyy):");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dataRetirada = LocalDate.parse(dataRetiradaStr, formatter).atStartOfDay();
            LocalDateTime dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter).atStartOfDay();

            long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
            if (dias <= 0) dias = 1;
            
            CategoriaVeiculo categoria = buscarCategoriaPorId(veiculo.getIdCategoria());
            double valorTotalPrevisto = dias * categoria.getValorDiariaBase();

            ContratoAluguel novoContrato = new ContratoAluguel(cliente.getId(), veiculo.getIdVeiculo(), dataRetirada, dataDevolucao, valorTotalPrevisto);
            listaContratos.add(novoContrato);

            veiculo.setStatus(StatusVeiculoEnum.ALUGADO);

            JOptionPane.showMessageDialog(null, "Contrato criado com sucesso!\nID: " + novoContrato.getIdContrato() + "\nValor Previsto: R$ " + String.format("%.2f", valorTotalPrevisto));
        
        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de número ou data inválido. Por favor, verifique os dados inseridos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void finalizarContrato() {
        try {
            int idContrato = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do contrato a ser finalizado:"));
            ContratoAluguel contrato = buscarContratoPorId(idContrato);

            if (contrato.getStatusContrato() != StatusContratoEnum.ABERTO) {
                throw new RegraNegocioException("Este contrato não está aberto para finalização.");
            }

            Veiculo veiculo = buscarVeiculoPorId(contrato.getIdVeiculo());
            contrato.setStatusContrato(StatusContratoEnum.FECHADO);
            contrato.setDataHoraDevolucaoReal(LocalDateTime.now());
            veiculo.setStatus(StatusVeiculoEnum.DISPONIVEL);

            JOptionPane.showMessageDialog(null, "Contrato " + idContrato + " finalizado com sucesso!\nVeículo " + veiculo.getIdVeiculo() + " agora está disponível.");
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Por favor, digite um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void listarContratos() {
        if (listaContratos.isEmpty()) { JOptionPane.showMessageDialog(null, "Nenhum contrato cadastrado."); return; }
        StringBuilder sb = new StringBuilder("--- Lista de Contratos ---\n\n");
        try {
            for (ContratoAluguel c : listaContratos) {
                Cliente cliente = buscarClientePorId(c.getIdCliente());
                Veiculo veiculo = buscarVeiculoPorId(c.getIdVeiculo());
                sb.append(c.toString()).append("\n-------------------\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Contratos Cadastrados", JOptionPane.PLAIN_MESSAGE);
        } catch (RegraNegocioException e) {
             JOptionPane.showMessageDialog(null, "Erro ao listar contratos: um cliente ou veículo associado não foi encontrado. Detalhes: " + e.getMessage(), "Erro de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void consultarContrato() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do contrato para consulta:"));
            ContratoAluguel c = buscarContratoPorId(id);
            JOptionPane.showMessageDialog(null, c.toString(), "Detalhes do Contrato", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Por favor, digite um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    //endregion

    // region Operações CRUD de Veículos
    public static void cadastrarVeiculo() {
        try {
            if (listaCategorias.isEmpty()) {
                throw new RegraNegocioException("É necessário cadastrar uma categoria antes de cadastrar um veículo.");
            }
            String placa = JOptionPane.showInputDialog("Placa:");
            String modelo = JOptionPane.showInputDialog("Modelo:");
            String marca = JOptionPane.showInputDialog("Marca:");
            int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano:"));
            double km = Double.parseDouble(JOptionPane.showInputDialog("Quilometragem Atual:"));
            int idCat = Integer.parseInt(JOptionPane.showInputDialog(listarCategoriasParaSelecao() + "\nDigite o ID da Categoria:"));
            
            buscarCategoriaPorId(idCat); // Valida se a categoria existe

            Veiculo novo = new Veiculo(placa, modelo, marca, ano, idCat, km);
            listaVeiculos.add(novo);
            JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso! ID: " + novo.getIdVeiculo());
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ano, KM ou ID de categoria inválido. Verifique os números digitados.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void listarVeiculos() {
        if (listaVeiculos.isEmpty()) { JOptionPane.showMessageDialog(null, "Nenhum veículo cadastrado."); return; }
        StringBuilder sb = new StringBuilder("--- Lista de Veículos ---\n\n");
        try {
            for (Veiculo v : listaVeiculos) {
                CategoriaVeiculo cat = buscarCategoriaPorId(v.getIdCategoria());
                sb.append(v.toString()).append("\nCategoria: ").append(cat.getNome()).append("\n-------------------\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Veículos Cadastrados", JOptionPane.PLAIN_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar veículos: uma categoria associada não foi encontrada.", "Erro de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void alterarVeiculo() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do veículo para alterar:"));
            Veiculo veiculo = buscarVeiculoPorId(id);
            
            String novaPlaca = JOptionPane.showInputDialog("Nova placa:", veiculo.getPlaca());
            double novoKm = Double.parseDouble(JOptionPane.showInputDialog("Nova quilometragem:", veiculo.getKmAtual()));
            
            // Alterar o Status
            Object[] opcoesStatus = { StatusVeiculoEnum.DISPONIVEL, StatusVeiculoEnum.EM_MANUTENCAO };
            StatusVeiculoEnum novoStatus = (StatusVeiculoEnum) JOptionPane.showInputDialog(null, "Selecione o novo status:", 
                "Alterar Status", JOptionPane.QUESTION_MESSAGE, null, opcoesStatus, veiculo.getStatus());

            veiculo.setPlaca(novaPlaca);
            veiculo.setKmAtual(novoKm);
            if(novoStatus != null) veiculo.setStatus(novoStatus);

            JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID ou KM inválido. Por favor, digite um número.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void excluirVeiculo() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do veículo para excluir:"));
            Veiculo veiculo = buscarVeiculoPorId(id);
            
            for (ContratoAluguel c : listaContratos) {
                if (c.getIdVeiculo() == id && c.getStatusContrato() == StatusContratoEnum.ABERTO) {
                    throw new RegraNegocioException("Não é possível excluir o veículo, pois ele está em um contrato de aluguel ativo (ID: " + c.getIdContrato() + ").");
                }
            }
            
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o veículo:\n" + veiculo.getModelo(), "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                listaVeiculos.remove(veiculo);
                JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void consultarVeiculo() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do veículo para consulta:"));
            Veiculo v = buscarVeiculoPorId(id);
            JOptionPane.showMessageDialog(null, v.toString(), "Detalhes do Veículo", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    //endregion

    // region Operações CRUD de Clientes
    public static void cadastrarCliente() {
        try {
            String nome = JOptionPane.showInputDialog("Nome do cliente:");
            String telefone = JOptionPane.showInputDialog("Telefone (Ex: (XX) XXXXX-XXXX):");
            String email = JOptionPane.showInputDialog("E-mail do cliente:");
            String cpfCnpj = JOptionPane.showInputDialog("CPF/CNPJ do cliente:");
            String cnh = JOptionPane.showInputDialog("CNH do cliente:");
            String endereco = JOptionPane.showInputDialog("Endereço completo:");

            if (nome == null || nome.trim().isEmpty() || cpfCnpj == null || cpfCnpj.trim().isEmpty()) {
                throw new RegraNegocioException("Nome e CPF/CNPJ são obrigatórios!");
            }
            Cliente novoCliente = new Cliente(nome, telefone, email, cpfCnpj, cnh, endereco);
            listaClientes.add(novoCliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\nID Gerado: " + novoCliente.getId());
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Validação", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void listarClientes() {
        if (listaClientes.isEmpty()) { JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado."); return; }
        StringBuilder sb = new StringBuilder("--- Lista de Clientes ---\n\n");
        for (Cliente c : listaClientes) { sb.append(c.toString()).append("\n-------------------\n"); }
        JOptionPane.showMessageDialog(null, sb.toString(), "Clientes Cadastrados", JOptionPane.PLAIN_MESSAGE);
    }
    public static void alterarCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente para alterar:"));
            Cliente cliente = buscarClientePorId(id);
            String novoNome = JOptionPane.showInputDialog("Novo nome:", cliente.getNome());
            String novoTelefone = JOptionPane.showInputDialog("Novo telefone:", cliente.getTelefone());
            String novoEmail = JOptionPane.showInputDialog("Novo e-mail:", cliente.getEmail());
            cliente.setNome(novoNome);
            cliente.setTelefone(novoTelefone);
            cliente.setEmail(novoEmail);
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void excluirCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente para excluir:"));
            Cliente cliente = buscarClientePorId(id);
            for (ContratoAluguel c : listaContratos) {
                if (c.getIdCliente() == id) {
                    throw new RegraNegocioException("Não é possível excluir o cliente, pois ele está associado a um contrato (ID: " + c.getIdContrato() + ").");
                }
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                listaClientes.remove(cliente);
                JOptionPane.showMessageDialog(null, "Cliente excluído!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void consultarCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
            Cliente c = buscarClientePorId(id);
            JOptionPane.showMessageDialog(null, c.toString(), "Detalhes do Cliente", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    //endregion
    
    // region Operações CRUD de Funcionários
    public static void cadastrarFuncionario() {
        try {
            String nome = JOptionPane.showInputDialog("Nome do funcionário:");
            String telefone = JOptionPane.showInputDialog("Telefone:");
            String email = JOptionPane.showInputDialog("E-mail:");
            String cargo = JOptionPane.showInputDialog("Cargo:");
            String login = JOptionPane.showInputDialog("Login de acesso:");
            String senha = JOptionPane.showInputDialog("Senha de acesso:");
            Object[] niveis = { NivelAcessoEnum.FUNCIONARIO, NivelAcessoEnum.GESTOR };
            NivelAcessoEnum nivel = (NivelAcessoEnum) JOptionPane.showInputDialog(null, "Selecione o nível de acesso:", 
                "Nível de Acesso", JOptionPane.QUESTION_MESSAGE, null, niveis, niveis[0]);
            
            Funcionario novo = new Funcionario(nome, telefone, email, cargo, login, senha, nivel);
            listaFuncionarios.add(novo);
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso! ID: " + novo.getId());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void listarFuncionarios() {
        if (listaFuncionarios.isEmpty()) { JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado."); return; }
        StringBuilder sb = new StringBuilder("--- Lista de Funcionários ---\n\n");
        for (Funcionario f : listaFuncionarios) { sb.append(f.toString()).append("\n-------------------\n"); }
        JOptionPane.showMessageDialog(null, sb.toString(), "Funcionários", JOptionPane.PLAIN_MESSAGE);
    }
    public static void alterarFuncionario() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do funcionário para alterar:"));
            Funcionario f = buscarFuncionarioPorId(id);
            f.setCargo(JOptionPane.showInputDialog("Novo cargo:", f.getCargo()));
            // ... implementar alteração de outros campos
            JOptionPane.showMessageDialog(null, "Funcionário alterado!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void excluirFuncionario() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do funcionário para excluir:"));
            Funcionario f = buscarFuncionarioPorId(id);
            // Poderia ter uma regra de negócio aqui para não excluir o único gestor, por exemplo.
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                listaFuncionarios.remove(f);
                JOptionPane.showMessageDialog(null, "Funcionário excluído!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void consultarFuncionario() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do funcionário:"));
            Funcionario f = buscarFuncionarioPorId(id);
            JOptionPane.showMessageDialog(null, f.toString(), "Detalhes do Funcionário", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    //endregion

    // region Operações CRUD de Categorias
    public static void cadastrarCategoria() {
        try {
            String nome = JOptionPane.showInputDialog("Nome da Categoria (Ex: Econômico, SUV):");
            String desc = JOptionPane.showInputDialog("Descrição:");
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor da Diária Base:"));
            CategoriaVeiculo nova = new CategoriaVeiculo(nome, desc, valor);
            listaCategorias.add(nova);
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso! ID: " + nova.getIdCategoria());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor da diária inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void listarCategorias() {
        if (listaCategorias.isEmpty()) { JOptionPane.showMessageDialog(null, "Nenhuma categoria cadastrada."); return; }
        StringBuilder sb = new StringBuilder("--- Lista de Categorias ---\n\n");
        for (CategoriaVeiculo c : listaCategorias) { sb.append(c.toString()).append("\n-------------------\n"); }
        JOptionPane.showMessageDialog(null, sb.toString(), "Categorias Cadastradas", JOptionPane.PLAIN_MESSAGE);
    }
    public static void alterarCategoria() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria para alterar:"));
            CategoriaVeiculo cat = buscarCategoriaPorId(id);
            cat.setNome(JOptionPane.showInputDialog("Novo nome:", cat.getNome()));
            cat.setValorDiariaBase(Double.parseDouble(JOptionPane.showInputDialog("Novo valor da diária:", cat.getValorDiariaBase())));
            JOptionPane.showMessageDialog(null, "Categoria alterada com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID ou valor inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void excluirCategoria() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria para excluir:"));
            buscarCategoriaPorId(id); // Apenas para validar se existe antes de iterar
            for (Veiculo v : listaVeiculos) {
                if (v.getIdCategoria() == id) {
                    throw new RegraNegocioException("Não é possível excluir a categoria, pois ela está associada ao veículo ID " + v.getIdVeiculo());
                }
            }
            CategoriaVeiculo cat = buscarCategoriaPorId(id); // Busca novamente para remover
            listaCategorias.remove(cat);
            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void consultarCategoria() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da categoria:"));
            CategoriaVeiculo c = buscarCategoriaPorId(id);
            JOptionPane.showMessageDialog(null, c.toString(), "Detalhes da Categoria", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    //endregion

    // region Métodos Utilitários (Busca e Seleção)
    private static int solicitarOpcao(String menu, String titulo) {
        try {
            String escolha = JOptionPane.showInputDialog(null, menu, titulo, JOptionPane.PLAIN_MESSAGE);
            return (escolha == null) ? 0 : Integer.parseInt(escolha);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opção inválida! Por favor, insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    private static Cliente buscarClientePorId(int id) throws RegraNegocioException {
        for (Cliente c : listaClientes) { if (c.getId() == id) return c; }
        throw new RegraNegocioException("Cliente com ID " + id + " não encontrado.");
    }
    private static Funcionario buscarFuncionarioPorId(int id) throws RegraNegocioException {
        for (Funcionario f : listaFuncionarios) { if (f.getId() == id) return f; }
        throw new RegraNegocioException("Funcionário com ID " + id + " não encontrado.");
    }
    private static Veiculo buscarVeiculoPorId(int id) throws RegraNegocioException {
        for (Veiculo v : listaVeiculos) { if (v.getIdVeiculo() == id) return v; }
        throw new RegraNegocioException("Veículo com ID " + id + " não encontrado.");
    }
    private static CategoriaVeiculo buscarCategoriaPorId(int id) throws RegraNegocioException {
        for (CategoriaVeiculo c : listaCategorias) { if (c.getIdCategoria() == id) return c; }
        throw new RegraNegocioException("Categoria com ID " + id + " não encontrada.");
    }
    private static ContratoAluguel buscarContratoPorId(int id) throws RegraNegocioException {
        for (ContratoAluguel c : listaContratos) { if (c.getIdContrato() == id) return c; }
        throw new RegraNegocioException("Contrato com ID " + id + " não encontrado.");
    }
    private static String listarClientesParaSelecao() {
        if (listaClientes.isEmpty()) return "Nenhum cliente cadastrado.\n";
        StringBuilder sb = new StringBuilder("--- Clientes ---\n");
        for (Cliente c : listaClientes) { sb.append(c.getId()).append(" - ").append(c.getNome()).append("\n"); }
        return sb.toString();
    }
    private static String listarVeiculosDisponiveisParaSelecao() {
        StringBuilder sb = new StringBuilder("--- Veículos Disponíveis ---\n");
        boolean algumDisponivel = false;
        for (Veiculo v : listaVeiculos) {
            if (v.getStatus() == StatusVeiculoEnum.DISPONIVEL) {
                sb.append(v.getIdVeiculo()).append(" - ").append(v.getMarca()).append(" ").append(v.getModelo()).append("\n");
                algumDisponivel = true;
            }
        }
        if (!algumDisponivel) return "Nenhum veículo disponível no momento.\n";
        return sb.toString();
    }
    private static String listarCategoriasParaSelecao() {
        if (listaCategorias.isEmpty()) return "Nenhuma categoria cadastrada.\n";
        StringBuilder sb = new StringBuilder("--- Categorias ---\n");
        for (CategoriaVeiculo c : listaCategorias) { sb.append(c.getIdCategoria()).append(" - ").append(c.getNome()).append("\n"); }
        return sb.toString();
    }
    //endregion

    // region Persistência de Dados
    public static void salvarDados() {
        Persistencia.gravar(ARQUIVO_CLIENTES, listaClientes);
        Persistencia.gravar(ARQUIVO_FUNCIONARIOS, listaFuncionarios);
        Persistencia.gravar(ARQUIVO_VEICULOS, listaVeiculos);
        Persistencia.gravar(ARQUIVO_CATEGORIAS, listaCategorias);
        Persistencia.gravar(ARQUIVO_CONTRATOS, listaContratos);
    }

    @SuppressWarnings("unchecked")
    public static void carregarDados() {
        listaClientes = (ArrayList<Cliente>) Persistencia.ler(ARQUIVO_CLIENTES);
        listaFuncionarios = (ArrayList<Funcionario>) Persistencia.ler(ARQUIVO_FUNCIONARIOS);
        listaVeiculos = (ArrayList<Veiculo>) Persistencia.ler(ARQUIVO_VEICULOS);
        listaCategorias = (ArrayList<CategoriaVeiculo>) Persistencia.ler(ARQUIVO_CATEGORIAS);
        listaContratos = (ArrayList<ContratoAluguel>) Persistencia.ler(ARQUIVO_CONTRATOS);
    }
    //endregion
}
