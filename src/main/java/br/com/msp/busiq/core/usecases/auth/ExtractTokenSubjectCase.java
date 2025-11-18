package br.com.msp.busiq.core.usecases.auth;

public interface ExtractTokenSubjectCase {
    String execute(String token);
}
