package com.org.dumper.specifications;

import com.org.dumper.model.Property;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nonnull;
import javax.persistence.criteria.*;

public final class PropertySpecification {

    public static Specification<Property> bySqFeet(@Nonnull String sqFeet) {
        return (Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            -> cb.greaterThanOrEqualTo(root.get("sqFeet"), sqFeet);
    }
}
