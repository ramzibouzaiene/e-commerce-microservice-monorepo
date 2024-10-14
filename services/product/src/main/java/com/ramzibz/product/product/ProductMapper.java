package com.ramzibz.product.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductRequest productRequest);

    @Mapping(source = "category.id", target = "categoryId")
    ProductRequest toRequestDto(Product product);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.description", target = "categoryDescription")
    ProductResponse toResponseDto(Product product);
    @Mapping(source = "quantity", target = "quantity")
    ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity);

}
