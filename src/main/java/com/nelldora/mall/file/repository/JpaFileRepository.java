package com.nelldora.mall.file.repository;

import com.nelldora.mall.file.domain.ItemImage;
import com.nelldora.mall.file.domain.QItemImage;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.QItem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class JpaFileRepository implements FileRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaFileRepository(EntityManager em)
    {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public void save(ItemImage itemImage) {
        em.persist(itemImage);
    }

    @Override
    public ItemImage findByItemIdFirst(Item inputItem) {
        QItemImage itemImage = QItemImage.itemImage;
        QItem item = QItem.item;
        return query.select(itemImage).from(itemImage).join(itemImage.item, item)
                .on(item.id.eq(inputItem.getId())).fetchFirst();

    }

}
