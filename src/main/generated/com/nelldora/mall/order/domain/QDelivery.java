package com.nelldora.mall.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 1921956660L;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final StringPath address = createString("address");

    public final StringPath addressNum = createString("addressNum");

    public final EnumPath<com.nelldora.mall.order.vo.DeliveryState> deliveryState = createEnum("deliveryState", com.nelldora.mall.order.vo.DeliveryState.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDelivery(String variable) {
        super(Delivery.class, forVariable(variable));
    }

    public QDelivery(Path<? extends Delivery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDelivery(PathMetadata metadata) {
        super(Delivery.class, metadata);
    }

}

