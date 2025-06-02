package com.exemple.memes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMemes extends JpaRepository<EntityMemes, Long> {
}
