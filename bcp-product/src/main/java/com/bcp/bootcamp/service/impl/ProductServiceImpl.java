package com.bcp.bootcamp.service.impl;

import org.springframework.stereotype.Service;

import com.bcp.bootcamp.constant.ProductContant;
import com.bcp.bootcamp.document.Product;
import com.bcp.bootcamp.dto.ProductDTO;
import com.bcp.bootcamp.repository.IProductRepository;
import com.bcp.bootcamp.service.IProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {

	final IProductRepository productRepository;

	public ProductServiceImpl(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Mono<ProductDTO> save(ProductDTO customer) {
		var customerBean = convertToBean(customer);
		customerBean.setStatus(ProductContant.ST_ACTIVO);
		return productRepository.save(convertToBean(customer))
				.map(this::convertToDto);
	}

	@Override
	public Mono<ProductDTO> findById(String id) {
		return productRepository.findById(id)
				.switchIfEmpty(Mono.error(new RuntimeException("no se encontro el registro")))
				.map(this::convertToDto);
	}

	@Override
	public Flux<ProductDTO> findAll() {
		return productRepository.findAll()
				.filter(bean -> ProductContant.ST_ACTIVO.equals(bean.getStatus()))
				.map(this::convertToDto).log();
	}

	private ProductDTO convertToDto(Product product) {
		ProductDTO dto = new ProductDTO();
		if (product instanceof Product) {
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setType(product.getType());
		}
		return dto;
	}
	
	private Product convertToBean(ProductDTO product) {
		Product bean = new Product();
		if (product instanceof ProductDTO) {
			bean.setId(product.getId());
			bean.setName(product.getName());
			bean.setType(product.getType());
		}
		return bean;
	}

	@Override
	public Mono<ProductDTO> update(ProductDTO customer, String id) {
		return productRepository.findById(id)
				.switchIfEmpty(Mono.error(new RuntimeException("no se encontro el registro")))
				.flatMap(bean -> {
					bean.setName(customer.getName());
					bean.setType(customer.getType());
					return productRepository.save(convertToBean(customer))
							.map(this::convertToDto);
				});
	}

	@Override
	public Mono<Void> delete(String id) {
		return productRepository.findById(id)
				.switchIfEmpty(Mono.error(new RuntimeException("no se encontro el registro")))
				.flatMap(bean -> {
					bean.setStatus(ProductContant.ST_INACTIVO);
					return productRepository.save(bean).log().then();
				});
	}

}
