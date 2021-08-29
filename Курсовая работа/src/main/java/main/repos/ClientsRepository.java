package main.repos;

import main.entity.Clients;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<Clients,Long> {
}
