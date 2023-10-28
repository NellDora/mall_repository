package com.nelldora.mall.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemCategory is a Querydsl query type for ItemCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemCategory extends EntityPathBase<ItemCategory> {

    private static final long serialVersionUID = 492637002L;

    public static final QItemCategory itemCategory = new QItemCategory("itemCategory");

    public final StringPath categoryName = createString("categoryName");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final ListPath<Item, QItem> items = this.<Item, QItem>createList("items", Item.class, QItem.class, PathInits.DIRECT2);

    public QItemCategory(String variable) {
        super(ItemCategory.class, forVariable(variable));
    }

    public QItemCategory(Path<? extends ItemCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemCategory(PathMetadata metadata) {
        super(ItemCategory.class, metadata);
    }

}

