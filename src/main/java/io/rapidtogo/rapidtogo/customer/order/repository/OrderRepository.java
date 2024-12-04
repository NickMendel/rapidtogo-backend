package io.rapidtogo.rapidtogo.customer.order.repository;

import io.rapidtogo.rapidtogo.customer.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
