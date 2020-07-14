package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {

		return valorTotal(items);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {

		return ids.stream().map(id -> productRepository.findById(id)).filter(Optional::isPresent).map(Optional::get)
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream().map(order -> valorTotal(order)).reduce(0.0, Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return findProductsById(productIds).stream().collect(Collectors.groupingBy(Product::getIsSale));
	}

	private Double valorTotal(List<OrderItem> items) {
		return items.stream().map(item ->  {
			Optional <Product> product = productRepository.findById(item.getProductId());
			if(product.isPresent()){
				return product.get().getIsSale()==true? product.get().getValue() * item.getQuantity() * 0.8
				:product.get().getValue() * item.getQuantity();
			}
			return 0.0;
		}).reduce(0.0, Double::sum);
	}

}