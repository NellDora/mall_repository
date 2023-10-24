package com.nelldora.mall.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1501717404L;

    public static final QUser user = new QUser("user");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Integer> balance = createNumber("balance", Integer.class);

    public final ListPath<com.nelldora.mall.board.domain.Board, com.nelldora.mall.board.domain.QBoard> boardList = this.<com.nelldora.mall.board.domain.Board, com.nelldora.mall.board.domain.QBoard>createList("boardList", com.nelldora.mall.board.domain.Board.class, com.nelldora.mall.board.domain.QBoard.class, PathInits.DIRECT2);

    public final EnumPath<com.nelldora.mall.user.vo.Grade> grade = createEnum("grade", com.nelldora.mall.user.vo.Grade.class);

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> joinDate = createDateTime("joinDate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final ListPath<com.nelldora.mall.order.domain.Order, com.nelldora.mall.order.domain.QOrder> orderList = this.<com.nelldora.mall.order.domain.Order, com.nelldora.mall.order.domain.QOrder>createList("orderList", com.nelldora.mall.order.domain.Order.class, com.nelldora.mall.order.domain.QOrder.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath telephone = createString("telephone");

    public final NumberPath<Long> userNumber = createNumber("userNumber", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

