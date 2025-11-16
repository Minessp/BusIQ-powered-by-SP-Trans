package br.com.msp.busiq.infrastructure.persistence.entities;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_key")
public class ApiKey {
    @Id
    private String id;
    private String publicId;
    private String secretHash;
    private boolean revoked = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt = createdAt.plusMonths(2);

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public ApiKey() {}

    public ApiKey(String publicId, String secretHash, boolean revoked, LocalDateTime createdAt, LocalDateTime expiresAt,
                  UserEntity user) {
        this.id = UlidCreator.getUlid().toString();
        this.publicId = publicId;
        this.secretHash = secretHash;
        this.revoked = revoked;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getSecretHash() {
        return secretHash;
    }

    public void setSecretHash(String secretHash) {
        this.secretHash = secretHash;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}