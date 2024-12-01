package io.rapidtogo.rapidtogo.product.service;

import io.rapidtogo.rapidtogo.menu.model.Menu;
import io.rapidtogo.rapidtogo.menu.repository.MenuRepository;
import io.rapidtogo.rapidtogo.menu.repository.MenuRepositoryHelper;
import io.rapidtogo.rapidtogo.product.dto.ProductRequest;
import io.rapidtogo.rapidtogo.product.mapper.ProductMapper;
import io.rapidtogo.rapidtogo.product.model.Product;
import io.rapidtogo.rapidtogo.product.repository.ProductRepository;
import io.rapidtogo.rapidtogo.product.repository.ProductRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final MenuRepository menuRepository;
  private final ProductRepositoryHelper productRepositoryHelper;
  private final ProductMapper productMapper;
  private final MenuRepositoryHelper menuRepositoryHelper;

  @Transactional
  public String createProduct(Long menuId, ProductRequest request) {

    Menu menu = menuRepositoryHelper.findById(menuId);
    productRepositoryHelper.checkExistenceByNameAndMenuId(request.getName(), menuId);
    Product product = productMapper.mapToEntity(request);
    product.setMenu(menu);
    menu.getProducts().add(product);
    menuRepository.save(menu);

    return "Product with name " + request.getName() + " created successfully.";
  }

  @Transactional
  public String updateProduct(Long menuId, Long productId, ProductRequest request) {

    menuRepositoryHelper.checkExistence(menuId);
    Product product = productRepositoryHelper.findByIdAndMenuId(productId, menuId);
    productMapper.updateEntity(product, request);

    return "Product with ID " + productId + " updated successfully.";
  }

  @Transactional
  public String deleteProduct(Long menuId, Long productId) {

    menuRepositoryHelper.checkExistence(menuId);
    Product product = productRepositoryHelper.findByIdAndMenuId(productId, menuId);
    productRepository.delete(product);

    return "Product with ID " + productId + " deleted successfully.";
  }

}
