package com.todo.todoP.Repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.todo.todoP.DTO.Member.MemberDTO;
import com.todo.todoP.DTO.Member.MemberSearchCondition;
import com.todo.todoP.DTO.Member.QMemberDTO;
import com.todo.todoP.Entity.Member;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.todo.todoP.Entity.QMember.*;
import static org.springframework.util.StringUtils.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MemberDTO> findAllPageSort(Pageable pageable, MemberSearchCondition condition) {
        JPAQuery<MemberDTO> query = queryFactory
                .select(new QMemberDTO(member))
                .from(member)
                .where(usernameEq(condition.getUsername()), emailEq(condition.getEmail()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC
            , pathBuilder.get(o.getProperty())));
        }

        List<Member> total = queryFactory
                .select(member)
                .from(member)
                .where(usernameEq(condition.getUsername()), emailEq(condition.getEmail()))
                .fetch();

        return PageableExecutionUtils.getPage(query.fetch(), pageable, total::size);
    }

    @Override
    public Slice<MemberDTO> findAllSliceSort(Pageable pageable, MemberSearchCondition condition) {
        JPAQuery<MemberDTO> query = queryFactory
                .select(new QMemberDTO(member))
                .from(member)
                .where(usernameEq(condition.getUsername()), emailEq(condition.getEmail()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(member.getType(), member.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC
                    , pathBuilder.get(o.getProperty())));
        }

        List<MemberDTO> fetch = query.fetch();
        boolean hasNext = false;
        if (fetch.size() > pageable.getPageSize()) {
            fetch.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(fetch, pageable,hasNext);
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? member.email.eq(email) : null;
    }

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.name.eq(username) : null;
    }
}
