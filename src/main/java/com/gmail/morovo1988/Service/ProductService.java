package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.DAO.BasketRepository;
import com.gmail.morovo1988.DAO.OrderRepository;
import com.gmail.morovo1988.DAO.ProductRepository;
import com.gmail.morovo1988.DAO.TypeRepository;
import com.gmail.morovo1988.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Дом on 31.07.2017.
 */
@Service
public class ProductService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BasketRepository basketRepository;



    @Transactional
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly=true)
    public List<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public long count() {
        return productRepository.count();
    }

    @Transactional(readOnly = true)
    public long countOrder() {
        return basketRepository.count();
    }


    @Transactional
    public void deleteProducts(long[] idList) {
        for (long id : idList)
            productRepository.delete(id);
    }
    @Transactional
    public void deleteProduct(long idList) {

            productRepository.delete(idList);
    }
    @Transactional
    public void deleteBasketOrder(long[] idList) {
        for (long id : idList)
            basketRepository.delete(id);
    }

    @Transactional
    public void orderProducts(CustomUser customUsers, long[] idList) {
        for (long id : idList) {
            Product product = productRepository.getOne(id);
            BasketOrders basketOrders = new BasketOrders(customUsers,product.getType(),product.getBrand(),product.getDiametr(),product.getPrice());
            basketRepository.save(basketOrders);

        }
    }
    @Transactional
    public void order(CustomUser customUsers,long idList) {

            Product product = productRepository.getOne(idList);
            BasketOrders basketOrders = new BasketOrders(customUsers,product.getType(),product.getBrand(),product.getDiametr(),product.getPrice());
            basketRepository.save(basketOrders);


    }

    @Transactional
    public void addType(Type type) {
        typeRepository.save(type);
    }



    @Transactional(readOnly=true)
    public List<Type> findTypes() {
        return typeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public long countByType(Type type) {
        return productRepository.countByType(type);
    }

    @Transactional(readOnly=true)
    public List<Product> findByType(Type type, Pageable pageable) {
        return productRepository.findByType(type, pageable);
    }

    @Transactional(readOnly=true)
    public Type findType(long id) {
        return typeRepository.findOne(id);
    }

    @Transactional(readOnly=true)
    public List<BasketOrders> findAllOrders(Pageable pageable) {
        return basketRepository.findAll(pageable).getContent();
    }
    @Transactional(readOnly=true)
    public List<BasketOrders> findByOrder(CustomUser customUser, Pageable pageable) {
        return basketRepository.findByOrder(customUser, pageable);
    }
    @Transactional(readOnly=true)
    public List<BasketOrders> findByOrder(CustomUser customUser) {
        return basketRepository.findByOrder(customUser);
    }

    @Transactional
    public void deleteBasketOrder(BasketOrders basketOrders) {
        basketRepository.delete(basketOrders);
    }

    @Transactional
    public void addBasketOrder(BasketOrders basketOrders) {
        basketRepository.save(basketOrders);
    }

    @Transactional(readOnly=true)
    public List<BasketOrders> findAllOrder(Pageable pageable) {
        return basketRepository.findAllOrder(pageable);
    }
}
