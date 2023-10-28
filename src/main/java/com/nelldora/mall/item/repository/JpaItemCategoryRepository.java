package com.nelldora.mall.item.repository;

import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.domain.QItemCategory;
import com.nelldora.mall.item.vo.ItemCategoryVO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

@Repository
@Transactional
@Slf4j
public class JpaItemCategoryRepository implements ItemCategoryRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemCategoryRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void save(ItemCategory itemCategory) {
        em.persist(itemCategory);
        log.info("JpaItemCategoryRepository : 카테고리 저장 성공");
    }

    @Override
    public ItemCategory findByCategory(ItemCategoryVO itemCategoryVO) {
        String category = itemCategoryVO.name();
        QItemCategory itemCategory = QItemCategory.itemCategory;
        ItemCategory findItemCategory = query.select(itemCategory).from(itemCategory).where(itemCategory.categoryName.eq(category)).fetchFirst();
        return findItemCategory;
    }

}
