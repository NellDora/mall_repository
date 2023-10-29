package com.nelldora.mall.item.repository;

import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.domain.ItemCategory;
import com.nelldora.mall.item.domain.QItem;
import com.nelldora.mall.item.domain.QItemCategory;
import com.nelldora.mall.item.vo.ItemCategoryVO;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.nelldora.mall.item.domain.QItem.item;

@Repository
@Transactional
public class JpaItemRepository implements ItemRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepository(EntityManager em){
        this.em = em;
        this.query= new JPAQueryFactory(em);
    }

    @Override
    public void save(Item item) {
        em.persist(item);
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }


    @Override
    public Optional<Item> findByCode(String code) {
        List<Item> items = em.createQuery("select t from Item t where t.itemCode = :code",Item.class)
                .setParameter("code",code)
                .getResultList();
        return items.stream().findAny();
    }

    @Override
    public List<Item> findByAll(){
        List<Item> items = em.createQuery("select t from Item t" , Item.class)
                .getResultList();
        return items;
    }

    @Override
    public List<Item> findByCategory(ItemCategory itemCategory) {
        QItem item = QItem.item;
        QItemCategory category =QItemCategory.itemCategory;
        List<Item> items = query.select(item).from(item).join(item.itemCategory,category).on(category.categoryName.eq(itemCategory.getCategoryName())).fetch();
        return items;
    }

    @Override
    public List<Item> findByNameAndCategory(String itemName, ItemCategory itemCategory) {
        QItem item = QItem.item;
        QItemCategory category = QItemCategory.itemCategory;
        List<Item> items = query.select(item).from(item).where(likeItemName(itemName)).join(item.itemCategory,category)
                .on(category.categoryName.eq(itemCategory.getCategoryName())).fetch();
        return items;
    }


    private BooleanExpression likeItemName(String itemName){
        if(StringUtils.hasText(itemName)){
            return item.name.like("%"+itemName+"%");
        }
        return null;
    }
}
