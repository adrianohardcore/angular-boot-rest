package br.com.adrianohardcore.repository;

import br.com.adrianohardcore.domain.Post;
import br.com.adrianohardcore.domain.QPost;
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
public class PostSpecifications {

    public static Specification<Post> filterByKeywordAndStatus(
            final String keyword,//
            final Post.Status status) {
        return (Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(keyword)) {
                predicates.add(
                        cb.or(cb.like(root.get(QPost.post.title.toString()), "%" + keyword + "%"),
                                cb.like(root.get(QPost.post.content.toString()), "%" + keyword + "%"))
                );
            }

            if (status != null) {
                predicates.add(cb.equal(root.get(QPost.post.status.toString()), status));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
