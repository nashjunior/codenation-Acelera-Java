package br.com.codenation.service;

import java.util.HashSet;
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
		Set<Product> setProducts = new HashSet<>();
		for (Long id : ids) {
			Optional<Product> product = productRepository.findById(id);
			if (product.isPresent()) {
				setProducts.add(product.get());
			}
		}
		return setProducts;
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		Double valorTotal = 0.0;
		for (List<OrderItem> order : orders) {
			valorTotal += valorTotal(order);
		}
		return valorTotal;
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return null;
	}

	private Double valorTotal(List<OrderItem> items) {
		Double valorTotal = 0.0;
		return items.stream().map(item -> productRepository.findById(item.getProductId())).collect(Collectors.toList())
				.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()).stream()
				.map(product -> product.getValue()).reduce(0.0, Double::sum);
		/*
		 * productRepository.findAll().stream() .filter(produto ->
		 * items.contains(produto)). Optional<Product> p =
		 * productRepository.findById(item.getProductId()); if (p.isPresent()) { if
		 * (p.get().getIsSale()) { valorTotal += (Double) p.get().getValue() * 0.8; }
		 * else { valorTotal += (Double) p.get().getValue(); } }
		 */
	}

}