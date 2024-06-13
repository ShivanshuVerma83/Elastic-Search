package Sample.elasticsearch1.repo;

import Sample.elasticsearch1.Entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {

}
