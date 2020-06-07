package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
    Page<Game> findAllByOrderByNameAsc(Pageable page);
}
