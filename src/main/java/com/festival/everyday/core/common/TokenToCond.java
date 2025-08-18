package com.festival.everyday.core.common;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.festival.everyday.core.domain.QFestival.festival;

public class TokenToCond {
    public static BooleanExpression getAndConditions(String[] tokens) {
        BooleanExpression andCondition = null;
        for (String token : tokens) {
            BooleanExpression tokenExpr = festival.name.containsIgnoreCase(token);
            andCondition = (andCondition == null) ? tokenExpr : andCondition.and(tokenExpr);
        }
        return andCondition;
    }
}
