package Sample.elasticsearch1.Controller;


import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

import Sample.elasticsearch1.Entity.Product;


import Sample.elasticsearch1.Entity.Product;
import Sample.elasticsearch1.service.ElasticSearchService;
import Sample.elasticsearch1.service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/apis")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    Iterable<Product> findAll(){
        return productService.getProducts();

    }

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody  Product product){
        return productService.insertProduct(product);
    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        SearchResponse<Map> searchResponse =  elasticSearchService.matchAllServices();
        System.out.println(searchResponse.hits().hits().toString());
        return searchResponse.hits().hits().toString();
    }
    //matchAllProducts video content
    @GetMapping("/matchAllProducts")
    public List<Product> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse =  elasticSearchService.matchAllProductsServices();
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits= searchResponse.hits().hits();
        List<Product> listOfProducts  = new ArrayList<>();
        for(Hit<Product> hit : listOfHits){
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }

    @GetMapping("/matchAllProducts/{fieldValue}")
    public List<Product> matchAllProductsWithName(@PathVariable String fieldValue) throws IOException {
        SearchResponse<Product> searchResponse =  elasticSearchService.matchProductsWithName(fieldValue);
        System.out.println(searchResponse.hits().hits().toString());

        List<Hit<Product>> listOfHits= searchResponse.hits().hits();
        List<Product> listOfProducts  = new ArrayList<>();
        for(Hit<Product> hit : listOfHits){
            listOfProducts.add(hit.source());
        }
        return listOfProducts;
    }



}