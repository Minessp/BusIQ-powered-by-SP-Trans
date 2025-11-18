package br.com.msp.busiq.infrastructure.persistence.entities;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ApiKeyEntity> apiKeyEntities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {}

    public UserEntity(String name, String email, String password, RoleEntity role) {
        this.id = UlidCreator.getUlid().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles.add(role);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ApiKeyEntity> getApiKeys() {
        return apiKeyEntities;
    }

    public void setApiKeys(List<ApiKeyEntity> apiKeyEntities) {
        this.apiKeyEntities = apiKeyEntities;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
