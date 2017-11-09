package com.codigo.aplios.toolbox.core.data.collect;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ClaimProductTypeCollector<T extends Claim> implements Collector<T, Map, Map> {

	public static void main(String[] args) {

		Set<Claim> claims = new HashSet<>();
		claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
		claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
		claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));

		claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));
		claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));

		ClaimProductTypeCollector<Claim> claimProductTypeCollector = new ClaimProductTypeCollector<>();
		claimProductTypeCollector.getRequiredTypes()
			.add(Claim.PRODUCT_TYPE.MOTOR);
		claimProductTypeCollector.getRequiredTypes()
			.add(Claim.PRODUCT_TYPE.HOUSEHOLD);
		Map oneClaimPerProductType = claims.stream()
			.collect(claimProductTypeCollector);

		int gg = 0;

	}

	private Set<Claim.PRODUCT_TYPE> requiredTypes = new HashSet<>();

	public Set<Claim.PRODUCT_TYPE> getRequiredTypes() {

		return this.requiredTypes;
	}

	@Override
	public Supplier<Map> supplier() {

		return HashMap::new;
	}

	@Override
	public BiConsumer<Map, T> accumulator() {

		return (map, claim) -> {
			if (map.get(claim.getProductType()) == null)
				map.put(claim.getProductType(), claim);
		};
	}

	@Override
	public Function<Map, Map> finisher() {

		return Function.identity();
	}

	@Override
	public BinaryOperator<Map> combiner() {

		return null;
	}

	@Override
	public Set<Characteristics> characteristics() {

		return Collections.singleton(Characteristics.IDENTITY_FINISH);
	}
}

class Claim {

	public enum PRODUCT_TYPE {
		MOTOR,
		HOUSEHOLD,
		TRAVEL
	}

	private PRODUCT_TYPE productType;

	public Claim(PRODUCT_TYPE productType) {

		this.productType = productType;
	}

	public PRODUCT_TYPE getProductType() {

		return this.productType;
	}

	public void setProductType(PRODUCT_TYPE productType) {

		this.productType = productType;
	}
}
