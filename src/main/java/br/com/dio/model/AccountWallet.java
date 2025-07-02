package br.com.dio.model;

import java.util.List;

import lombok.Getter;

import static br.com.dio.model.BankService.ACCOUNT;

@Getter
public class AccountWallet extends Wallet{

@Override
public String toString() {
    return String.format("Conta criada com sucesso! Pix: %s | Saldo: R$ %d",
        pix.isEmpty() ? "não informado" : pix.get(0),
        getFunds());
}

    private final List<String> pix;

    public AccountWallet(final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
    }

    public AccountWallet(final long amount, final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
        addMoney(amount, "valor de criação da conta");
    }

    public void addMoney(final long amount, final String description) {
        var money = generateMoney(amount, description);
        this.money.addAll(money);
    }

}
