package com.codenation.desafio.desafio.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import com.codenation.desafio.desafio.entity.EventLog;
import com.codenation.desafio.desafio.entity.QEventLog;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long>, QuerydslPredicateExecutor<EventLog>,
        QuerydslBinderCustomizer<QEventLog> {

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QEventLog eventlog) {

        bindings.excluding(eventlog.id);

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        bindings.bind(eventlog.eventDate).all((path, value) -> {
            Iterator<? extends Date> it = value.iterator();
            Date from = it.next();
            if (value.size() >= 2) {
                Date to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.eq(from));
            }

        });

        bindings.bind(eventlog.quantity).first((path, value) -> {
            return path.eq(value);
        });
    }

}
