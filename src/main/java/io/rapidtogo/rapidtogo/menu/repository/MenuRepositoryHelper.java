package io.rapidtogo.rapidtogo.menu.repository;

import io.rapidtogo.rapidtogo.menu.model.Menu;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuRepositoryHelper {

  private final MenuRepository menuRepository;

  public List<Menu> getAllByRestaurantId(Long restaurantId) {

    List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);

    if (menus.isEmpty()) {
      throw new EntityNotFoundException("No menus found for restaurant ID " + restaurantId);
    }

    return menus;
  }
}
