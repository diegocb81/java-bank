package br.com.dio;

import java.util.Arrays;
import java.util.Scanner;

import br.com.dio.exception.AccountNotFoundException;
import br.com.dio.exception.NoFundsEnoughException;
import br.com.dio.repository.AccountRepository;
import br.com.dio.repository.InvestmentRepository;

public class Main {

    private final static AccountRepository accountRepository = new AccountRepository();
    private final static InvestmentRepository investmentRepository = new InvestmentRepository();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Olá! Seja bem-vindo ao DIO Bank!");
        while (true) {
            System.out.println("Selecione a operação desejada:");
            System.out.println("1 - Criar uma Conta");
            System.out.println("2 - Criar um Investimento");
            System.out.println("3 - Fazer um Investimento");
            System.out.println("4 - Depositar na Conta");
            System.out.println("5 - Sacar da Conta");
            System.out.println("6 - Transferência entre Contas");
            System.out.println("7 - Investir");
            System.out.println("8 - Sacar Investimento");
            System.out.println("9 - Listar Contas");
            System.out.println("10 - Listar Investimentos");
            System.out.println("11 - Listar Carteiras de Investimentos");
            System.out.println("12 - Atualizar Investimentos");
            System.out.println("13 - Histórico de Conta");
            System.out.println("14 - Sair");
            var option = scanner.nextInt();
            switch(option){
                case 1 -> createAccount();
                case 2 -> createInvestment();
                case 3 -> 
                case 4 -> deposit();
                case 5 -> withdraw();
                case 6 -> 
                case 7 -> 
                case 8 -> 
                case 9 -> accountRepository.list().forEach(System.out::println);
                case 10 -> investmentRepository.list().forEach(System.out::println);
                case 11 -> investmentRepository.listWallets().forEach(System.out::println);
                case 12 -> {
                    investmentRepository.updateAmount();
                    System.out.println("Investimentos atualizados com sucesso!");
                }
                case 13 -> 
                case 14 -> System.exit(0);
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void createAccount() {
        System.out.println("Informe as chaves pix (separadas por ';')");
        var pix = Arrays.stream(scanner.next().split(";")).toList();
        System.out.println("Informe o valor inicial de depósito:");
        var amount = scanner.nextLong();
        var wallet = accountRepository.create(pix, amount);
        System.out.println("Conta criada: " + wallet);        
    }

    private static void createInvestment() {
        System.out.println("Informe a taxa do investimento:");
        var tax = scanner.nextInt();
        System.out.println("Informe o valor inicial de depósito:");
        var initialFunds = scanner.nextLong();
        var investment = investmentRepository.create(tax, initialFunds);
        System.out.println("Investimento criado: " + investment);        
    }

    private static void withdraw(){
        System.out.println("Informe a chave pix da conta para saque:");
        var pix = scanner.next();
        System.out.println("Informe o valor que será sacado:");
        var amount = scanner.nextLong();
        try {
            accountRepository.withdraw(pix, amount);
        } catch (NoFundsEnoughException | AccountNotFoundException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    private static void deposit(){
        System.out.println("Informe a chave pix da conta para depósito:");
        var pix = scanner.next();
        System.out.println("Informe o valor que será depositado:");
        var amount = scanner.nextLong();
        try {
            accountRepository.deposit(pix, amount);
        } catch (AccountNotFoundException ex) {
            System.out.println(ex.getMessage());
        }       
    }
}
