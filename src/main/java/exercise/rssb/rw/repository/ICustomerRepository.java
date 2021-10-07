package exercise.rssb.rw.repository;

import exercise.rssb.rw.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The Interface of ICustomerRepository.
 *
 * @author Christian Iradukunda
 */
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, UUID> {

}