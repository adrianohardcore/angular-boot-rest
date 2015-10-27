package br.com.adrianohardcore.repository;

import br.com.adrianohardcore.domain.User;
import br.com.adrianohardcore.domain.QUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 *
 */
public class UserSpecifications {

    public static Specification<User> filterUsersByKeyword(
            final String keyword,//
            final String role //
    ) {

        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        cb.or(
                                cb.like(root.get(QUser.user.name.toString()), "%" + keyword + "%"),
                                cb.like(root.get(QUser.user.username.toString()), "%" + keyword + "%")
                        ));
            }

            if (StringUtils.hasText(role) && !"ALL".equals(role)) {
                predicates.add(cb.equal(root.get(QUser.user.role.toString()), role));
//                ListJoin<User_, String> roleJoin = root.join(User_.roles);
//                predicates.add(cb.equal(roleJoin, role));
            }

//            if (StringUtils.hasText(locked)) {
//                predicates.add(cb.equal(root.get(User_.locked), Boolean.valueOf(locked)));
//            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
