package br.com.msp.busiq.core.usecases.auth;

public interface ValidateTokenCase {
    String execute(String token);
}
